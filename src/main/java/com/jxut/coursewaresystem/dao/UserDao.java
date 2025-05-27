package com.jxut.coursewaresystem.dao;

import com.jxut.coursewaresystem.entity.User;

import java.util.List;

public interface UserDao {
    boolean checkUser(String username, String password);

    void addUser(User user);

    void deleteUser(int id);

    boolean updateUser(User user);

    User queryUserById(int id);

    List<User> queryUsersByRealname(String realname);

    int countAllUsers();

    List<User> queryUsersByPage(int start, int pageSize);
}
