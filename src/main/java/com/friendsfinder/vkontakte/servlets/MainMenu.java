package com.friendsfinder.vkontakte.servlets;

import com.friendsfinder.vkontakte.VKConnection;
//import com.friendsfinder.vkontakte.VKGraph;
import com.friendsfinder.vkontakte.VKGraphMethods;
import com.friendsfinder.vkontakte.VKProfile;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//TODO проверить localhost
public class MainMenu extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private String code;

    public MainMenu(){
        code = "";
    }

    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();

        code = req.getParameter("code");

        //TODO добавить сообщение
        if (code == null || code.equals("")) {
            res.sendRedirect("/index");
            return;
        } else {
            log.info("code from auth = " + code);
        }

        VKProfile me = null;
        String name= "";
        try {
            VKConnection.getAccessToken(code);
            // me = (new VKGraph(VKConnection.getUserID())).getmyProfile();
            ArrayList<String> id = new ArrayList<>();
            id.add(VKConnection.getUserID());
            me = (VKGraphMethods.getMapfromUser(id,"")).get(0);
            name = me.getProfileInfo().get("first_name");
        } catch (JSONException e) {
            log.log(Level.SEVERE, "JSONExeption: ", e);
        }
        session.setAttribute("me", me);
        session.setAttribute("net", "vk");
        session.setAttribute("name", name);
        res.sendRedirect("/params");
    }

    private static Logger log = Logger.getLogger(MainMenu.class.getName());
}
