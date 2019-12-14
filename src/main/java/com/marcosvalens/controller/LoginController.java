package com.marcosvalens.controller;

import com.marcosvalens.dao.SchoolDao;
import com.marcosvalens.dao.UserDao;
import com.marcosvalens.model.Rol;
import com.marcosvalens.model.School;
import com.marcosvalens.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "loginController", urlPatterns = "/loginForm")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<School> schools = SchoolDao.getInstance().getAll();
        req.setAttribute("schools",schools);
        req.getRequestDispatcher("loginForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String pwd = req.getParameter("pwd");
        String schoolId = req.getParameter("schoolId");
        User user = UserDao.getInstance().authenticated(userName, pwd, schoolId);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("userName", user.getUserName());
            if (user.getRol().equals(Rol.ADMIN)) {
                session.setAttribute("admin", "true");
            }
            session.setAttribute("authenticated", "true");
            session.setAttribute("lastActivity", LocalDateTime.now());
            resp.sendRedirect("users");
        } else {
            req.setAttribute("errorValidation", true);
            List<School> schools = SchoolDao.getInstance().getAll();
            req.setAttribute("schools",schools);
            req.getRequestDispatcher("loginForm.jsp").forward(req, resp);
        }
    }
}
