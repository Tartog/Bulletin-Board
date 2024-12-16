package com.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addAnnouncement", urlPatterns = "/add-announcement")
public class AddAnnouncement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><meta charset=\"UTF-8\"></head>" +
                "<body>" +
                "<form action = '/main-page' method = 'POST'> " +
                "<label><p>Объявление --------- <input type=\"text\" name=\"announcement\" /></p></label>" +
                "<p><input type=\"submit\" value=\"опубликовать объявление\"></p>" +
                "</form>" +
                "</body></html>");
    }
}
