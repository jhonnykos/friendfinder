package com.friendsfinder.vkontakte;

import com.friendsfinder.api.Page;
import com.friendsfinder.vkontakte.servlets.Parameters;
import com.friendsfinder.vkontakte.servlets.MainMenu;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VKGraphMethods {

    /**
     * Метод, который возвращает список id всех друзей текущего пользователя
     */
    public static ArrayList<String> getFriendsIds(String ID, int offset) {
        ArrayList<String> friendsids = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            String friendscontent = Page.GetContent(getVKGraphFriends(ID, offset));
            log.info("friends = " + friendscontent);
            try {
                JSONObject json = new JSONObject(friendscontent);

                if (json.has("error")) {
                    json = (JSONObject) json.get("error");
                    if (json.getInt("error_code") == 6) {
                        Thread.sleep(1000);
                        continue;
                    } else return null;
                }

                json = (JSONObject) json.get("response");
                JSONArray ids = json.getJSONArray("items");
                for (int i = 0; i < ids.length(); ++i) {
                    friendsids.add((ids.get(i)).toString());
                }
                flag = false;

            } catch (JSONException e) {
                log.log(Level.SEVERE, "ERROR in parsing VK Graph data map wall: ", e);
                return null;
            } catch (InterruptedException e) {
                log.log(Level.SEVERE, "ERROR in parsing VK Graph data map wall thread: ", e);
            }
        }
        return friendsids;
    }




    /**
     * Метод для заполнения поля-отображения vkProfile с основной информации на странице пользователя
     * id, about, first_name, last_name, career, interests
     */
    public static ArrayList<VKProfile> getMapfromUser(ArrayList<String> friends, String userinfoparams) {
        boolean flag = true;
        ArrayList<VKProfile> friendsprofiles = new ArrayList<>();
        while (flag) {
            String userinfo = Page.GetContent(VKGraphMethods.getVKGraphUser(friends, userinfoparams));

            try {
                JSONObject json = new JSONObject(userinfo);

                if (json.has("error")) {
                    json = (JSONObject) json.get("error");
                    if (json.getInt("error_code") == 6) {
                        Thread.sleep(1000);
                        log.info("userinfo = " + userinfo);
                        continue;
                    } else return null;
                }

                JSONArray array = (JSONArray) json.get("response");
                for (int i = 0; i < array.length(); ++i) {
                    json = (JSONObject) array.get(i);
                    if (json.has("can_access_closed") && json.getBoolean("can_access_closed") && !json.has("error") && !json.getString("first_name").equals("DELETED")) {
                        Map profileInfo = new HashMap();
                        profileInfo.put("id", json.get("id").toString());
                        profileInfo.put("first_name", json.getString("first_name"));
                        profileInfo.put("last_name", json.getString("last_name"));
                        if (json.has("about")) profileInfo.put("about", json.getString("about"));
                        if (json.has("photo_200")) profileInfo.put(("photo"), json.get("photo_200"));
                        if (json.has("city")) profileInfo.put(("city"), (json.getJSONObject("city")).get("title"));
                        if (json.has("careers")) {
                            JSONArray careers = json.getJSONArray("career");
                            String career = "";
                            for (int j = 0; j < careers.length(); ++j) {
                                career += ((JSONObject) careers.get(j)).getString("company") + ",";
                            }
                            profileInfo.put("career", career);
                        }
                        if (json.has("interests")) profileInfo.put("interests", json.getString("interests"));
                        VKProfile profile = new VKProfile(profileInfo);
                        friendsprofiles.add(profile);
                        flag = false;
                    }
                }
            } catch (JSONException e) {
                log.log(Level.SEVERE, "ERROR in parsing VK Graph data: " + e.getMessage());
                return null;
            } catch (InterruptedException e) {
                log.log(Level.SEVERE, "ERROR in parsing VK Graph data: " + e.getMessage());
            }
        }
        return friendsprofiles;
    }



    /**
     * Метод для заполнения поля-отображения vkProfile со стены текущего пользователя
     *  wall
     */
    public static ArrayList<String> getMapfromWall(String ID) {

        boolean flag = true;
        ArrayList<String> posts = new ArrayList<>();
        while (flag) {
            String wall = Page.GetContent(VKGraphMethods.getVkGraphWall(ID));
            //log.info("wall = " + wall);

            try {
                JSONObject json = new JSONObject(wall);

                if (json.has("error")) {
                    json = (JSONObject) json.get("error");
                    if (json.getInt("error_code") == 6) {
                        Thread.sleep(1000);
                        log.info("wall = " + wall);
                        continue;
                    } else return null;
                }

                json = (JSONObject) json.get("response");
                JSONArray array = (JSONArray) json.get("items");
                for (int j = 0; j < array.length(); ++j) {
                    JSONObject post = (JSONObject) array.get(j);
                    posts.add(post.getString("text"));
                    if (post.has("copy_history")) {
                        JSONArray array_ = (JSONArray) post.get("copy_history");
                        for (int i = 0; i < array_.length(); ++i) {
                            JSONObject repost = (JSONObject) array_.get(i);
                            posts.add(repost.getString("text"));
                        }
                    }
                }
                flag = false;
            } catch (JSONException e) {
                log.log(Level.SEVERE, "ERROR in parsing VK Graph data map wall: ", e);
            }
            catch (InterruptedException e) {
                log.log(Level.SEVERE, "ERROR in parsing VK Graph data map wall: ", e);
            }
        }
        return posts;
    }



    //    public static void getTypos(ArrayList<String> texts){
