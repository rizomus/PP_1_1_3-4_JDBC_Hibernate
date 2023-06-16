package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        var dao = new UserDaoJDBCImpl();
        dao.dropUsersTable();
        dao.createUsersTable();
//
        dao.saveUser("Vasya", "P", (byte) 13);
        dao.saveUser("Alex", "N", (byte) 14);
        dao.saveUser("Petya", "V", (byte) 15);

        System.out.println(dao.getAllUsers());

        dao.removeUserById(1);

        System.out.println(dao.getAllUsers());

        dao.cleanUsersTable();

        System.out.println(dao.getAllUsers());




    }
}

