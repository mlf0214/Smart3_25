package com.example.smart3_25.util;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.litepal.LitePalApplication;

public class MyApplication  extends LitePalApplication {
    private static RequestQueue requestQueue;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        requestQueue= Volley.newRequestQueue(context);
    }
    public static Context getContext(){
        return context;
    }
    public static RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
