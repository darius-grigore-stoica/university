package com.company.controller;

import com.company.model.Boss;
import com.company.model.Employee;
import com.company.model.Task;
import com.company.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public static class LoginRequest {
        public String username;
        public String password;
    }

    @PostMapping("/login")
    public void login(@RequestBody EmployeeController.LoginRequest request) {
        employeeService.login(request.username, request.password);
    }

    public static class LogoutRequest {
        public String username;
    }

    @PostMapping("/logout")
    public void logout(@RequestBody EmployeeController.LogoutRequest request) {
        employeeService.logout(request.username);
    }

    @GetMapping("/{employeeId}/tasks")
    public List<Task> getTasks(@PathVariable int employeeId) {
        Employee emp = new Employee(); emp.setId(employeeId);
        return employeeService.getTasks(emp);
    }

    @PostMapping("/{employeeId}/tasks/{taskId}/complete")
    public void markTaskAsCompleted(@PathVariable int employeeId, @PathVariable int taskId) {
        employeeService.markTaskAsCompleted(taskId, employeeId);
    }

    @PostMapping("/{employeeId}/status")
    public void updateStatus(@PathVariable int employeeId, @RequestBody String status) {
        employeeService.updateStatusAndArrival(employeeId, status);
    }
}
