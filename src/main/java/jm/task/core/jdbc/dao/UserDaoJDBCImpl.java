package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection connection = null;

    public UserDaoJDBCImpl() {
        try {
            this.connection = Util.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void createUsersTable() throws SQLException {

        try (Statement stmt = connection.createStatement()) {

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users_table (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), last_name VARCHAR(100), age TINYINT)");

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void dropUsersTable() throws SQLException {

        try (Statement stmt = connection.createStatement()) {

            stmt.executeUpdate("DROP TABLE IF EXISTS users_table");

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO users_table (name, last_name, age) VALUES (?, ?, ?);")) {

            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setString(3, Byte.toString(age));
            stmt.executeUpdate();

            System.out.printf("User с именем – %s добавлен в базу данных", name);
            System.out.println();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void removeUserById(long id) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM users_table WHERE id = ?")) {

            stmt.setString(1, Long.toString(id));
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {

        ArrayList<User> users = new ArrayList<>();
        ;

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users_table");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(new User(rs.getString("name"), rs.getString("last_name"), rs.getByte("age")));
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement("TRUNCATE TABLE users_table")) {

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
