package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Ivan1", "Ivanov1", (byte) 20);
        user.saveUser("Ivan2", "Ivanov2", (byte) 25);
        user.saveUser("Ivan3", "Ivanov3", (byte) 31);
        user.saveUser("Ivan4", "Ivanov4", (byte) 38);
        user.removeUserById(2);
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
