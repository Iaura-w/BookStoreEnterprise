package org.example.services;

import org.example.entity.User;

public interface UserService {

    User getUser(String username);
    void saveUser(User user);
}
