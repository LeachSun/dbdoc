package org.leach.dbdoc.analyzer;

/**
 * @author Leach
 * @date 2017/2/25
 * <p>
 * 解析器工厂类接口
 */
public interface AnalyzerFactory {

    /**
     * 创建表信息解析器
     *
     * @return
     */
    TableAnalyzer createTableAnalyzer();

    /**
     * 创建列信息解析器
     *
     * @return
     */
    ColumnAnalyzer createColumnAnalyzer();

    /**
     * 创建索引信息解析器
     *
     * @return
     */
    IndexAnalyzer createIndexAnalyzer();

    /**
     * 创建外键信息解析器
     *
     * @return
     */
    ForeignKeyAnalyzer createForeignKeyAnalyzer();

}
