package com.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "logOut",urlPatterns = "/log-out")
public class LogOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        Cookie cookie1 = new Cookie("name", "");
        Cookie cookie2 = new Cookie("password", "");
        cookie1.setMaxAge(0);
        cookie2.setMaxAge(0);
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
        resp.sendRedirect("/main-page");
    }
}
