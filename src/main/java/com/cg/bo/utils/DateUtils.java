package com.cg.bo.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtils {

    public java.sql.Date getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        return java.sql.Date.valueOf(formatter.format(date));
    }

    public String getCurrentTime(){
        Date date = new Date();
        return date.getHours() +":"+date.getMinutes() +":"+  date.getSeconds();
    }

    public long differentTimeInMinutes(String time) {
        String currentTime = getCurrentTime();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        try {
            return (format.parse(currentTime).getTime() - format.parse(time).getTime())/(1000*60);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

}