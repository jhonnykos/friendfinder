package com.friendsfinder.vkontakte.servlets;

import com.friendsfinder.vkontakte.servlets.MainMenu;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class Finder extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String i = request.getParameter("i");
        //System.out.println("i from class: " + i);
        HttpSession session = request.getSession();
        session.setAttribute("number", i);
        response.sendRedirect("/profilepage");
    }
    private static Logger log = Logger.getLogger(MainMenu.class.getName());

}
