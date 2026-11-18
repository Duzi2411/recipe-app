package com.example.recipeapp.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        String path = request.getRequestURI();

        if (path.equals("/")
                || path.equals("/login")
                || path.equals("/register")
                || path.equals("/style.css")
                || path.equals("/error")
                || path.equals("/favicon.ico")) {
            return true;
        }

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect("/login");
            return false;
        }

        if (path.startsWith("/categories") || path.startsWith("/ingredients")) {
            String role = (String) session.getAttribute("user_role");

            if (!"ADMIN".equals(role)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }

        return true;
    }
}