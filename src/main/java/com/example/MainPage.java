package com.example;

import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "mainPage", value = "/main-page")
public class MainPage extends HttpServlet {

    private LinkedList<String> announcementBoard;

    public void init() {
        DataBase dataBase = new DataBase();
        dataBase.fileCheck();
        announcementBoard = new LinkedList<String>();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html><head><meta charset=\"UTF-8\">" +
                "<link rel='stylesheet' type='text/css' href='styles.css'>" +
                "</head>" +
                "<body>");

        HttpSession session = request.getSession();
        out.println("<h1>Доска объявлений</h1>");
        out.println("<p> <a href=\"http://localhost:8081/authorization\">войти в систему</a> </p>");

        Cookie[] cookies = request.getCookies();

        if (cookies.length > 1){
            session.setAttribute("name", cookies[0].getValue());
            session.setAttribute("password", cookies[1].getValue());
        }
        if (session.getAttribute("name") != null && session.getAttribute("password") != null) {
            out.println("<p> <a href=\"http://localhost:8081/log-out\">выйти из системы</a> </p>");
            out.println("<p> <a href=\"http://localhost:8081/add-announcement\">добавить объявление</a> </p>");
        }


        for (int i = 0;i < announcementBoard.size();i++) {
            if(i % 2 == 0){
                out.println("<h4>" + announcementBoard.get(i) + "</h4>");
            } else {
                out.println("<p>" + announcementBoard.get(i) + "</p>");
            }
        }
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String announcement = req.getParameter("announcement");
        String name = session.getAttribute("name").toString();
        if(!announcement.equals("") && name != null){
            Date current = new Date();
            announcementBoard.add(name + " " + current + " :");
            announcementBoard.add(announcement);
        }
        resp.sendRedirect("/main-page");
    }

    public void destroy() {
    }
}