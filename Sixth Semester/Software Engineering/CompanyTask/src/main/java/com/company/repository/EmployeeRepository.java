package com.company.repository;

import com.company.model.Employee;
import com.company.model.Task;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository implements IEmployeeRepository{

    private final JdbcUtils jdbc;

    public EmployeeRepository(JdbcUtils jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Employee> findAll() {
        var employees = new ArrayList<Employee>();
        try (var conn = jdbc.getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM employee")) {
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

    @Override
    public Employee findById(int id) {
        try (var conn = jdbc.getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM employee WHERE id = ?")) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                var emp = new Employee(rs.getString("username"), rs.getString("password"),
                        rs.getTimestamp("arrival_time") != null ? rs.getTimestamp("arrival_time").toLocalDateTime() : null,
                        rs.getString("status"));
                emp.setId(rs.getInt("id"));
                return emp;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Employee save(Employee entity) {
        try (var conn = jdbc.getConnection();
             var stmt = conn.prepareStatement("INSERT INTO employee(username, password, status) VALUES(?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getUsername());
            stmt.setString(2, entity.getPassword());
            stmt.setString(3, entity.getStatus());
            stmt.executeUpdate();
            var rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Employee entity) {
        try (var conn = jdbc.getConnection();
             var stmt = conn.prepareStatement("DELETE FROM employee WHERE id = ?")) {
            stmt.setInt(1, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee update(Employee entity) {
        try (var conn = jdbc.getConnection();
             var stmt = conn.prepareStatement("UPDATE employee SET username = ?, password = ?, status = ? WHERE id = ?")) {
            stmt.setString(1, entity.getUsername());
            stmt.setString(2, entity.getPassword());
            stmt.setString(3, entity.getStatus());
            stmt.setInt(4, entity.getId());
            stmt.executeUpdate();
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void login(String username, String password) {
        try (var conn = jdbc.getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM employee WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            var rs = stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void logout(String username) {
        try (var conn = jdbc.getConnection();
             var stmt = conn.prepareStatement("UPDATE employee SET status = 'OFFLINE' WHERE username = ?")) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> getTasks(int employeeId) {
        List<Task> tasks = new ArrayList<>();
        var conn = jdbc.getConnection();

        try (var stmt = conn.prepareStatement(
                "SELECT * FROM task WHERE employee_id = ? ORDER BY assigned_date DESC")) {

            stmt.setInt(1, employeeId);
            var rs = stmt.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setDescription(rs.getString("description"));
                task.setStatus(rs.getString("status"));
                task.setAssignedDate(rs.getTimestamp("assigned_date") != null
                        ? rs.getTimestamp("assigned_date").toLocalDateTime()
                        : null);
                task.setCompletedDate(rs.getTimestamp("completed_date") != null
                        ? rs.getTimestamp("completed_date").toLocalDateTime()
                        : null);
                task.setBossId(rs.getInt("boss_id"));
                task.setEmployeeId(rs.getInt("employee_id"));

                tasks.add(task);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching tasks for employee " + employeeId, e);
        }

        return tasks;
    }


    public void markTaskAsCompleted(int taskId, int employeeId) {
        try (var conn = jdbc.getConnection();
             var stmt = conn.prepareStatement("UPDATE task SET status = 'COMPLETED', completed_date = NOW() WHERE id = ? AND employee_id = ?")) {
            stmt.setInt(1, taskId);
            stmt.setInt(2, employeeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStatusAndArrival(int employeeId, String status, LocalDateTime arrivalTime) {
        try (var conn = jdbc.getConnection();
             var stmt = conn.prepareStatement("UPDATE employee SET status = ?, arrival_time = ? WHERE id = ?")) {
            stmt.setString(1, status);
            stmt.setTimestamp(2, Timestamp.valueOf(arrivalTime));
            stmt.setInt(3, employeeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
