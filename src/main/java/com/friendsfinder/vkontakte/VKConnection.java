package com.friendsfinder.vkontakte;

import com.friendsfinder.api.Page;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VKConnection {

    public static final String VK_APP_ID = "6409550";
    public static final String VK_APP_SECRET = "66hzFV9JTXBhBcWhgFEN";
    public static final String REDIRECT_URI = "http://localhost:8080/vkhome";
    public static String access_token = "";

    public VKConnection() {
        userID = ""; access_token = "";
    }

    /**
     * Статический метод для получения кода
     * @return ссылка с параметром-кодом
     */
    public static String getVKAuthCodeUrl() {
        String vkLoginUrl = "";
        try {
            vkLoginUrl = "https://oauth.vk.com/authorize?" + "client_id="
                    + VKConnection.VK_APP_ID + "&redirect_uri="
                    + URLEncoder.encode(VKConnection.REDIRECT_URI, "UTF-8")
                    + "&response_type=code"
                    + "&scope=email,public_profile,user_friends"
                    + "&v=5.73";
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
            log.log(Level.SEVERE,"Exeption vklogin url: ", e);
        }
        return vkLoginUrl;
    }

    /**
     * Метод для обмена кода на access_token
     * @param code
     */
    public static void getAccessToken(String code) throws JSONException {
        if ("".equals(access_token)) {
            access_token = Page.GetContent(VKConnection.getVKAuthTokenUrl(code));
            log.info(access_token);

            if (access_token == "") {
                try {
                    throw new Exception("ERROR: Access Token is empty");
                } catch (Exception ex) {
                    log.log(Level.SEVERE, "Exception: ", ex);
                }
            }
            JSONObject json = new JSONObject(access_token);
            access_token = json.getString("access_token");
            userID = (json.get("user_id")).toString();
            System.out.println("access token from json: " + access_token);
        }
        //return access_token;
    }

    /**
     * Метод для получения id авторизованного пользователя
     */
    public static String getUserID(){
        return userID;
    }

    private static String getVKAuthTokenUrl(String code) {
        String vkGraphUrl = "";
        try {
            vkGraphUrl = "https://oauth.vk.com/access_token?"
                    + "client_id=" + VKConnection.VK_APP_ID + "&redirect_uri="
                    + URLEncoder.encode(VKConnection.REDIRECT_URI, "UTF-8")
                    + "&client_secret=" + VK_APP_SECRET + "&code=" + code;
        } catch (UnsupportedEncodingException e) {
            log.log(Level.SEVERE,"Exeption in preparing auth token url: ", e);
        }
        return vkGraphUrl;
    }

    private static String userID = "";

    private static Logger log = Logger.getLogger(VKConnection.class.getName());
}
