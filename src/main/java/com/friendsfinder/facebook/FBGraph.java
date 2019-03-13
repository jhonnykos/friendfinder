package com.friendsfinder.facebook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.friendsfinder.api.Page;
import com.friendsfinder.vkontakte.servlets.MainMenu;
import org.json.JSONException;
import org.json.JSONObject;


public class FBGraph {

    public FBGraph(String accessToken, String id)
    {
        this.accessToken = accessToken;
        this.ID = id;
        this.fbProfile = new HashMap();
        this.friends = new ArrayList<String>();
    }


    public Map<String,String> getFBCurrentUser(){
        Map profile = new HashMap();
        String url = "https://graph.facebook.com/me?fields=id,name,first_name,last_name,posts&access_token="+accessToken;
        String cucontent = Page.GetContent(url);
        try{
        JSONObject json = new JSONObject(cucontent);
        profile.put("first_name",json.getString("first_name"));
        profile.put("last_name",json.getString("last_name"));
        profile.put("id", json.getString("id"));
        profile.put("posts",(((JSONObject)json.get("posts")).getJSONArray("data")).get(0));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR in getting FB graph data. " + e);
        }
        return profile;
    }

    public Map<String,String> getGraphMap(){
        return fbProfile;
    }

   // private

    public String getFBGraph() {
        String graph = null;
        try {
            String g = "https://graph.facebook.com/me?fields=id,name,first_name,about,last_name,feed{message},work,education&access_token=" + accessToken;
            URL u = new URL(g);
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    c.getInputStream()));
            String inputLine;
            StringBuffer b = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine + "\n");
            in.close();
            graph = b.toString();
           // System.out.println("FBGraph with params" + graph);
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR in parsing FB graph data.", e);
        }
        return graph;
    }

    public Map<String, String> getGraphData(String fbGraph) {
        Map fbProfile = new HashMap();
        try {
            JSONObject json = new JSONObject(fbGraph);
//            System.out.println("id = " + json.getString("id"));
//            System.out.println("name =" + json.getString("name"));
            fbProfile.put("id", json.getString("id"));
            fbProfile.put("first_name", json.getString("first_name"));
            fbProfile.put("last_name",json.getString("last_name"));
//            System.out.println("id from map = " + fbProfile.get("id"));
//            System.out.println("name from map = " + fbProfile.get("first_name")+" "+fbProfile.get("last_name"));
        } catch (JSONException e) {
            log.log(Level.SEVERE, "ERROR in parsing FB graph data.", e);
        }
        return fbProfile;
    }

    private String getFBGraphFriends(){
        String friendsurl="";
        return friendsurl;
    }

    private String accessToken;
    private Map fbProfile;
    private ArrayList<String> friends;
    private String ID;

    private static Logger log = Logger.getLogger(MainMenu.class.getName());

}
