package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() throws SQLException {

        try (Connection con = Util.getConnection();
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS usersTable (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), last_name VARCHAR(100), age TINYINT)");

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void dropUsersTable() throws SQLException {

        try (Connection con = Util.getConnection();
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate("DROP TABLE IF EXISTS usersTable");

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {

        try (Connection con = Util.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO usersTable (name, last_name, age) VALUES (?, ?, ?);")) {

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

        try (Connection con = Util.getConnection();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM usersTable WHERE id = ?")) {

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

        try (Connection con = Util.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM usersTable");
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

        try (Connection con = Util.getConnection();
             PreparedStatement stmt = con.prepareStatement("TRUNCATE TABLE usersTable")) {

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