//        String typos = Page.GetContent(VKGraphMethods.getTypo(texts));
//        System.out.println(typos);
//    }
    public static void getTypos(String text) {
        String typos = Page.GetContent(VKGraphMethods.getTypo(text));
        System.out.println("TYPOS= " + typos);
    }




    public static ArrayList<VKProfile> InitializewithChecking(String ID, ArrayList<VKProfile> friends, String userinfoparams) {
        ArrayList<VKProfile> profilestoGet = new ArrayList<>();
        ArrayList<String> idstoGet = new ArrayList<>();
        int count = (int)Math.ceil(Parameters.LENGTH * 1.5);
        int offset = 0;
        do {
            idstoGet = VKGraphMethods.getFriendsIds(ID, offset * count);
            //   System.out.println("ИД ДО: " + idstoGet.toString());

            for (Iterator<String> it = idstoGet.iterator(); it.hasNext(); ) {
                if (VKGraphMethods.ids.contains(it.next())) {
                    it.remove();
                }
            }
            //    System.out.println("ИД ПОСЛЕ: " + idstoGet.toString());


            try {
                profilestoGet = VKGraphMethods.getMapfromUser(idstoGet,userinfoparams);

                if (profilestoGet != null && friends.size() < Parameters.LENGTH) {
                    int min = Math.min(profilestoGet.size(), Parameters.LENGTH - friends.size());
                    for (int i = 0; i < min; ++i) {
                        profilestoGet.get(i).setProfileWall();
                        friends.add(profilestoGet.get(i));
                    }

                    int lastindex = idstoGet.indexOf((profilestoGet.get(min-1)).getProfileID());

                    for(int i = 0; i <= lastindex; ++i) {
                        VKGraphMethods.ids.add(idstoGet.get(i));
                    }
                    offset++;

                    // System.out.println("OFFSET " + idstoGet.toString() + ": " + offset);
                    // System.out.println(VKGraphMethods.ids.toString());
                }
            } catch (NullPointerException ex) {
                log.log(Level.SEVERE, "Nullpointer in initialize", ex);
            }
        } while (Parameters.LENGTH != friends.size());

        return friends;
    }





    /**
     * Далее перечислены методы для составления нужных http-запросов
     */
    private static String getVkGraphWall(String ID) {
        String wallurl = "https://api.vk.com/method/wall.get?" +
                "owner_id=" + ID +
                "&v=5.89" +
                "&access_token=" + VKConnection.access_token +
                "&count=10";
        return wallurl;
    }

    private static String getVKGraphUser(ArrayList<String> friends, String fields) {
        String userurl = "https://api.vk.com/method/users.get?" +
                "v=5.89" +
                "&user_ids=";
        for(int i=0; i< friends.size(); ++i){
            userurl+= friends.get(i);
            if(i!=friends.size()-1) userurl+=",";
        }
        userurl +=  "&fields=" + fields +
                "&access_token=" + VKConnection.access_token;
        return userurl;
    }

//    private static String getTypo(ArrayList<String> texts) {
//        String typourl = "https://speller.yandex.net/services/spellservice.json/checkTexts?";
//        String texturl = "";
//        for (int i = 0; i < texts.size(); ++i) {
//            texturl += "text=";
//            texturl += texts.get(i) + "&";
//        }
//        typourl += texturl;
//        typourl += "options=518" +
//                "&lang=ru";
//        return typourl;
//    }

    private static String getTypo(String text){
        String typourl = "https://speller.yandex.net/services/spellservice.json/checkText?";
//            String texturl = "";
//            for (int i = 0; i < texts.size(); ++i) {
//                texturl += "text=";
//                texturl += texts.get(i) + "&";
//            }
        try {
            typourl += "text=" + URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.log(Level.SEVERE, "Error in typo", e);
        }
        typourl += "&options=518";
        return typourl;
    }

    private static String getVKGraphFriends(String ID, int offset) {
        double count = Math.ceil(Parameters.LENGTH*1.5);
        String frineds = "https://api.vk.com/method/friends.get?" +
                "v=5.89" +
                "&user_id=" + ID +
                "&order" + "hints" +
                "&count=" + count +
                "&offset=" + offset +
                "&access_token=" + VKConnection.access_token;
        return frineds;
    }

    private static Logger log = Logger.getLogger(MainMenu.class.getName());
    public static final String USERINFO = "about,career,interests,photo_200,city";
    public static ArrayList<String> ids = new ArrayList<>();

}
