/* 2018  2018年9月17日  下午2:14:17
 * DbUtil.java
 * guorf
 */
package com.jxut.coursewaresystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author guorf
 * @date 2018年9月17日 下午2:14:17
 **/
public class DbUtil {
    public static void main(String[] args) {
        System.out.println(getConnection());
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
//            //5.x
//            Class.forName("com.mysql.jdbc.Driver");

//            8.x
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/practical-demo?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true",
                    "root",
                    "123456"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeAll
            (Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
