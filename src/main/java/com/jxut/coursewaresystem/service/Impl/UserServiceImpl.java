package com.jxut.coursewaresystem.service.Impl;



import com.jxut.coursewaresystem.dao.Impl.UserDaoImpl;
import com.jxut.coursewaresystem.dao.UserDao;
import com.jxut.coursewaresystem.entity.User;
import com.jxut.coursewaresystem.service.UserService;

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
}
