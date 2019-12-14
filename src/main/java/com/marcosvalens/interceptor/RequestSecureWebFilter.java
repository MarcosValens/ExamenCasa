package com.marcosvalens.interceptor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@WebFilter(servletNames = {"secureWebController"})
public class RequestSecureWebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Duration duration;
        String authenticate = (String) session.getAttribute("authenticated");
        String admin = (String) session.getAttribute("admin");
        LocalDateTime lastActivity = (LocalDateTime) session.getAttribute("lastActivity");
        LocalDateTime now = LocalDateTime.now();
        if (lastActivity != null) {
            duration = Duration.between(now, lastActivity);
            long diff = Math.abs(duration.toMinutes());
            if (diff > 5) {
                session.invalidate();
                resp.sendRedirect("login");
            } else {
                session.setAttribute("lastactivity", lastActivity);
            }
        }
        if (authenticate != null && authenticate.equals("true") && admin != null && admin.equals("true")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            resp.sendRedirect("notAuthorized.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
