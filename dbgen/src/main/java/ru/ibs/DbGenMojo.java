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

            System.out.println("columns:");
            tablesNames.forEach((root, tableNames) -> tableNames.forEach(tableName -> {
                try {
                    ResultSet columnsRs = metaData.getColumns(root.get(0), (root.size() > 1 ? root.get(1) : null), tableName, null);
                    while(columnsRs.next()){
                        System.out.println();
                    }
//                      *  <OL>
//     *  <LI><B>TABLE_CAT</B> String {@code =>} table catalog (may be <code>null</code>)
//     *  <LI><B>TABLE_SCHEM</B> String {@code =>} table schema (may be <code>null</code>)
//     *  <LI><B>TABLE_NAME</B> String {@code =>} table name
//     *  <LI><B>COLUMN_NAME</B> String {@code =>} column name
//     *  <LI><B>DATA_TYPE</B> int {@code =>} SQL type from java.sql.Types
//                            *  <LI><B>TYPE_NAME</B> String {@code =>} Data source dependent type name,
//     *  for a UDT the type name is fully qualified
//     *  <LI><B>COLUMN_SIZE</B> int {@code =>} column size.
//     *  <LI><B>BUFFER_LENGTH</B> is not used.
//     *  <LI><B>DECIMAL_DIGITS</B> int {@code =>} the number of fractional digits. Null is returned for data types where
//                            * DECIMAL_DIGITS is not applicable.
//                            *  <LI><B>NUM_PREC_RADIX</B> int {@code =>} Radix (typically either 10 or 2)
//                            *  <LI><B>NULLABLE</B> int {@code =>} is NULL allowed.
//                            *      <UL>
//     *      <LI> columnNoNulls - might not allow <code>NULL</code> values
//                            *      <LI> columnNullable - definitely allows <code>NULL</code> values
//                            *      <LI> columnNullableUnknown - nullability unknown
//                            *      </UL>
//     *  <LI><B>REMARKS</B> String {@code =>} comment describing column (may be <code>null</code>)
//     *  <LI><B>COLUMN_DEF</B> String {@code =>} default value for the column, which should be interpreted as a string when the value is enclosed in single quotes (may be <code>null</code>)
//     *  <LI><B>SQL_DATA_TYPE</B> int {@code =>} unused
//                            *  <LI><B>SQL_DATETIME_SUB</B> int {@code =>} unused
//                            *  <LI><B>CHAR_OCTET_LENGTH</B> int {@code =>} for char types the
//                            *       maximum number of bytes in the column
//     *  <LI><B>ORDINAL_POSITION</B> int {@code =>} index of column in table
//                            *      (starting at 1)
//     *  <LI><B>IS_NULLABLE</B> String  {@code =>} ISO rules are used to determine the nullability for a column.
//     *       <UL>
//     *       <LI> YES           --- if the column can include NULLs
//                            *       <LI> NO            --- if the column cannot include NULLs
//                            *       <LI> empty string  --- if the nullability for the
//                            * column is unknown
//     *       </UL>
//     *  <LI><B>SCOPE_CATALOG</B> String {@code =>} catalog of table that is the scope
//                            *      of a reference attribute (<code>null</code> if DATA_TYPE isn't REF)
//                            *  <LI><B>SCOPE_SCHEMA</B> String {@code =>} schema of table that is the scope
//                            *      of a reference attribute (<code>null</code> if the DATA_TYPE isn't REF)
//                            *  <LI><B>SCOPE_TABLE</B> String {@code =>} table name that this the scope
//     *      of a reference attribute (<code>null</code> if the DATA_TYPE isn't REF)
//                            *  <LI><B>SOURCE_DATA_TYPE</B> short {@code =>} source type of a distinct type or user-generated
//                            *      Ref type, SQL type from java.sql.Types (<code>null</code> if DATA_TYPE
//                            *      isn't DISTINCT or user-generated REF)
//                            *   <LI><B>IS_AUTOINCREMENT</B> String  {@code =>} Indicates whether this column is auto incremented
//     *       <UL>
//     *       <LI> YES           --- if the column is auto incremented
//                            *       <LI> NO            --- if the column is not auto incremented
//     *       <LI> empty string  --- if it cannot be determined whether the column is auto incremented
//     *       </UL>
//     *   <LI><B>IS_GENERATEDCOLUMN</B> String  {@code =>} Indicates whether this is a generated column
//     *       <UL>
//     *       <LI> YES           --- if this a generated column
//                            *       <LI> NO            --- if this not a generated column
//     *       <LI> empty string  --- if it cannot be determined whether this is a generated column
//     *       </UL
                    while(columnsRs.next()){

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
