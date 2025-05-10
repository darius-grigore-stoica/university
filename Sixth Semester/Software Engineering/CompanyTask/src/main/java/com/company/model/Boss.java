package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class Boss extends MyEntity {
    private String username;
    private String password;
    private String status;
    private List<Employee> employees = new ArrayList<>();
    private List<Task> assignedTasks = new ArrayList<>();

    public Boss(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Boss() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }

    public List<Task> getAssignedTasks() { return assignedTasks; }
    public void setAssignedTasks(List<Task> assignedTasks) { this.assignedTasks = assignedTasks; }

    public void addEmployee(Employee e) { this.employees.add(e); }
    public void addTask(Task t) { this.assignedTasks.add(t); }

    public void setStatus(String online) {
        if (online.equals("ONLINE")) {
            this.status = "ONLINE";
        } else {
            this.status = "OFFLINE";
        }
    }
}
