package com.xpf.utils;

import javax.sound.midi.SoundbankResource;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {



    //日期转换成字符串

    public  static String date2String(Date date, String patt){

        SimpleDateFormat sdf = new SimpleDateFormat(patt);

        String format = sdf.format(date);
        return  format;

    }

    //字符串转换成日期
    public  static Date string2Date(String str,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return parse;
    }

    public static void main(String[] args) {
//        Date d = new Date();
        String s= date2String(new Date(),"yyyy-MM-dd HH:mm:ss");
        System.out.println("dee ");
        System.out.println(s);
    }
}
