package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String USER_NAME = "Leyla";
    private static String PASSWORD = "Leyla737";
    private static String URL = "jdbc:mysql://localhost:3306/testhiber";

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}


