package ru.ibs;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import java.sql.*;
import java.util.*;

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
