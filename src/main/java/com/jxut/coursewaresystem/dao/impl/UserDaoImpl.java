package com.jxut.coursewaresystem.dao.impl;

import com.jxut.coursewaresystem.dao.UserDao;
import com.jxut.coursewaresystem.entity.User;
import com.jxut.coursewaresystem.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean checkUser(String username, String password) {
        boolean result = false;

        String sql = "SELECT * FROM t_user WHERE username = ? AND password = ?";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
        String sql = "INSERT INTO t_user(username, password, realname, sex, address, tel, type, birthday) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // TODO 更优雅的获取字段方式，避免index硬编码匹配
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRealname());
            pstmt.setString(4, user.getSex());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getTel());
            pstmt.setString(7, user.getType());
            pstmt.setString(8, user.getBirthday());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM t_user WHERE id = ?";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, String.valueOf(id));
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        User user = new User();

        String sql = "SELECT * FROM t_user WHERE id = ?";

        try (Connection conn = DbUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRealname(rs.getString("realname"));
                user.setSex(rs.getString("sex"));
                user.setAddress(rs.getString("address"));
                user.setTel(rs.getString("tel"));
                user.setType(rs.getString("type"));
                user.setBirthday(rs.getString("birthday"));
                user.setIf_valid(rs.getString("if_valid"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public List<User> queryUsersByRealname(String realname) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM t_user WHERE realname LIKE ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + realname + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 新增的 extractUserFromResultSet 方法
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRealname(rs.getString("realname"));
        user.setSex(rs.getString("sex"));
        user.setAddress(rs.getString("address"));
        user.setTel(rs.getString("tel"));
        user.setType(rs.getString("type"));
        user.setBirthday(rs.getString("birthday"));
        user.setIf_valid(rs.getString("if_valid"));
        return user;
    }

    @Override
    public List<User> queryAllUsers() {
        return Collections.emptyList();
    }

    @Override
    public int countAllUsers() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM t_user";

        try (Connection conn = DbUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

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

        try (Connection conn = DbUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, start);
            pstmt.setInt(2, pageSize);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

}
