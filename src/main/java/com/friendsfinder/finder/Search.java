package com.friendsfinder.finder;

import com.friendsfinder.api.ImageRecognition;
import com.friendsfinder.api.WordTranslation;
import com.friendsfinder.vkontakte.PostWall;
import com.friendsfinder.vkontakte.VKGraphMethods;
import com.friendsfinder.vkontakte.servlets.MainMenu;
import com.friendsfinder.vkontakte.VKProfile;
import org.json.JSONException;

import java.io.IOException;
import java.util.regex.Pattern;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Search {

    public Search(String str, String sections) {
        this.friends = new ArrayList<VKProfile>();
        this.wordforms = new ArrayList<String>();
        this.sections = sections;
        this.ids = new ArrayList<String>();
        this.words = (str).split(regexkey);
        System.out.println("WORDS: " + words.toString());
        getWordforms();
    }

    private void getWordforms() {
        for (int i = 0; i < words.length; ++i) {
            try {
                wordforms.addAll(Morfology.getForms(words[i]));
                System.out.println("WORDSFORMS: " + wordforms.toString());
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception in morfoloy", e);
            }
        }
    }

    public void getFriends(VKProfile profile) {

        Map<String, String> info = profile.getProfileInfo();
        System.out.println("Checking friend: " + info.get("first_name") + " " + info.get("last_name") + " " + info.get("id"));
        //    System.out.println("info from profile: " + info.toString());
        Set<String> keys = info.keySet();
        Boolean flag = false;
        for (String key : keys) { //идем по секциям текущего профиля
            if (sections.contains(key)) {
                String infofromSection = info.get(key);
                VKGraphMethods.getTypos(infofromSection);
                if (checktext(infofromSection)) {
                    profile.addSection(key);
                    flag = true;
                    System.out.println("Найдено в инфо");
                }
            }
        }

        if (sections.contains("wall")) {
            //  System.out.println("Зашли в стену");
            ArrayList<PostWall> wall = profile.getWall();
            if (wall != null) {
                System.out.println("Стена есть");
                for (int i = 0; i < wall.size(); ++i) {
                    if (checktext(wall.get(i).getText())) {
                        profile.addSection("wall");
                        flag = true;
                        System.out.println("Найдено на стене текстом");
                    }

                    ArrayList<String> photourls = wall.get(i).getPhotosurl();
                    ArrayList<String> wordsbyphotos = new ArrayList<>();
                    try {
                        ArrayList<String> tags = ImageRecognition.getTagsbyAll(photourls);
                        if (tags != null) {
                            wordsbyphotos = WordTranslation.getTranslate(tags);

                            if (checktext(wordsbyphotos.toString())) {
                                System.out.println("WORDS TO STRING!: " + wordsbyphotos.toString());
                                profile.addSection("wall");
                                flag = true;
                                System.out.println("Найдено на стене фото");
                            }
                        }

                        ArrayList<String> audios = wall.get(i).getAudio();
                        if (checktext(audios.toString())) {
                            System.out.println("AUDIOS TO STRING!: " + wordsbyphotos.toString());
                            profile.addSection("wall");
                            flag = true;
                            System.out.println("Найдено на стене аудио");
                        }
                    } catch (JSONException e) {
                        log.log(Level.SEVERE, "Error in imagerecognition IO", e);
                    }
                }
            } else System.out.println("Стены нет((");
        }

        if (flag && (!ids.contains(info.get("id")))) {
            System.out.println("FRIEND " + profile.getProfileInfo().get("first_name") + " " + profile.getProfileInfo().get("last_name"));
            this.friends.add(profile);
            ids.add(info.get("id"));
        }


        if (profile.getFriends() != null) {
            for (int i = 0; i < profile.getFriends().size(); ++i) { // идем по друзьям
                getFriends(profile.getFriends().get(i));
            }
        }

        //System.out.println("LIST SIZE = " + list.size());
        // return list;
    }

    private boolean checktext(String infofromSection) {
        infofromSection = infofromSection.toLowerCase();
        String[] text = infofromSection.split(regex);
        try {
            for (int j = 0; j < text.length; ++j) { //проходим по каждому слову текста
                ArrayList textForms = Morfology.getForms(text[j]);
                for (int k = 0; k < textForms.size(); ++k) {
                    if (wordforms.contains(textForms.get(k))) {
                        return true;
                    }
                }
            }

        } catch (Exception e) {
            log.log(Level.SEVERE, "Morfology exception", e);
        }
        return false;
    }

    public ArrayList<VKProfile> getfinallyFriends(){
        return this.friends;
    }

    final String regex = "[,.!;:?\\s]+";
    final String regexkey = "[,\\s]+";


    private ArrayList<VKProfile> friends;
    private String sections;
    private String[] words;
    ArrayList<String> wordforms;
    ArrayList<String> ids;

    private static Logger log = Logger.getLogger(MainMenu.class.getName());


    public static void main(String[] args) throws Exception {

        // ArrayList<String> array = getForms("leg");
//      Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        String regex = "[,.!;:?\\s]+";

        String regexkey = "[,\\s]+";
        String s = "с днем-рождения, котики";

        String[] st = s.split(regexkey);
        for (int i = 0; i < st.length; ++i) {
            System.out.println(st[i]);
        }
    }
}
