package com.company.service;

import com.company.model.Boss;
import com.company.model.Employee;
import com.company.model.Task;

public class Validation {
    public static void validateEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if (employee.getUsername() == null || employee.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee username cannot be empty");
        }
        if (employee.getPassword() == null || employee.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee password cannot be empty");
        }
        if (employee.getUsername().length() < 3 || employee.getUsername().length() > 20) {
            throw new IllegalArgumentException("Username must be between 3-20 characters");
        }
    }

    public static void validateBoss(Boss boss) {
        if (boss == null) {
            throw new IllegalArgumentException("Boss cannot be null");
        }
        if (boss.getUsername() == null || boss.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Boss username cannot be empty");
        }
        if (boss.getPassword() == null || boss.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Boss password cannot be empty");
        }
    }

    public static void validateTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty");
        }

        if (task.getDescription().length() > 500) {
            throw new IllegalArgumentException("Description too long (max 500 chars)");
        }
    }
}