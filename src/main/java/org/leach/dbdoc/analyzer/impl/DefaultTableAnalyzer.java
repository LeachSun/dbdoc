package org.leach.dbdoc.analyzer.impl;

import org.leach.dbdoc.analyzer.TableAnalyzer;
import org.leach.dbdoc.entity.DbTable;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leach
 * @date 2017/2/25
 * <p>
 * 默认表信息解析器
 */
public class DefaultTableAnalyzer implements TableAnalyzer {

    @Override
    public List<DbTable> analyze(Connection connection) {

        List<DbTable> tables = new ArrayList<>();

        ResultSet tableSet = null;
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            tableSet = metaData.getTables(null, "%", "%", new String[]{"TABLE"});

            while (tableSet.next()) {

                DbTable dbTable = new DbTable();
                dbTable.setName(tableSet.getString("TABLE_NAME"));
                dbTable.setComment(tableSet.getString("REMARKS"));
                tables.add(dbTable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (tableSet != null) {
                try {
                    tableSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return tables;
    }

}
