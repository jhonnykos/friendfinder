package com.friendsfinder.api;

import com.friendsfinder.vkontakte.servlets.MainMenu;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Logger;

public class WordTranslation {

    public static ArrayList<String> getTranslate(ArrayList<String> words) throws JSONException {
        ArrayList<String> translate = new ArrayList<>();
        String url = WordTranslation.TRANSLATION_URL;
        for (int i = 0; i < words.size(); ++i) {
            if(words.get(i) != null && words.get(i) !="") {
                url += "&text=" + words.get(i);
            }
        }
        if(url.indexOf("&text") != -1) {
            String translatecontent = Page.GetContent(url);
            JSONObject json = new JSONObject(translatecontent);
            JSONArray array = (JSONArray) json.get("text");
            for (int i = 0; i < array.length(); ++i) {
                translate.add(array.get(i).toString());
//            System.out.println(array.get(i).toString());
            }
            return translate;
        }

        return null;
    }


    static final String TRANSLATION_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?lang=en-ru&key="
            + WordTranslation.TRANSLATION_KEY;
    static final String TRANSLATION_KEY ="trnsl.1.1.20190314T005541Z.b1240de379fab445.0962b40450b5d16046ee6a01fbe3ae4dbdece5c6";
    private static Logger log = Logger.getLogger(MainMenu.class.getName());

}
