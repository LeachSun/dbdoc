package org.leach.dbdoc.entity;

/**
 * @author Leach
 * @date 2017/2/22
 * <p>
 * 索引信息
 */
public class DbIndex {

    /* 索引名称 */
    private String name;
    /* 是否唯一索引 */
    private boolean unique;
    /* 索引列名 */
    private String columnName;

    @Override
    public String toString() {
        return "DbIndex{" +
                "name='" + name + '\'' +
                ", unique='" + unique + '\'' +
                ", columnName='" + columnName + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
