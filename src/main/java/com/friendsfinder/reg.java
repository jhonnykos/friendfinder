package com.friendsfinder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//        }

        String str = "Крещение Руси,произошло в 988 году! Не так ли? ";
        String pattern = "\t\n\r,.";
        String [] s = str.split("\\p{P}?[ \\t\\n\\r]+");
        for(int i= 0 ;i<s.length; ++i)
            System.out.println(s[i] +" ");

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
