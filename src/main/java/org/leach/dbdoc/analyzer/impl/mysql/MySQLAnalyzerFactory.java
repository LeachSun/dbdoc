package org.leach.dbdoc.analyzer.impl.mysql;

import org.leach.dbdoc.analyzer.*;
import org.leach.dbdoc.analyzer.impl.DefaultColumnAnalyzer;
import org.leach.dbdoc.analyzer.impl.DefaultForeignKeyAnalyzer;
import org.leach.dbdoc.analyzer.impl.DefaultIndexAnalyzer;

/**
 * @author Leach
 * @date 2017/2/27
 * <p>
 * MySQLDefaultAnalyzerFactory
 */
public class MySQLAnalyzerFactory implements AnalyzerFactory {

    @Override
    public TableAnalyzer createTableAnalyzer() {
        return new MySQLTableAnalyzer();
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
