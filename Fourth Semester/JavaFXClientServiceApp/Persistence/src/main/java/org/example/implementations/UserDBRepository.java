package org.example.implementations;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.JdbcUtils;
import org.example.User;
import org.example.interfaces.IUserRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class UserDBRepository implements IUserRepository {

    private JdbcUtils jdbcUtils;

    private static final Logger logger = LogManager.getLogger();

    public UserDBRepository(Properties props) {
        logger.traceEntry("Initializing UserRepository with properties: {}", props);
        this.jdbcUtils = new JdbcUtils(props);
    }

    @Override
    public void add(User entity) {
        logger.traceEntry("saving task {}", entity);
        var conn = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("insert into user(username, password) values (?,?)")) {
            preStmt.setString(1, entity.getUsername());
            preStmt.setString(2, entity.getPassword());
            preStmt.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        }

    }

    @Override
    public void remove(User entity) {
        logger.traceEntry("removing task {}", entity);
        var conn = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("delete from user where username = ?")) {
            preStmt.setString(1, entity.getUsername());
            preStmt.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        }

    }

    @Override
    public void update(User entity) {
        var conn = jdbcUtils.getConnection();
        logger.traceEntry("updating task {}", entity);
        try (PreparedStatement preStmt = conn.prepareStatement("update user set password = ? where username = ?")) {
            preStmt.setString(1, entity.getPassword());
            preStmt.setString(2, entity.getUsername());
            preStmt.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public User find(Integer id) {
        logger.traceEntry("finding task with id {}", id.toString());
        var conn = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("select * from user where userID = ?")) {
            preStmt.setString(1, id.toString());
            String username = preStmt.executeQuery().getString("username");
            String password = preStmt.executeQuery().getString("username");
            User user = new User(username, password);
            logger.traceExit(user);
            return user;
        } catch (SQLException e) {
            logger.error("No user found with id {}", id.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<User> getAll() {
        logger.traceEntry("Fetching all users");
        var conn = jdbcUtils.getConnection();
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement preStmt = conn.prepareStatement("select * from user")) {
            var rs = preStmt.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getString("username"), rs.getString("password"));
                u.setEntityID(rs.getInt("userID"));
                users.add(u);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return users;
    }

    @Override
    public boolean login(String username, String password) {
        if (username == null || password == null) {
            logger.error("Username or password is null");
            return false;
        } else {
            for (User user : getAll()) {
                if (user.getUsername().equals(username)) {
                    logger.traceEntry("User found in database with username {}", username, "and correct password");
                    return user.getPassword().equals(password);
                }
            }
        }
        logger.traceExit("User not found in database");
        return false;
    }
}
