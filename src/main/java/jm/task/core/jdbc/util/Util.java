package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {  // реализуйте настройку соеденения с БД


    public static Connection getConnection() throws SQLException {

        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbUsername = "root";
        String password = "synchro4";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
