package org.leach.dbdoc.entity;

/**
 * @author Leach
 * @date 2017/2/22
 * <p>
 * 列信息
 */
public class DbColumn {

    /* 列名 */
    private String name;
    /* 类型 */
    private String type;
    /* 大小 */
    private int size;
    /* 小数部分位数 */
    private int digits;
    /* 是否主键 */
    private boolean primaryKey = false;
    /* 是否允许空，1为允许，0为不允许 */
    private int nullable;
    /* 默认值 */
    private String defaultVal;
    /* 自动 */
    private String autoIncrement;
    /* 备注 */
    private String remarks;

    @Override
    public String toString() {
        return "DbColumn{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", digits=" + digits +
                ", primaryKey=" + primaryKey +
                ", nullable=" + nullable +
                ", defaultVal='" + defaultVal + '\'' +
                ", autoIncrement='" + autoIncrement + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    public int getNullable() {
        return nullable;
    }

    public void setNullable(int nullable) {
        this.nullable = nullable;
    }

    public String getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }

    public String getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(String autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
