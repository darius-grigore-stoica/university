package org.example.interfaces;

import org.example.IRepository;
import org.example.User;

public interface IUserRepository extends IRepository<User> {
    boolean login(String username, String password);
}
