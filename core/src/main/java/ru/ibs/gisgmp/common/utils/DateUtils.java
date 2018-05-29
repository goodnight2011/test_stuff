package ru.ibs.gisgmp.common.utils;

import jsweet.lang.Replace;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    @Replace("return date.getFullYear();")
    public static int getYear(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    @Replace("return date.getDate();")
    public static int getDay(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    @Replace("return date.getMonth();")
    public static int getMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH);
    }

    public static String pad2digits(int val){
       return val < 10 ? "0" + val : "" + val;
    }

    public static String format(Date date, String delimiter, boolean isInverse){
       int year = getYear(date);
       int month = getMonth(date);
       int day = getDay(date);
       String sm = pad2digits(month);
       String sd = pad2digits(day);

       if(isInverse)
           return "" + year + delimiter + sm + delimiter + sd;
       else return sd + delimiter + sm + delimiter + year;
    }

    public static Date parseDate(String str, String delimiter, boolean isInverse){
       try{
           String[] arr;
           if(!delimiter.isEmpty()){
              arr = str.split(delimiter);
           }
           else arr = new String[]{
                isInverse ? str.substring()
           };
       }
       catch(Exception e){
           return null;
       }
    }
}
