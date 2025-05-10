package com.company.service;

import com.company.model.Boss;
import com.company.model.Employee;
import com.company.model.Task;
import com.company.repository.IBossRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BossService {

    private final IBossRepository bossRepository;

    public BossService(IBossRepository bossRepository) {
        this.bossRepository = bossRepository;
    }

    public void login(String username, String password) {
        bossRepository.login(username, password);
    }

    public void logout(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Invalid username.");
        }
        bossRepository.logout(username);
    }

    public List<Employee> getOnlineEmployees(Boss boss) {
        if (boss == null || boss.getId() <= 0) {
            throw new IllegalArgumentException("Invalid boss.");
        }
        return bossRepository.getOnlineEmployees(boss.getId());
    }

    public void assignTask(Boss boss, Employee employee, String description) {
        if (boss == null || employee == null || description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Invalid task assignment.");
        }
        Task task = new Task(description, "NOT_STARTED", boss.getId(), employee.getId(), LocalDateTime.now(), null);
        bossRepository.assignTask(task);
    }
}
