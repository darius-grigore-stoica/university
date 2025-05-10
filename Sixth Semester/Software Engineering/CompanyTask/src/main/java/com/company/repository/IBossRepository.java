package com.company.repository;

import com.company.model.Boss;
import com.company.model.Employee;
import com.company.model.Task;

import java.util.List;

public interface IBossRepository extends IRepository<Boss>{
    void assignTask(Task task);
    List<Employee> getOnlineEmployees(int boss_id);

    public void login(String username, String password);
    public void logout(String username);
}
