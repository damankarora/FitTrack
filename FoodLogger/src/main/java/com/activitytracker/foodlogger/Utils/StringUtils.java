package com.activitytracker.foodlogger.Utils;

import java.util.List;

public class StringUtils {
    public static boolean isEmpty(String s){
        return s == null || s.isEmpty() || s.trim().isEmpty();
    }

    public static boolean isEmptyMultiple(List<String> inputs){
        for(String s : inputs){
            if (isEmpty(s)){
                return true;
            }
        }
        return false;
    }
}
