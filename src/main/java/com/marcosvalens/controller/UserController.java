package com.marcosvalens.controller;

import com.marcosvalens.dao.UserDao;
import com.marcosvalens.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "/users")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserDao.getInstance().getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("users.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserDao.getInstance().getUser(req.getParameter("userName"));
        UserDao.getInstance().delete(user);
        List<User> users= UserDao.getInstance().getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("users.jsp").forward(req,resp);
    }
}
