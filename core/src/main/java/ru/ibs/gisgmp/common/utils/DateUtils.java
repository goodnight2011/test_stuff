package ru.ibs.gisgmp.common.utils;

import jsweet.lang.Replace;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    @Replace("return new Date(year, month, day);")
    public static Date createDate(int day, int month, int year){
       return new GregorianCalendar(year, month, day).getTime();
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
              if(isInverse)
                  arr = new String[]{arr[2], arr[1], arr[0]};
           } else arr = new String[]{
                   isInverse ? str.substring(6, 8) : str.substring(0, 2),
                   isInverse ? str.substring(4, 6) : str.substring(2, 4),
                   isInverse ? str.substring(6, 10) : str.substring(4, 8)
           };
           int day = Integer.parseInt(arr[0]);
           int month = Integer.parseInt(arr[1]);
           int year = Integer.parseInt(arr[2]);
           return createDate(year, month, day);
       }
       catch(Exception e){
           return null;
       }
    }

    @Replace("return one.getTime() - two.getTime();")
    public static int compare(Date one, Date two){
       return one.compareTo(two);
    }
}
