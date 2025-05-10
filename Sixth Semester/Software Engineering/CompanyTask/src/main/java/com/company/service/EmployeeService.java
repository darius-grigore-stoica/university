package com.company.service;

import com.company.model.Employee;
import com.company.model.Task;
import com.company.repository.IEmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    private final IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void login(String username, String password) {
        try {
            employeeRepository.login(username, password);
        } catch (Exception ignored) {
            throw new RuntimeException("Invalid employee credentials.");
        }
    }

    public void logout(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Invalid username.");
        }
        employeeRepository.logout(username);
    }

    public List<Task> getTasks(Employee employee) {
        if (employee == null || employee.getId() <= 0) {
            throw new IllegalArgumentException("Invalid employee.");
        }
        return employeeRepository.getTasks(employee.getId());
    }

    public void markTaskAsCompleted(int taskId, int employeeId) {
        employeeRepository.markTaskAsCompleted(taskId, employeeId);
    }

    public void updateStatusAndArrival(int employeeId, String status) {
        employeeRepository.updateStatusAndArrival(employeeId, status, LocalDateTime.now());
    }
}
