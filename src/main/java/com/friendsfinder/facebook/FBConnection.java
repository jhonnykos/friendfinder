package com.friendsfinder.facebook;

import com.friendsfinder.api.Page;
import com.friendsfinder.vkontakte.servlets.MainMenu;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FBConnection {
    //    public static String myUrl="http://localhost:8080/";
    public static String myUrl="https://friendsfindersocial.herokuapp.com/";
    public static final String FB_APP_ID = "404496400014228";
    public static final String FB_APP_SECRET = "0459c8be0dfb451c4fdd9e87d3d09690";
    public static final String REDIRECT_URI = FBConnection.myUrl+"fbhome";


    public FBConnection() {
        this.accessToken = "";
    }

    public String getFBAuthCodeUrl() {
        String fbLoginUrl = "";
        try {
            fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
                    + FBConnection.FB_APP_ID + "&redirect_uri="
                    + URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
                    + "&response_type=code"
                    + "&scope=email,public_profile,user_friends";
        } catch (UnsupportedEncodingException e) {
            log.log(Level.SEVERE, "Exeption", e);
        }
        return fbLoginUrl;
    }

    public String getAccessToken(String code) throws JSONException {
        if ("".equals(accessToken)) {
            accessToken = Page.GetContent(getFBAuthTokenUrl(code));

            JSONObject json = new JSONObject(accessToken);
            System.out.println("AT = " + accessToken);
            accessToken = json.getString("access_token");
            if(accessToken == ""){
                throw new RuntimeException("ERROR: Access Token is empty");
            }
        }
        return accessToken;
    }

    private String getFBAuthTokenUrl(String code) {
        String fbGraphUrl = "";
        try {
            fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
                    + "client_id=" + FBConnection.FB_APP_ID + "&redirect_uri="
                    + URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
                    + "&client_secret=" + FB_APP_SECRET + "&code=" + code;
        } catch (UnsupportedEncodingException e) {
            log.log(Level.SEVERE, "Exeption", e);
        }
        return fbGraphUrl;
    }

    private String accessToken;
    private static Logger log = Logger.getLogger(MainMenu.class.getName());
}
