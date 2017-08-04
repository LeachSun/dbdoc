package org.leach.dbdoc;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.leach.dbdoc.analyzer.*;
import org.leach.dbdoc.analyzer.impl.DefaultAnalyzerFactory;
import org.leach.dbdoc.analyzer.impl.mysql.MySQLAnalyzerFactory;
import org.leach.dbdoc.entity.DbColumn;
import org.leach.dbdoc.entity.DbForeignKey;
import org.leach.dbdoc.entity.DbIndex;
import org.leach.dbdoc.entity.DbTable;
import org.leach.dbdoc.generator.HtmlGenerator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Leach
 * @date 2017/2/27
 * <p>
 * 入口程序
 */
public class Bootstrap {

    public static void main(String[] args) {
        OptionParser parser = createOptionParser();
        OptionSet options = parser.parse(args);
        if (options.hasArgument("help") || options.has("?") || args.length == 0) {
            try {
                parser.printHelpOn(System.out);
            } catch (IOException e) {
            }
            return;
        }

        DbType dbType = (DbType) options.valueOf("t");
        String dbServer = (String) options.valueOf("s");
        String dbName = (String) options.valueOf("n");
        String dbUser = (String) options.valueOf("u");
        String dbPwd = (String) options.valueOf("p");
        DocType docType = (DocType) options.valueOf("d");


        List<DbTable> tableList = analyzeDatabase(dbType, dbServer, dbName, dbUser, dbPwd);

        generateDoc(docType, dbName, tableList);

    }

    /**
     * 数据库分析
     */
    private static List<DbTable> analyzeDatabase(DbType dbType, String dbServer, String dbName, String dbUser, String dbPwd) {
        AnalyzerFactory analyzerFactory;
        switch (dbType) {
            case MYSQL:
                analyzerFactory = new MySQLAnalyzerFactory();
                break;
            default:
                analyzerFactory = new DefaultAnalyzerFactory();
                break;
        }

        TableAnalyzer tableAnalyzer = analyzerFactory.createTableAnalyzer();
        ColumnAnalyzer columnAnalyzer = analyzerFactory.createColumnAnalyzer();
        ForeignKeyAnalyzer foreignKeyAnalyzer = analyzerFactory.createForeignKeyAnalyzer();
        IndexAnalyzer indexAnalyzer = analyzerFactory.createIndexAnalyzer();

        System.out.printf("Get connection of database[%s]...\n", dbName);
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection(dbType, dbServer, dbName, dbUser, dbPwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (connection == null) {
            System.out.println("Connection is null");
            return null;
        }

        System.out.printf("Analyzing tables of database[%s]...\n", dbName);
        List<DbTable> tableList = tableAnalyzer.analyze(connection);

        for (int i = 0; i < tableList.size(); i++) {
            DbTable dbTable = tableList.get(i);
            String tableName = dbTable.getName();
            System.out.printf("\n------Analyzing table[%s] start------\n", tableName);

            System.out.printf("Analyzing columns of table[%s]...\n", tableName);
            List<DbColumn> columns = columnAnalyzer.analyze(connection, tableName);
            dbTable.setColumns(columns);

            System.out.printf("Analyzing foreignKeys of table[%s]...\n", tableName);
            List<DbForeignKey> foreignKeys = foreignKeyAnalyzer.analyze(connection, tableName);
            dbTable.setForeignKeys(foreignKeys);

            System.out.printf("Analyzing indexs of table[%s]...\n", tableName);
            List<DbIndex> indexs = indexAnalyzer.analyze(connection, tableName);
            dbTable.setIndexs(indexs);

            System.out.printf("======Analyzing table[%s] end======\n", tableName);

        }
        return tableList;
    }

    /**
     * 生成文档
     *
     * @param docType
     * @param dbName
     * @param tableList
     */
    private static void generateDoc(DocType docType, String dbName, List<DbTable> tableList) {
        System.out.printf("\nGenerate database doc: %s.html\n", dbName);
        if (tableList == null) {
            System.out.println("No tables");
            return;
        }
        new HtmlGenerator().generateDoc(dbName, tableList);
    }

    /**
     * 创建参数解析器
     */
    private static OptionParser createOptionParser() {
        return new OptionParser() {
            {
                acceptsAll(Arrays.asList("help", "?"), "show this help.");

                acceptsAll(Arrays.asList("t", "type"), "options: " + DbType.allElement()).withRequiredArg()
                        .ofType(DbType.class)
                        .describedAs("database type")
                        .defaultsTo(DbType.MYSQL);

                acceptsAll(Arrays.asList("s", "server"), "e.g. 127.0.0.1:3306").withRequiredArg()
                        .ofType(String.class)

                        .describedAs("database server");
                acceptsAll(Arrays.asList("n", "name")).withRequiredArg()
                        .ofType(String.class)
                        .describedAs("database name");

                acceptsAll(Arrays.asList("u", "user")).withRequiredArg()
                        .ofType(String.class)
                        .describedAs("database user");

                acceptsAll(Arrays.asList("p", "pwd")).withRequiredArg()
                        .ofType(String.class)
                        .describedAs("database password");

                acceptsAll(Arrays.asList("d", "doc"), "options: " + DocType.allElement()).withOptionalArg()
                        .ofType(DocType.class)
                        .describedAs("document type")
                        .defaultsTo(DocType.HTML);
            }
        };
    }
}
