package org.leach.dbdoc.analyzer.impl;

import org.leach.dbdoc.analyzer.ColumnAnalyzer;
import org.leach.dbdoc.entity.DbColumn;

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
 * 默认列信息解析器
 */
public class DefaultColumnAnalyzer implements ColumnAnalyzer {

    @Override
    public List<DbColumn> analyze(Connection connection, String tableName) {

        List<DbColumn> columns = new ArrayList<>();

        ResultSet columnRet = null;

        try {
            DatabaseMetaData metaData = connection.getMetaData();

            List<String> primaryKeyColumns = analyzePrimaryKey(metaData, tableName);

            columnRet = metaData.getColumns(null, "%", tableName, "%");
            while (columnRet.next()) {

                DbColumn dbColumn = analyzeDbColumn(columnRet);
                if (primaryKeyColumns.contains(dbColumn.getName())) {
                    dbColumn.setPrimaryKey(true);
                }
                columns.add(dbColumn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (columnRet != null) {
                try {
                    columnRet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return columns;
    }

    private List<String> analyzePrimaryKey(DatabaseMetaData metaData, String tableName) throws SQLException {
        List<String> primaryKeyColumns = new ArrayList<>();
        ResultSet primaryKeyResultSet = metaData.getPrimaryKeys(null, null, tableName);
        while (primaryKeyResultSet.next()) {
            primaryKeyColumns.add(primaryKeyResultSet.getString("COLUMN_NAME"));
        }
        return primaryKeyColumns;
    }


    private DbColumn analyzeDbColumn(ResultSet columnRet) throws SQLException {
        DbColumn dbColumn = new DbColumn();
        dbColumn.setName(columnRet.getString("COLUMN_NAME"));
        dbColumn.setType(columnRet.getString("TYPE_NAME"));
        dbColumn.setSize(columnRet.getInt("COLUMN_SIZE"));
        dbColumn.setDigits(columnRet.getInt("DECIMAL_DIGITS"));
        dbColumn.setNullable(columnRet.getInt("NULLABLE"));
        dbColumn.setDefaultVal(columnRet.getString("COLUMN_DEF"));
        dbColumn.setAutoIncrement(columnRet.getString("IS_AUTOINCREMENT"));
        dbColumn.setRemarks(columnRet.getString("REMARKS"));

        return dbColumn;
    }
}
