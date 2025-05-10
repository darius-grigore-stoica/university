package com.company.model;

import java.time.LocalDateTime;

public class Task extends MyEntity {
    private String description;
    private String status;
    private LocalDateTime assignedDate;
    private LocalDateTime completedDate;

    private int bossId;
    private int employeeId;

    public Task(String description, String status, int bossId, int employeeId, LocalDateTime assignedDate, LocalDateTime completedDate) {
        this.description = description;
        this.status = status;
        this.bossId = bossId;
        this.employeeId = employeeId;
        this.assignedDate = assignedDate;
        this.completedDate = completedDate;
    }

    public Task() {}

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getAssignedDate() { return assignedDate; }
    public void setAssignedDate(LocalDateTime assignedDate) { this.assignedDate = assignedDate; }

    public LocalDateTime getCompletedDate() { return completedDate; }
    public void setCompletedDate(LocalDateTime completedDate) { this.completedDate = completedDate; }

    public int getBossId() { return bossId; }
    public void setBossId(int bossId) { this.bossId = bossId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }
}
