package com.jxut.coursewaresystem.controller;

import com.jxut.coursewaresystem.entity.User;
import com.jxut.coursewaresystem.service.Impl.UserServiceImpl;
import com.jxut.coursewaresystem.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * action 对应 index 中的 /user?action=list
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action != null ? action : "list") {
                // ... 其他 case

                case "list":
                    int pageNum = 1;
                    int pageSize = 6; // 每页显示的记录数

                    try {
                        pageNum = Integer.parseInt(request.getParameter("pageNum"));
                    } catch (NumberFormatException ignored) {}

                    // TODO 抽离分页逻辑到 PageBean
                    List<User> userList = userService.getUsersByPage(pageNum, pageSize);
                    int totalUsers = userService.countAllUsers();
                    int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

                    request.setAttribute("userList", userList);
                    request.setAttribute("totalPages", totalPages);
                    request.setAttribute("currentPage", pageNum);

                    request.getRequestDispatcher("/userList.jsp").forward(request, response);
                    break;

                default:
                    // 默认情况下列出所有用户
                    request.getRequestDispatcher("/userList.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
