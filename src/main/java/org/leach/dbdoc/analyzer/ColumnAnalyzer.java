package org.leach.dbdoc.analyzer;

import org.leach.dbdoc.entity.DbColumn;

import java.sql.Connection;
import java.util.List;

/**
 * @author Leach
 * @date 2017/2/25
 * <p>
 * 列信息解析器接口
 */
public interface ColumnAnalyzer {


    /**
     * 解析列信息
     *
     * @param connection 数据库连接
     * @param tableName 要解析的表名称
     * @return 指定表中的所有列信息
     */
    List<DbColumn> analyze(Connection connection, String tableName);
}
