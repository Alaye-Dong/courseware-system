package com.jxut.coursewaresystem.controller;

import com.jxut.coursewaresystem.entity.User;
import com.jxut.coursewaresystem.service.Impl.UserServiceImpl;
import com.jxut.coursewaresystem.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

// "/login" 对应JSP页面中的表单的 action
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            boolean loginSuccess = userService.login(username, password);

            if (loginSuccess) {
                // 登录成功，跳转index.jsp
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("error", "用户名或密码错误");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
    }
}
