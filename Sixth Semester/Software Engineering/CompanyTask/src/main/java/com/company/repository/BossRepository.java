package com.company.repository;

import com.company.model.Boss;
import com.company.model.Employee;
import com.company.model.Task;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BossRepository implements IBossRepository{

    private final JdbcUtils jdbc;

    public BossRepository(JdbcUtils jdbc) {
        this.jdbc = jdbc;
    }

    public void login(String username, String password) {
        try (var conn = jdbc.getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM boss WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            var rs = stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Login failed", e);
        }
    }

    public void logout(String username) {
        try (var conn = jdbc.getConnection();
             var stmt = conn.prepareStatement("UPDATE boss SET status = 'OFFLINE' WHERE username = ?")) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Logout failed", e);
        }
    }

    @Override
    public List<Boss> findAll() {
        return List.of();
    }

    @Override
    public Boss findById(int id) {
        return null;
    }

    @Override
    public Boss save(Boss entity) {
        return null;
    }

    @Override
    public void delete(Boss entity) {}

    @Override
    public Boss update(Boss entity) {
        return null;
    }

    public List<Employee> getOnlineEmployees(int bossId) {
        var employees = new ArrayList<Employee>();
        try (var conn = jdbc.getConnection();
             var stmt = conn.prepareStatement("""
                 SELECT e.* FROM employee e 
                 JOIN boss_employee be ON e.id = be.employee_id
                 WHERE be.boss_id = ? AND e.status = 'ONLINE'
             """)) {
            stmt.setInt(1, bossId);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var emp = new Employee(rs.getString("username"), rs.getString("password"),
                        rs.getTimestamp("arrival_time") != null ? rs.getTimestamp("arrival_time").toLocalDateTime() : null,
                        rs.getString("status"));
                emp.setId(rs.getInt("id"));
                employees.add(emp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public void assignTask(Task task) {
        try (var conn = jdbc.getConnection();
             var stmt = conn.prepareStatement("""
                 INSERT INTO task(description, status, assigned_date, boss_id, employee_id)
                 VALUES (?, ?, ?, ?, ?)
             """)) {
            stmt.setString(1, task.getDescription());
            stmt.setString(2, task.getStatus());
            stmt.setTimestamp(3, Timestamp.valueOf(task.getAssignedDate()));
            stmt.setInt(4, task.getBossId());
            stmt.setInt(5, task.getEmployeeId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
