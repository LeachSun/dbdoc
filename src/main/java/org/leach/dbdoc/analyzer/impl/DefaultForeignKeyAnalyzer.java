package org.leach.dbdoc.analyzer.impl;

import org.leach.dbdoc.analyzer.ForeignKeyAnalyzer;
import org.leach.dbdoc.entity.DbForeignKey;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leach
 * @date 2017/2/27
 * <p>
 * 默认外键信息解析器
 */
public class DefaultForeignKeyAnalyzer implements ForeignKeyAnalyzer {

    @Override
    public List<DbForeignKey> analyze(Connection connection, String tableName) {
        List<DbForeignKey> dbForeignKeys = new ArrayList<>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet foreignKeyResultSet = metaData.getImportedKeys(null, null, tableName);
            while (foreignKeyResultSet.next()) {
                DbForeignKey foreignKey = new DbForeignKey();
                foreignKey.setPkTableName(foreignKeyResultSet.getString("PKTABLE_NAME"));
                foreignKey.setPkColumnName(foreignKeyResultSet.getString("PKCOLUMN_NAME"));
                foreignKey.setFkColumnName(foreignKeyResultSet.getString("FKCOLUMN_NAME"));
                foreignKey.setFkName(foreignKeyResultSet.getString("FK_NAME"));

                dbForeignKeys.add(foreignKey);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbForeignKeys;
    }
}
