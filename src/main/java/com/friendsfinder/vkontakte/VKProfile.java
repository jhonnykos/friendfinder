package com.friendsfinder.vkontakte;

import com.friendsfinder.vkontakte.servlets.Parameters;
import com.friendsfinder.vkontakte.servlets.MainMenu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VKProfile {

    public VKProfile(Map<String,String> info) {
        this.info = info;
        this.ID = info.get("id");
        this.friends = new ArrayList<>();
        this.wall = new ArrayList<>();
        this.friendsids = new ArrayList<>();
        this.depth = 0;
        this.sections = "";
    }

    public void setProfileWall(){
        this.wall = VKGraphMethods.getMapfromWall(ID);
        //String [] s = null;
        // VKGraphMethods.getTypos(wall);
    }

    public void initializeFriends(int depth) throws InterruptedException {
        if(depth < Parameters.DEPTH) {

            friends = VKGraphMethods.InitializewithChecking(ID,friends,VKGraphMethods.USERINFO);

            for (int i = 0; i < friends.size(); ++i) {
                friends.get(i).setParent(this);
                friends.get(i).setDepth(depth + 1);
                friends.get(i).initializeFriends(depth + 1);
            }
        }
    }

    public Map<String,String> getProfileInfo(){
        return info;
    }

    public String getProfileID(){ return ID; }

    public ArrayList<String> getFriendsids() {
        return friendsids;
    }

    public ArrayList<VKProfile> getFriends() {
        return friends;
    }

    public ArrayList<String> getWall() {
        return this.wall;
    }

    public void cleanFriends(){
        friends = new ArrayList<>();
    }

    public void setParent(VKProfile parent){
        this.parent = parent;
    }

    public VKProfile getParent(){
        return this.parent;
    }

    public int getDepth(){
        return this.depth;
    }

    public void setDepth(int depth){
        this.depth = depth;
    }

    public void addSection(String sec){
        if(sections.equals("")) sections += ";";
        sections += sec;
    }

    public String getSections(){
        return sections;
    }

    public static  final Comparator<VKProfile> COMPARE_BY_DEPTH = new Comparator<VKProfile>() {
        public int compare(VKProfile o1, VKProfile o2) {
            return o1.getDepth() - o2.getDepth();
        }
    };

    public static ArrayList<VKProfile> getParents(VKProfile profile){
        ArrayList<VKProfile> parents = new ArrayList<>();
        while(profile.getParent()!=null){
            parents.add(profile.getParent());
            profile = profile.getParent();
        }
        return parents;
    }

    private Map<String,String> info;
    private ArrayList<String> friendsids;
    private ArrayList<VKProfile> friends;
    private ArrayList<String> wall;
    private VKProfile parent;
    private int depth;
    private String sections;
    private String ID;
    private static Logger log = Logger.getLogger(MainMenu.class.getName());
}
