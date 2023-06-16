package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private String host = "jdbc:mysql://localhost:3306/";
    private String mysqlUrl = "jdbc:mysql://localhost:3306/xxx";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        try (Connection con = DriverManager.getConnection(host, "root", "synchro4");
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate("CREATE SCHEMA IF NOT EXISTS xxx");
            stmt.executeUpdate("USE xxx");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS usersTable (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), lastName VARCHAR(100), age BIGINT)");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {

        try (Connection con = DriverManager.getConnection(mysqlUrl, "root", "synchro4");
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate("DROP TABLE IF EXISTS usersTable");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection con = DriverManager.getConnection(mysqlUrl, "root", "synchro4");
             PreparedStatement stmt = con.prepareStatement("INSERT INTO usersTable (name, lastName, age) VALUES (?, ?, ?);")) {

            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setString(3, Byte.toString(age));
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

        try (Connection con = DriverManager.getConnection(mysqlUrl, "root", "synchro4");
             PreparedStatement stmt = con.prepareStatement("DELETE FROM usersTable WHERE id = ?")) {

            stmt.setString(1, Long.toString(id));
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();
        ;

        try (Connection con = DriverManager.getConnection(mysqlUrl, "root", "synchro4");
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM usersTable");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(new User(rs.getString("name"), rs.getString("lastName"), rs.getByte("age")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {

        try (Connection con = DriverManager.getConnection(mysqlUrl, "root", "synchro4");
             PreparedStatement stmt = con.prepareStatement("TRUNCATE TABLE usersTable")) {

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
