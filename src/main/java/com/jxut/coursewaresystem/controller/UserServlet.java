package com.jxut.coursewaresystem.controller;

import com.jxut.coursewaresystem.entity.User;
import com.jxut.coursewaresystem.service.impl.UserServiceImpl;
import com.jxut.coursewaresystem.service.UserService;
import com.jxut.coursewaresystem.util.PageBean;

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
                case "toAdd":
                    toAdd(request, response);
                    break;

                case "toUpdate":
                    toUpdate(request, response);
                    break;

                case "queryByRealname":
                    queryByRealname(request, response);
                    break;

                case "view":
                    view(request, response);
                    break;

                case "delete":
                    delete(request, response);
                    break;

                case "list":
                    list(request, response);
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

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/userAdd.jsp").forward(request, response);
    }

    private void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/userUpdate.jsp").forward(request, response);
    }

    private void queryByRealname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String realname = request.getParameter("realname");
        List<User> users = userService.getUsersByRealname(realname);

        request.setAttribute("userList", users);
        request.setAttribute("realname", realname);  // 将搜索关键词传回页面
        request.getRequestDispatcher("/userList.jsp").forward(request, response);
    }

    private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/userView.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(id);
        response.sendRedirect("user"); // 删除后刷新列表
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNum = 1;
        int pageSize = 6; // 每页显示的记录数

        try {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        } catch (NumberFormatException ignored) {

        }

        // 抽离分页逻辑到 PageBean
        PageBean<User> page = userService.getUsersByPage(pageNum, pageSize);

        request.setAttribute("userList", page.getItems());
        request.setAttribute("totalPages", page.getTotalPages());
        request.setAttribute("currentPage", page.getCurrentPage());

        request.getRequestDispatcher("/userList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 防止Post中文乱码
        String action = request.getParameter("action");

        try {
            switch (action != null ? action : "list") {
                // ... 其他 case
                case "login":
                    login(request, response);
                    break;

                case "add":
                    add(request, response);
                    break;

                case "update":
                    update(request, response);
                    break;

                case "list":
                    list(request, response);
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

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");
            if (idStr == null || idStr.trim().isEmpty()) {
                throw new ServletException("用户ID不能为空");
            }

            User user = new User();
            user.setId(Integer.parseInt(idStr)); // 确保id不为空
            user.setRealname(request.getParameter("realname"));
            user.setSex(request.getParameter("sex"));
            user.setBirthday(request.getParameter("birthday"));
            user.setTel(request.getParameter("tel"));
            user.setAddress(request.getParameter("address"));
            user.setType(request.getParameter("type"));

            boolean success = userService.updateUser(user);
            if (success) {
                response.sendRedirect("user?action=list");
            } else {
                request.setAttribute("error", "更新用户失败");
                request.getRequestDispatcher("/userUpdate.jsp?id=" + user.getId()).forward(request, response);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("用户ID必须是数字", e);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String realname = request.getParameter("realname");
        String password = request.getParameter("password");
        String birthday = request.getParameter("birthday");
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");
        String type = request.getParameter("type");

        User user = new User(username, realname, password, birthday, sex, address, tel, type);

        userService.addUser(user);
        response.sendRedirect("user"); // 添加后刷新列表
    }
}
