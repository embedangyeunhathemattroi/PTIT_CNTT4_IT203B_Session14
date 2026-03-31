package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/cyber_gaming?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // username MySQL
    private static final String PASS = "123456"; // mật khẩu MySQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}