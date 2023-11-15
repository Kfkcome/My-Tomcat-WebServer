package com.hzau.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    public static void warn(String meg){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        System.out.println(date +"[warn] : "+meg);
    }
    public static void info(String meg){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        System.out.println(date +"[info] : "+meg);
    }
    public static void error(String meg){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        System.out.println(date +"[error] : "+meg);
    }
}
