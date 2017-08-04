package org.leach.dbdoc;

/**
 * @author Leach
 * @date 2017/2/22
 * <p>
 * 数据库类型
 */
public enum DbType {
    MYSQL("mysql"),
    SQL_SERVER("sqlserver"),
    ORACLE("oracle");

    private String urlType;

    DbType(String urlType) {
        this.urlType = urlType;
    }

    public String getUrlType() {
        return urlType;
    }


    public static String allElement() {
        StringBuilder builder = new StringBuilder();
        DbType[] values = DbType.values();
        for (int i = 0; i < values.length; i++) {
            builder.append(values[i].toString()).append(",");
        }
        return builder.substring(0, builder.length() - 1);
    }
}
