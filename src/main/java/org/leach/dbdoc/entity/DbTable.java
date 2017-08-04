package org.leach.dbdoc.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leach
 * @date 2017/2/22
 * <p>
 * 表信息
 */
public class DbTable {

    /* 表名 */
    private String name;
    /* 表备注 */
    private String comment;

    /* 列信息 */
    private List<DbColumn> columns = new ArrayList<>();
    /* 外键信息 */
    private List<DbForeignKey> foreignKeys = new ArrayList<>();
    /* 索引信息 */
    private List<DbIndex> indexs = new ArrayList<>();

    public void addColumn(DbColumn column) {
        this.columns.add(column);
    }

    @Override
    public String toString() {
        return "DbTable{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", columns=" + columns +
                ", indexs=" + indexs +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<DbColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DbColumn> columns) {
        this.columns = columns;
    }

    public List<DbForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<DbForeignKey> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    public List<DbIndex> getIndexs() {
        return indexs;
    }

    public void setIndexs(List<DbIndex> indexs) {
        this.indexs = indexs;
    }
}
