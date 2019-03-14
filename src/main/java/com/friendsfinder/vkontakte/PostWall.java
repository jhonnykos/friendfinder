package com.friendsfinder.vkontakte;

import java.util.ArrayList;

public class PostWall {

    public PostWall(String id) {
        photosurl = new ArrayList<>();
        audio = new ArrayList<>();
        this.id = id;
        text = "";
    }

    public void addPhoto(String url){
        this.photosurl.add(url);
    }

    public void addText(String text){
        this.text += text +";";
    }

    public void addAudio(String audio){
        this.audio.add(audio);
    }

    public String getText(){
        return this.text;
    }

    public String getId(){
        return this.id;
    }

    public ArrayList<String> getAudio() {
        return audio;
    }

    public ArrayList<String> getPhotosurl() {
        return photosurl;
    }

    private String id;
    private String text;
    private ArrayList<String> photosurl;
    private ArrayList<String> audio;
}
