package com.friendsfinder.vkontakte.servlets;

import com.friendsfinder.finder.Search;
//import com.friendsfinder.vkontakte.VKGraph;
import com.friendsfinder.vkontakte.VKProfile;
import com.friendsfinder.vkontakte.servlets.MainMenu;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sections extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO проверки!
        request.setCharacterEncoding("windows-1251");

        String sec = "";
        if (request.getParameter("about") != null) sec += "about,";
        if (request.getParameter("careers") != null) sec += "careers,";
        if (request.getParameter("wall") != null) sec += "wall,";
        if (request.getParameter("interests") != null) sec += "interests";
        if (sec.equals("")) {
            response.sendRedirect("/welcomepage");
        }
        else {
            HttpSession session = request.getSession();
            boolean needGraph = (boolean) session.getAttribute("needgraph");

            //если пользователь использует фб или ок, посылаем на первую страницу
            //if (session.getAttribute("net") != "fb" && session.getAttribute("net") != "ok") {
            VKProfile me = ((VKProfile)session.getAttribute("me"));

            //    check(me.getFriendsprofiles());

            try {
                String words = (request.getParameter("keyword")).toLowerCase();
                Search search = new Search(words, sec);
                search.getFriends(me);
                ArrayList<VKProfile> friends = new ArrayList<>();
//                  //  System.out.println("ME FROM SECTIONS: " + me.toString());
//                    for(int i = 0; i < me.getFriendsprofiles().size(); ++i){
//                        search.getFriends(me.getFriendsprofiles().get(i));
//                      //  friends.addAll(array);
//                    }
                friends.addAll(search.getfinallyFriends());
                friends.sort(VKProfile.COMPARE_BY_DEPTH);
                session.setAttribute("friends", friends);

                for (int i = 0; i < friends.size(); ++i) {
                    System.out.println(friends.get(i).getProfileInfo().get("first_name") + " " + friends.get(i).getProfileInfo().get("last_name"));
                }

            } catch (Exception e) {
                log.log(Level.SEVERE, "Exeption: ", e);
            }
        }
        response.sendRedirect("/findpage");
    }

    private void check(ArrayList<VKProfile> fr){
        if (fr!=null) {
            for (int i = 0; i < fr.size(); ++i) {
                System.out.println("Друг глубины " + fr.get(i).getDepth() + ", по номеру " + i + ": " +
                        fr.get(i).getProfileInfo().get("first_name") + " " +
                        fr.get(i).getProfileInfo().get("last_name")  + " " +
                        fr.get(i).getProfileInfo().get("id"));
                check(fr.get(i).getFriends());
            }
        }
    }
    //  }
    private static Logger log = Logger.getLogger(MainMenu.class.getName());
}

