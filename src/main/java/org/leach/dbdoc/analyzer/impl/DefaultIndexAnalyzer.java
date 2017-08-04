package org.leach.dbdoc.analyzer.impl;

import org.leach.dbdoc.analyzer.IndexAnalyzer;
import org.leach.dbdoc.entity.DbIndex;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Leach
 * @date 2017/2/27
 * <p>
 * 默认索引解析器
 */
public class DefaultIndexAnalyzer implements IndexAnalyzer {

    @Override
    public List<DbIndex> analyze(Connection connection, String tableName) {
        List<DbIndex> indexs = new ArrayList<>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet indexInfo = metaData.getIndexInfo(null, null, tableName, false, true);
            while (indexInfo.next()) {

                DbIndex index = new DbIndex();
                String indexName = indexInfo.getString("INDEX_NAME");
                if (Objects.equals("PRIMARY", indexName)) {
                    continue;
                }
                index.setName(indexName);
                index.setUnique(!indexInfo.getBoolean("NON_UNIQUE"));
                index.setColumnName(indexInfo.getString("COLUMN_NAME"));

                indexs.add(index);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indexs;
    }
}
