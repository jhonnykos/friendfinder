package com.friendsfinder.vkontakte.servlets;

import com.friendsfinder.vkontakte.VKProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parameters  extends HttpServlet {
    public static int DEPTH = 0;
    public static int LENGTH = 0;

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {

        HttpSession session = req.getSession();
        try {
            int depth = Integer.parseInt(req.getParameter("depth"));
            int length = Integer.parseInt(req.getParameter("length"));

            if (depth != Parameters.DEPTH | length != Parameters.LENGTH) {
                Parameters.DEPTH = depth;
                Parameters.LENGTH = length;
                session.setAttribute("needgraph", true);
                VKProfile me = (VKProfile) session.getAttribute("me");
                me.initializeFriends(0);

                //  proverka(me);

                session.setAttribute("me", me);
            } else {
                session.setAttribute("needgraph", false);
            }

        }catch (NumberFormatException e) {
            log.log(Level.SEVERE, "NUllformatex", e);
        } catch (InterruptedException e) {
            log.log(Level.SEVERE, "initialize", e);
        }
        res.sendRedirect("/welcomepage");
    }

//    private void proverka(VKGraph me){
//        System.out.println("СЕЙЧАС БУДЕТ ИНИЦИАЛИЗАЦИЯ ДЕРЕВА ДРУЗЕЙ!/n");
//        ArrayList<VKProfile> profilies = me.getmyProfile().getFriends();
//        System.out.println("РАЗМЕР: " + profilies.size());
//        for(int i = 0 ;i <profilies.size(); ++i) {
//            System.out.println("ИД: " + profilies.get(i).getProfileID());
//            System.out.println("ИМЯ: " + profilies.get(i).getProfileInfo().get("first_name") + " " + profilies.get(i).getProfileInfo().get("last_name"));
//            System.out.println("О СЕБЕ: " + profilies.get(i).getProfileInfo().toString());
//            System.out.println("СТЕНА: " + profilies.get(i).getWall());
//            System.out.println();
//
//            System.out.println("СЕЙЧАС БУДУТ ДРУЗЬЯ ЭТОГО ЧЕЛА");
//            ArrayList<VKProfile> pr = profilies.get(i).getFriends();
//            for (int j = 0; j < pr.size(); ++j) {
//                System.out.println("ИД: " + pr.get(j).getProfileID());
//                System.out.println("ИМЯ: " + pr.get(j).getProfileInfo().get("first_name") + " " + pr.get(j).getProfileInfo().get("last_name"));
//                System.out.println("О СЕБЕ: " + pr.get(j).getProfileInfo().toString());
//                System.out.println("СТЕНА: " + pr.get(j).getWall());
//                System.out.println();
//            }
//        }
//    }

    private static Logger log = Logger.getLogger(MainMenu.class.getName());
}
