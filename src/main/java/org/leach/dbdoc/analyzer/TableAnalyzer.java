package org.leach.dbdoc.analyzer;

import org.leach.dbdoc.entity.DbTable;

import java.sql.Connection;
import java.util.List;

/**
 * @author Leach
 * @date 2017/2/25
 * <p>
 * 表信息解析器接口
 */
public interface TableAnalyzer {

    /**
     * 解析表信息
     *
     * @param connection 数据库连接
     * @return 数据库中所有的表信息
     */
    List<DbTable> analyze(Connection connection);
}
