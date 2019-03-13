package com.friendsfinder.facebook;

import org.json.JSONException;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet
public class MainMenu extends HttpServlet {

private static final long serialVersionUID = 1L;
private String code="";

    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        code = req.getParameter("code");
        if (code == null || code.equals("")) {
            throw new RuntimeException(
                    "ERROR: Didn't get code parameter in callback.");
        }
        else{
            log.info("code = " + code);
        }
        FBConnection fbConnection = new FBConnection();
        String accessToken = null;
        try {
            accessToken = fbConnection.getAccessToken(code);
        } catch (JSONException e) {
            log.log(Level.SEVERE, "Exeption", e);
        }

        FBGraph fbGraph = new FBGraph(accessToken,"");
        Map<String,String> profile = fbGraph.getFBCurrentUser();
        System.out.println();
        HttpSession session = req.getSession();
        String name = profile.get("first_name");
        System.out.println("PROFILE = "+profile.toString());

        session.setAttribute("name", name);
        session.setAttribute("net", "fb");
        res.sendRedirect("/fbwarning.jsp");
    }

    private static Logger log = Logger.getLogger(com.friendsfinder.vkontakte.servlets.MainMenu.class.getName());

}
