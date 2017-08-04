package org.leach.dbdoc.analyzer.impl.mysql;

import org.leach.dbdoc.analyzer.impl.DefaultTableAnalyzer;
import org.leach.dbdoc.entity.DbTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Leach
 * @date 2017/2/25
 * <p>
 * MySQL表信息解析器
 */
public class MySQLTableAnalyzer extends DefaultTableAnalyzer {

    @Override
    public List<DbTable> analyze(Connection connection) {
        List<DbTable> tables = super.analyze(connection);
        Map<String, String> tableInfos = analyzeTable(connection);
        for (int i = 0; i < tables.size(); i++) {
            DbTable dbTable = tables.get(i);
            dbTable.setComment(tableInfos.get(dbTable.getName()));
        }
        return tables;
    }

    private Map<String, String> analyzeTable(Connection connection) {
        Map<String, String> tableInfos = new HashMap<>();


        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("show table status");
            while (resultSet.next()) {
                tableInfos.put(resultSet.getString("Name"), resultSet.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return tableInfos;
    }
}
