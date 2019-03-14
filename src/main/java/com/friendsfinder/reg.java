package com.friendsfinder;

import com.friendsfinder.api.Page;
import com.sun.deploy.net.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class reg
{
    public static void main(String[] args) {
//        String str = "кот, собака,полиглот";
//        String regex = "([а-яА-Я#_]+)";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher text = pattern.matcher(str);
//
//        for(int i = 1; i< text.groupCount(); ++i){
//            System.out.println(text.group(i));
//        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
//
//        HttpPost request = new HttpPost("http://yoururl");
//        StringEntity params =new StringEntity("details={\"name\":\"myname\",\"age\":\"20\"} ");
//        request.addHeader("content-type", "application/x-www-form-urlencoded");
//        request.setEntity(params);
//        HttpResponse response = httpClient.execute(request);
//        }
//        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        String url = "https://northeurope.api.cognitive.microsoft.com/vision/v2.0/analyze?visualFeatures=Tags&language=en";
        try {
            URL urllll = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urllll.openConnection();

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Ocp-Apim-Subscription-Key", "8599ab38e11546f688cbda0c884edee0");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
//            String json= "{url":"http://i.ytimg.com/vi/YRxsxJtYT6k/maxresdefault.jpg}";
            JSONObject j = new JSONObject();
            j.put("url","http://i.ytimg.com/vi/YRxsxJtYT6k/maxresdefault.jpg");
            OutputStream os = conn.getOutputStream();
            os.write(j.toString().getBytes("UTF-8"));
            os.close();

            // read the response
//            InputStream in = new BufferedInputStream(conn.getInputStream());
//            String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
//            JSONObject jsonObject = new JSONObject(result);
//
//
//            in.close();
//            conn.disconnect();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

//        String content = Page.GetContent(url);
//        System.out.println(content);
        String str = "Крещение Руси,произошло в 988 году! Не так ли? ";
        String pattern = "\t\n\r,.";
        String [] s = str.split("\\p{P}?[ \\t\\n\\r]+");
        for(int i= 0 ;i<s.length; ++i)
            System.out.println(s[i] +" ");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Создание Pattern объекта
//        Pattern r = Pattern.compile(pattern);
//
//        // Создание matcher объекта
//        Matcher m = r.matcher(str);
//        if (m.find( )) {
//            System.out.println("Найдено значение: " + m.group(0));
//            System.out.println("Найдено значение: " + m.group(1));
//            System.out.println("Найдено значение: " + m.group(2));
//        }else {
//            System.out.println("НЕ СОВПАДАЕТ");
//        }
    }
}
