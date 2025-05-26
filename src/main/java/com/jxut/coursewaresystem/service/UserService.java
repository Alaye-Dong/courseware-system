package com.jxut.coursewaresystem.service;


import com.jxut.coursewaresystem.entity.User;

import java.util.List;

public interface UserService {
    boolean login(String username, String password);
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
    User getUserByName(String username);
    User getUserById(int id);
    List<User> getAllUsers();
}
