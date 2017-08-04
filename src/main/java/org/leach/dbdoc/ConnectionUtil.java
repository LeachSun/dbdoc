package org.leach.dbdoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Leach
 * @date 2017/2/22
 * <p>
 * 数据库连接工具类
 */
public class ConnectionUtil {

    private static Map<DbType, String> dbDrivers = new HashMap<>();

    static {
        dbDrivers.put(DbType.MYSQL, "com.mysql.jdbc.Driver");
    }

    public static Connection getConnection(DbType dbType, String dbServer, String dbName, String dbUser, String dbPwd) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName(dbDrivers.get(dbType)).newInstance();
        String dbUrl = "jdbc:" + dbType.getUrlType()
                + "://" + dbServer
                + "/" + dbName;
        return DriverManager.getConnection(dbUrl, dbUser, dbPwd);
    }
}
