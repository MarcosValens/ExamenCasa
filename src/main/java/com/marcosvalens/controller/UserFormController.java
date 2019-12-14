package com.marcosvalens.controller;

import com.marcosvalens.dao.SchoolDao;
import com.marcosvalens.dao.UserDao;
import com.marcosvalens.model.Rol;
import com.marcosvalens.model.School;
import com.marcosvalens.model.User;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@WebServlet(name = "UserFormController", urlPatterns = "/addUser")
@MultipartConfig(location = "/tmp")
public class UserFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("userName") != null) {
            String userName = req.getParameter("userName");
            User user = UserDao.getInstance().getUser(userName);
            req.setAttribute("user", user);
            req.setAttribute("picture", true);
        }
        List<School> schools = SchoolDao.getInstance().getAll();
        req.setAttribute("schools", schools);
        req.getRequestDispatcher("userForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        User oldUser = new User();
        if (!req.getParameter("userName").equals("")){
            System.out.println(req.getParameter("userName"));
            oldUser = UserDao.getInstance().getUser(req.getParameter("userName"));
        }

        try {
            School school = SchoolDao.getInstance().getSchool(Long.parseLong(req.getParameter("school")));
            user.setSchool(school);
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setUserName(req.getParameter("userNameInput"));
        user.setPwd(req.getParameter("pwd"));
        user.setName(req.getParameter("name"));
        user.setSecondName(req.getParameter("secondName"));
        String rol = req.getParameter("rol");
        if (rol.equalsIgnoreCase(String.valueOf(Rol.ADMIN))) {
            user.setRol(Rol.ADMIN);
        } else if (rol.equalsIgnoreCase(String.valueOf(Rol.EDITOR))) {
            user.setRol(Rol.EDITOR);
        } else user.setRol(null);

        if (req.getParameter("userName").equals("")) {
            UserDao.getInstance().save(user);
        } else {
            if (req.getPart("file") != null) {
                Part file = req.getPart("file");
                String fileName = file.getSubmittedFileName();
                DiskFileItemFactory factory = new DiskFileItemFactory();
                File repository = new File("uploads/");
                factory.setRepository(repository);
                File thisFile = new File(repository, fileName);
                try (InputStream input = file.getInputStream()) {
                    if (!thisFile.exists()){
                        Files.copy(input, thisFile.toPath());
                    }
                }
                UserDao.getInstance().addPicture("uploads" + fileName, oldUser);
                UserDao.getInstance().update(user, oldUser);
            }
        }
        List<User> users = UserDao.getInstance().getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }
}
