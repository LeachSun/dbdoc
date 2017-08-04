package org.leach.dbdoc.analyzer.impl;

import org.leach.dbdoc.analyzer.*;

/**
 * @author Leach
 * @date 2017/2/25
 * <p>
 * 默认DefaultAnalyzerFactory
 */
public class DefaultAnalyzerFactory implements AnalyzerFactory {

    @Override
    public TableAnalyzer createTableAnalyzer() {
        return new DefaultTableAnalyzer();
    }

    @Override
    public ColumnAnalyzer createColumnAnalyzer() {
        return new DefaultColumnAnalyzer();
    }

    @Override
    public IndexAnalyzer createIndexAnalyzer() {
        return new DefaultIndexAnalyzer();
    }

    @Override
    public ForeignKeyAnalyzer createForeignKeyAnalyzer() {
        return new DefaultForeignKeyAnalyzer();
    }
}
