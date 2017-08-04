package org.leach.dbdoc.analyzer;

import org.leach.dbdoc.entity.DbForeignKey;

import java.sql.Connection;
import java.util.List;

/**
 * @author Leach
 * @date 2017/2/25
 * <p>
 * 外键信息解析器接口
 */
public interface ForeignKeyAnalyzer {

    /**
     * 解析外键信息
     *
     * @param connection 数据库连接
     * @param tableName 要解析的表名称
     * @return 指定表中的所有外键信息
     */
    List<DbForeignKey> analyze(Connection connection, String tableName);
}
