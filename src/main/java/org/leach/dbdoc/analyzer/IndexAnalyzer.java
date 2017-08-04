package org.leach.dbdoc.analyzer;

import org.leach.dbdoc.entity.DbIndex;

import java.sql.Connection;
import java.util.List;

/**
 * @author Leach
 * @date 2017/2/25
 * <p>
 * 索引解析器接口
 */
public interface IndexAnalyzer {

    /**
     * 解析索引信息
     *
     * @param connection 数据库连接
     * @param tableName 要解析的表名称
     * @return 指定表中的所有索引信息
     */
    List<DbIndex> analyze(Connection connection, String tableName);
}
