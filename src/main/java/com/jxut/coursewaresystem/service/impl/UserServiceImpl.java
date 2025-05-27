package com.jxut.coursewaresystem.service.impl;


import com.jxut.coursewaresystem.dao.impl.UserDaoImpl;
import com.jxut.coursewaresystem.dao.UserDao;
import com.jxut.coursewaresystem.entity.User;
import com.jxut.coursewaresystem.service.UserService;
import com.jxut.coursewaresystem.util.PageBean;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public boolean login(String username, String password) {
        return userDao.checkUser(username, password);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.queryUserByName(username);
    }

    @Override
    public User getUserById(int id) {
        return userDao.queryUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.queryAllUsers();
    }

    @Override
    public int countAllUsers() {
        return userDao.countAllUsers();
    }

    @Override
    public PageBean<User> getUsersByPage(int pageNum, int pageSize) {
        int totalUsers = countAllUsers();
        int start = (pageNum - 1) * pageSize;
        List<User> users = userDao.queryUsersByPage(start, pageSize);
        return new PageBean<>(pageNum, pageSize, totalUsers, users);
    }
}
