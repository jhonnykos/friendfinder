package com.friendsfinder.api;

import com.friendsfinder.vkontakte.servlets.MainMenu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Page {

    /**
     * Метод, который возвращает содержание страницы
     * @param url - адрес страницы
     * @return весь контент в String
     */
    public static String GetContent(String url){
        String info = "";
        try {
            //   System.out.println("ДО: " + url.toString());
            //  url = URLEncoder.encode(url, "utf-8");
            URL u = new URL(url);
            //   System.out.println("ПОСЛЕ: " + u.toString());
            URLConnection c = u.openConnection();
            log.info(c.toString());

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    c.getInputStream()));
            String inputLine;
            StringBuffer b = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine + "\n");
            in.close();
            info = b.toString();

        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR in getting VK Graph data.", e);
        }
        return info;
    }
    private static Logger log = Logger.getLogger(MainMenu.class.getName());

}
