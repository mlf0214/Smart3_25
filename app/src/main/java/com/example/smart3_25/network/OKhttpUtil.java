package com.example.smart3_25.network;

import android.app.Activity;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKhttpUtil {
    private OkHttpClient okHttpClient;
    private JSONObject jsonObject;
    private Okhttp_code okhttp_code;
    private Activity mActivity;

    public OKhttpUtil(Activity activity) {
        okHttpClient=new OkHttpClient.Builder()
                .callTimeout(500, TimeUnit.SECONDS)
                .connectTimeout(500,TimeUnit.SECONDS)
                .writeTimeout(500,TimeUnit.SECONDS)
                .readTimeout(500,TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();
        jsonObject=new JSONObject();
        mActivity=activity;
    }
    public void setOkhttp_code(Okhttp_code okhttp_code){
        this.okhttp_code=okhttp_code;
    }
    public void setget(String url,String token){
        Request request=null;
        if (token.equals("")){
            request=new Request.Builder()
                    .url(url)
                    .get()
                    .build();
        }else {
            request=new Request.Builder()
                    .url(url)
                    .addHeader("Authorization",token)
                    .get().build();
        }
        Call call= okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okhttp_code.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            okhttp_code.onResponse(call, response);
            }
        });
    }
    public void setpost(String url,String token,String... strings){
        for (int i = 0; i < strings.length; i++) {
            if (i%2==0){
                try {
                    jsonObject.put(strings[i],strings[i+1]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
        Request request=null;
        if (token.equals("")){
            request=new Request.Builder().post(requestBody)
                    .url(url).build();
        }else {
            request=new Request.Builder().post(requestBody)
                    .addHeader("Authorization",token)
                    .url(url)
                    .build();
        }
            Call call= okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okhttp_code.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                okhttp_code.onResponse(call, response);
//                okhttp_code.getString(response.body().string());
            }
        });

        }
    public void setput(String url,String token,String... strings){
        for (int i = 0; i < strings.length; i++) {
            if (i%2==0){
                try {
                    jsonObject.put(strings[i],strings[i+1]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
        Request request=null;
        if (token.equals("")){
            request=new Request.Builder().put(requestBody)
                    .url(url).build();
        }else {
            request=new Request.Builder().put(requestBody)
                    .addHeader("Authorization",token)
                    .url(url)
                    .build();
        }
            Call call= okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okhttp_code.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                okhttp_code.onResponse(call, response);
            }
        });

        }
}
