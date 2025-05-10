package com.company.repository;

import com.company.model.Employee;
import com.company.model.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface IEmployeeRepository extends IRepository<Employee> {
    void markTaskAsCompleted(int taskId, int employeeId);

    List<Task> getTasks(int id);

    void updateStatusAndArrival(int employeeId, String status, LocalDateTime now);

    public void login(String username, String password);

    public void logout(String username);
}
