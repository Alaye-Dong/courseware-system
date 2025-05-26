package com.jxut.coursewaresystem.dao.Impl;

import com.jxut.coursewaresystem.dao.UserDao;
import com.jxut.coursewaresystem.entity.User;
import com.jxut.coursewaresystem.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean checkUser(String username, String password) {
        boolean result = false;

        String sql = "SELECT COUNT(*) FROM t_user WHERE username = ? AND password = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User queryUserByName(String username) {
        return null;
    }

    @Override
    public User queryUserById(int id) {
        return null;
    }

    @Override
    public List<User> queryAllUsers() {
        return Collections.emptyList();
    }
}
