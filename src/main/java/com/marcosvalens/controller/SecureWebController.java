package com.marcosvalens.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "secureWebController", urlPatterns = "/secureWeb")
public class SecureWebController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("secret",true);
        req.setAttribute("secretWord","SecretWord...Shhh...");
        req.getRequestDispatcher("superSecureWeb.jsp").forward(req,resp);
    }
}
