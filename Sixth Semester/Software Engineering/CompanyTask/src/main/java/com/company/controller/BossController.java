package com.company.controller;

import com.company.model.Boss;
import com.company.model.Employee;
import com.company.service.BossService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boss")
public class BossController {

    private final BossService bossService;

    public BossController(BossService bossService) {
        this.bossService = bossService;
    }

    public static class LoginRequest {
        public String username;
        public String password;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest request) {
        bossService.login(request.username, request.password);
    }

    public static class LogoutRequest {
        public String username;
    }
    @PostMapping("/logout")
    public void logout(@RequestBody LoginRequest request) {
        bossService.logout(request.username);
    }

    @GetMapping("/{bossId}/online-employees")
    public List<Employee> getOnlineEmployees(@PathVariable int bossId) {
        Boss boss = new Boss();
        boss.setId(bossId);
        return bossService.getOnlineEmployees(boss);
    }

    public static class TaskRequest {
        public int employeeId;
        public String description;
    }

    @PostMapping("/{bossId}/assign-task")
    public void assignTask(@PathVariable int bossId, @RequestBody TaskRequest request) {
        Boss boss = new Boss(); boss.setId(bossId);
        Employee emp = new Employee(); emp.setId(request.employeeId);
        bossService.assignTask(boss, emp, request.description);
    }

}
