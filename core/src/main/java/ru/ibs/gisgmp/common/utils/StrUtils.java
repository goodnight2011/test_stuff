package ru.ibs.gisgmp.common.utils;

import jsweet.lang.Replace;

public class StrUtils {

    @Replace("return new RegExp(regexp).test(str);")
    public static boolean matches(String str, String regexp){
       return str.matches(regexp);
    }
}
