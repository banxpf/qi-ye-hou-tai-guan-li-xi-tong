package com.xpf.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return  bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "123";
        String s = encodePassword(password);
        System.out.println(s);
        String s1 = DateUtils.date2String(new Date(),"yyyy-mm-dd HH:mi:ss");

    }
}
