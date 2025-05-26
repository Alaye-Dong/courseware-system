package com.jxut.coursewaresystem.dao.Impl;

import com.jxut.coursewaresystem.dao.UserDao;
import com.jxut.coursewaresystem.entity.User;
import com.jxut.coursewaresystem.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

    @Override
    public int countAllUsers() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM t_user";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    @Override
    public List<User> queryUsersByPage(int start, int pageSize) {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM t_user LIMIT ?, ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, start);
            pstmt.setInt(2, pageSize);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRealname(rs.getString("realname"));
                user.setSex(rs.getString("sex"));
                user.setTel(rs.getString("tel"));
                user.setType(rs.getString("type"));
                user.setBirthday(rs.getString("birthday"));
                user.setIf_valid(rs.getString("if_valid"));
                // 设置其他字段...
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

}
