package com.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "authorizationPage", urlPatterns = "/authorization")
public class AuthorizationPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><meta charset=\"UTF-8\"></head>" +
                "<body>" +
                "<form method = 'POST'> " +
                "<label><p>Имя пользователя ---- <input type=\"text\" name=\"name\" /></p></label>" +
                "<label><p>Пароль ------------------ <input type=\"text\" name=\"password\" /></p></label>" +
                "<p><input type=\"submit\" value=\"авторизироваться\"></p>" +
                "</form>" +
                "</body></html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String userData = name + " " + password;
        if(!name.equals("") && !password.equals("")){
            try {
                HttpSession session = req.getSession();
                List<String> lines = Files.readAllLines(Paths.get(DataBase.filePath));
                for (String line : lines) {
                    if (line.equals(userData)) {
                        if(session.getAttribute("name") == null && session.getAttribute("password") == null){
                            session.setAttribute("name", name);
                            session.setAttribute("password", password);
                            Cookie cookie1 = new Cookie("name",name);
                            Cookie cookie2 = new Cookie("password",password);
                            cookie1.setMaxAge(24 * 60 * 60);
                            cookie2.setMaxAge(24 * 60 * 60);
                            resp.addCookie(cookie1);
                            resp.addCookie(cookie2);
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/main-page");
    }
}
