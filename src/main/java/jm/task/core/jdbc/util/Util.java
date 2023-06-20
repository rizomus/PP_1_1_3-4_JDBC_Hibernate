package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {  // реализуйте настройку соеденения с БД


    public static Connection getConnection() throws SQLException {

        final String  dbURL = "jdbc:mysql://localhost:3306/world";
        final String dbUsername = "root";
        final String password = "synchro4";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, password);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return connection;
    }
}
