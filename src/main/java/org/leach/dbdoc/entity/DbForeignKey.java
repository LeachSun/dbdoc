package org.leach.dbdoc.entity;

/**
 * @author Leach
 * @date 2017/2/22
 * <p>
 * 外键信息
 */
public class DbForeignKey {

    /* 外键的名称 */
    private String fkName;
    /* 外键列名称 */
    private String fkColumnName;
    /* 被导入的主键表名称 */
    private String pkTableName;
    /* 被导入的主键列名称 */
    private String pkColumnName;

    @Override
    public String toString() {
        return "DbForeignKey{" +
                "fkName='" + fkName + '\'' +
                ", fkColumnName='" + fkColumnName + '\'' +
                ", pkTableName='" + pkTableName + '\'' +
                ", pkColumnName='" + pkColumnName + '\'' +
                '}';
    }

    public String getFkName() {
        return fkName;
    }

    public void setFkName(String fkName) {
        this.fkName = fkName;
    }

    public String getFkColumnName() {
        return fkColumnName;
    }

    public void setFkColumnName(String fkColumnName) {
        this.fkColumnName = fkColumnName;
    }

    public String getPkTableName() {
        return pkTableName;
    }

    public void setPkTableName(String pkTableName) {
        this.pkTableName = pkTableName;
    }

    public String getPkColumnName() {
        return pkColumnName;
    }

    public void setPkColumnName(String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }
}
