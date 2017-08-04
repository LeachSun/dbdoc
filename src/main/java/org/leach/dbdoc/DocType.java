package org.leach.dbdoc;

/**
 * @author Leach
 * @date 2017/2/27
 * <p>
 * 文档类型
 */
public enum DocType {
    HTML;

    public static String allElement() {
        StringBuilder builder = new StringBuilder();
        DocType[] values = DocType.values();
        for (int i = 0; i < values.length; i++) {
            builder.append(values[i].toString()).append(",");
        }
        return builder.substring(0, builder.length() - 1);
    }
}
