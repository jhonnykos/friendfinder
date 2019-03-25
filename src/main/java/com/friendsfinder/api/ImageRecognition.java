package com.friendsfinder.api;

import com.friendsfinder.vkontakte.servlets.MainMenu;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageRecognition {

    public static ArrayList<String> getTags(String imageurl) throws Exception {

        URL url = new URL(ImageRecognition.IMAGERECOGNITION_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        String info = "";
        ArrayList<String> imagetags = new ArrayList<>();

        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Ocp-Apim-Subscription-Key", IMAGERECOGNITION_KEY);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        JSONObject j = new JSONObject();
        j.put("url", imageurl);
        OutputStream os = conn.getOutputStream();
        os.write(j.toString().getBytes("UTF-8"));
        os.close();
        BufferedReader br;
        try {
             br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
        }catch(IOException e){
            log.log(Level.SEVERE, "Error in imageurl "+ imageurl, e);
            return null;
        }
        catch(Exception e){
            log.log(Level.SEVERE, "Error in image: " + imageurl, e);
            return null;
        }
        StringBuffer sb = new StringBuffer();
        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            sb.append(output + "\n");
            System.out.println(output);
        }
        info = sb.toString();
        conn.disconnect();

        //TODO подумать с уверенностью в объекте
        JSONObject json = new JSONObject(info);
//        if(json.has("statusCode")) return null;

        if(json.has("statusCode")) throw new Exception("bad request");

        JSONArray tags = json.getJSONArray("tags");
        for (int i = 0; i < tags.length(); ++i) {
            String tag = ((JSONObject)(tags.get(i))).get("name").toString();
            if(!tag.contains(" ")) {
                imagetags.add(tag);
            }
        }
        return imagetags;
    }

    public static ArrayList<String> getTagsbyAll(ArrayList<String> urls){
        ArrayList tags = new ArrayList();
        for(int i = 0; i < urls.size(); ++i){
            try {
                ArrayList<String> tagsi = ImageRecognition.getTags(urls.get(i));
                if(tagsi!=null) tags.addAll(tagsi);
            } catch (JSONException e) {
                log.log(Level.SEVERE, "Error in imagerecognition IO");
                continue;
            } catch (IOException e) {
                log.log(Level.SEVERE, "Error in imagerecognition JSON");
                continue;
            } catch (Exception e) {
                log.log(Level.SEVERE, "bad request");
                continue;
            }
        }
        return tags;
    }

    public static void main(String[] args) {
        String url="https://bipbap.ru/wp-content/uploads/2017/04/leto_derevo_nebo_peyzazh_dom_derevya_domik_priroda_3000x2000.jpg";
        try {
            ArrayList<String> ar = ImageRecognition.getTags(url);
            WordTranslation.getTranslate(ar);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error in imagerecognition IO");
        } catch (JSONException e) {
            log.log(Level.SEVERE, "Error in imagerecognition JSON");
        } catch (Exception e) {
            log.log(Level.SEVERE, "bad request");
        }
    }


    public static String IMAGERECOGNITION_URL = "https://northeurope.api.cognitive.microsoft.com/vision/v2.0/analyze?visualFeatures=Tags&language=en";
//    public static String IMAGERECOGNITION_KEY = "8599ab38e11546f688cbda0c884edee0";
//    c7870c0b5e06474cbf7db01639353b39
    public static String IMAGERECOGNITION_KEY = "8599ab38e11546f688cbda0c884edee0";

    private static Logger log = Logger.getLogger(MainMenu.class.getName());

}
