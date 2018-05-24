package ru.ibs;

import jdk.internal.dynalink.DynamicLinkerFactory;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mojo( name = "dbgen")
public class DbGenMojo extends AbstractMojo{

    private String user = "postgres";
    private String password = "postgres";
    private String url = "jdbc:postgresql://localhost:5432/gisgmp";
    public void execute() throws MojoExecutionException
    {
        getLog().info( "Hello, world." );
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean emptyStr(String str){
        return str == null || str.trim().isEmpty();
    }

    private enum ElemType{
        CAT, SHM, TB, COL
    }

    private final static String DFLT = "DEFAULT";

    private static class Elem{
        private final String name;
        private final ElemType type;

        public Elem(String name, ElemType type) {
            if(emptyStr(name))
                throw new IllegalArgumentException("name of element can't be empty");

            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public ElemType getType() {
            return type;
        }

        public boolean isCatalog(){
           return type.equals(ElemType.CAT);
        }

        public boolean isSchema(){
            return type.equals(ElemType.SHM);
        }

        public boolean isDefault(){
            return name.equals(DFLT);
        }
    }

    private static List<Elem> getCatalogs(DatabaseMetaData meta) throws SQLException {
        List<Elem> ret = new ArrayList<>();
        ResultSet catalogsRs = meta.getCatalogs();
        while(catalogsRs.next()) {
            String cname = catalogsRs.getString("TABLE_CAT");
            if(!emptyStr(cname))
                ret.add(new Elem(cname, ElemType.CAT));
        }

        return ret;
    }

    private static Map<Elem, List<Elem>> getSchemas(DatabaseMetaData meta, List<Elem> catalogs) throws SQLException {
        if (!catalogs.stream().allMatch(Elem::isCatalog))
            throw new RuntimeException("some passed elements aren't of a catalog type");

        Map<Elem, List<Elem>> ret = new HashMap<>();
        Elem dlftCat = new Elem(DFLT, ElemType.CAT);
        ret.put(dlftCat, new ArrayList<>());
        Map<String, Elem> mappedCats = catalogs.stream().collect(Collectors.toMap(Elem::getName, Function.identity()));
        mappedCats = new HashMap<>(mappedCats);
        mappedCats.put(DFLT, dlftCat);

        ResultSet schemasRs = meta.getSchemas();
        while (schemasRs.next()) {
            String cname = schemasRs.getString("TABLE_CATALOG");
            cname = !emptyStr(cname) ? cname : DFLT;
            Elem elem = mappedCats.get(cname);
            String sname = schemasRs.getString("TABLE_SCHEM");
            if(!emptyStr(sname))
                ret.get(elem).add(new Elem(sname, ElemType.SHM));
        }

        ret.entrySet().removeIf(entry -> entry.getValue().isEmpty());
        return ret;
    }

    private static Map<List<Elem>, List<Elem>> getTables(DatabaseMetaData meta, Map<Elem, List<Elem>> catSch){
       if(!catSch.keySet().stream().allMatch(Elem::isCatalog))
           throw new IllegalArgumentException("some of passed elements aren't of a catalog type");

       if(!catSch.values().stream().flatMap(List::stream).allMatch(Elem::isSchema))
           throw new IllegalArgumentException("some of passed elemtns aren't of schema type");

       Map<List<Elem>, List<Elem>> ret = new HashMap<>();
        catSch.forEach((cat, schs) -> schs.forEach(sch -> {
            try {
                List<Elem> key = Arrays.asList(cat, sch);
                ret.put(key, new ArrayList<>());
                ResultSet tableRs = meta.getTables(cat.getName(), sch.getName(), null, null);
                while (tableRs.next()) {
                    String tname = tableRs.getString("TABLE_NAME");
                    String ttype = tableRs.getString("TABLE_TYPE");
                    if (!emptyStr(tname) && !ttype.contains("SYSTEM") && !ttype.contains("INDEX"))
                        ret.get(key).add(new Elem(tname, ElemType.TB));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));

       return null;
    }

    public static void main(String[] args){
        DbGenMojo dbgen = new DbGenMojo();
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(dbgen.url, dbgen.user, dbgen.password);
            DatabaseMetaData metaData = conn.getMetaData();

            List<String> catalogsNames = new ArrayList<>();
            ResultSet catalogsRs = metaData.getCatalogs();
            while(catalogsRs.next())
                catalogsNames.add(catalogsRs.getString("TABLE_CAT"));

            System.out.println("catalogs: ");
            catalogsNames.forEach(System.out::println);

            Map<String, List<String>> schemasNames = new HashMap<>();
            catalogsNames.stream().filter(str -> !str.isEmpty()).forEach(str -> schemasNames.put(str, new ArrayList<>()));

            final String catUn = "UNKNOWN";
            schemasNames.put(catUn, new ArrayList<>());
            ResultSet schemasRs = metaData.getSchemas();
            while(schemasRs.next()) {
                String cat = schemasRs.getString("TABLE_CATALOG") != null ? schemasRs.getString("TABLE_CATALOG") : catUn;
                String schema = schemasRs.getString("TABLE_SCHEM");
                if(cat != null && !cat.trim().isEmpty() && schema !=null && !schema.trim().isEmpty() )
                    schemasNames.get(cat).add(schema);
            }
            final String schUn = "UNKNOWN";

            System.out.println("\r\nschemas:");
            schemasNames.forEach((cat, schemas) -> schemas.forEach(schema -> System.out.println("catalog: " + cat + " schema: " + schema)));

            Map<List<String>, List<String>> tablesNames = new HashMap<>();

            schemasNames.forEach((cat, schemas) -> {
                schemas.forEach(schema -> {
                    try {
                        tablesNames.put(Arrays.asList(cat, schema), new ArrayList<>());
                        ResultSet tableRs = metaData.getTables(cat, schema, null, null);
                        while(tableRs.next()){
                            String tcat = tableRs.getString("TABLE_CAT");
                            String tscheme = tableRs.getString("TABLE_SCHEM");
                            String tname = tableRs.getString("TABLE_NAME");
                            if(!emptyStr(tcat) && !emptyStr(tscheme) && !emptyStr(tname))
                                tablesNames.get(Arrays.asList(tcat, tscheme)).add(tname);
                        }
                    }
                    catch (Exception e){
                        throw new RuntimeException(e);
                    }
                });
                tablesNames.put(Arrays.asList(cat), new ArrayList<>());

                try {
                    ResultSet tableRs = metaData.getTables(cat, "", null, null);
                    while(tableRs.next()){
                        String tcat = tableRs.getString("TABLE_CAT");
                        String tname = tableRs.getString("TABLE_NAME");
                        String ttype = tableRs.getString("TABLE_TYPE");
                        if(ttype == null || ttype.contains("SYSTEM") || ttype.contains("INDEX"))
                            continue;
                        System.out.println("cat:" + cat +   " tcat: "+ tcat + " table: " + tname + " type: " + ttype);
                        if(/*!emptyStr(tcat)  &&*/ !emptyStr(tname))
                            tablesNames.get(Arrays.asList(cat )).add(tname);
                    }
                }
                catch (Exception e){
                  throw new RuntimeException(e);
                }
            });
            System.out.println("tables: ");
            tablesNames.forEach((root, tables) ->tables.forEach(tname -> {
               System.out.println("catalog: " + root.get(0) + (root.size() > 1 ? " schema: " + root.get(1) :" ") + " table: " + tname);
            }));

            System.out.println("columns:");
            tablesNames.forEach((root, tableNames) -> tableNames.forEach(tableName -> {
                try {
                    ResultSet columnsRs = metaData.getColumns(root.get(0), (root.size() > 1 ? root.get(1) : null), tableName, null);
                    while(columnsRs.next()){
                        String cname = columnsRs.getString("COLUMN_NAME");
                        String ctype = columnsRs.getString("DATA_TYPE");
                        System.out.println("catalog:" + root.get(0)
                                + (root.size() > 1 ? " scheme: " + root.get(1) : "")
                                + " table:" + tableName
                                + " column: " + cname  + " type: " + ctype);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
