package com.company.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Employee extends MyEntity {
    private String username;
    private String password;
    private LocalDateTime arrivalTime;
    private String status;
    private List<Task> tasks = new ArrayList<>();

    public Employee(String username, String password, LocalDateTime arrivalTime, String status) {
        this.username = username;
        this.password = password;
        this.arrivalTime = arrivalTime;
        this.status = status;
    }

    public Employee() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<Task> getTasks() { return tasks; }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }

    public void addTask(Task task) { this.tasks.add(task); }
}
