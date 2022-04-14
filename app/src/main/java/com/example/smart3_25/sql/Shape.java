package com.example.smart3_25.sql;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.smart3_25.util.MyApplication;

public class Shape {
    private static SharedPreferences sharedPreferences= MyApplication.getContext().getSharedPreferences("666", Context.MODE_PRIVATE);
    private static SharedPreferences.Editor editor=sharedPreferences.edit();

    public static void setValue(String key,String value){

        editor.putString(key, value).commit();
    }
    public static void setValue(String key,int value){
        editor.putInt(key, value).commit();
    }
    public static void setValue(String key,boolean value){
        editor.putBoolean(key, value).commit();
    }
    public static String getValue(String key,String value){
        return sharedPreferences.getString(key, value);
    }
    public static int getValue(String key,int value){
        return sharedPreferences.getInt(key, value);
    }
    public static boolean getValue(String key,boolean value){
        return sharedPreferences.getBoolean(key, value);
    }


}
