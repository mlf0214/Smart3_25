package com.example.smart3_25.util;

import android.provider.Settings;

import java.text.SimpleDateFormat;

public class Date extends java.util.Date {
    public static String getTHisDate(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        return format.format(System.currentTimeMillis());
    }
}
