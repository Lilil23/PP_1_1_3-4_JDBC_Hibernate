package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        String CREATE_TABLE = "create table if not exists user " + "(" +
                "id int not null AUTO_INCREMENT, " +
                "name varchar(30) not null, " +
                "lastname varchar(50) not null, " +
                "age TINYINT, primary key (id)" + ")";
        try (Statement st = connection.createStatement()) {
            connection.setAutoCommit(false);
            st.executeUpdate(CREATE_TABLE);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void dropUsersTable() throws SQLException {
        String DROP_TABLE = "drop table if exists user";
        try (Statement st = connection.createStatement()) {
            connection.setAutoCommit(false);
            st.executeUpdate(DROP_TABLE);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }

    }


    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String INSERT_USER = "insert into user(name, lastname, age) VALUES (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER)) {
            connection.setAutoCommit(false);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void removeUserById(long id) throws SQLException {
        String DELETE_USER = "delete from user where id = " + id;
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate(DELETE_USER);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String SELECT_ALL = "select * from user";
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                users.add(new User(name, lastName, age));
            }
            ps.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    public void cleanUsersTable() throws SQLException {
        String TRUNCATE_TABLE = "TRUNCATE TABLE user";
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate(TRUNCATE_TABLE);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
