package com.example.smart3_25.network;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.smart3_25.util.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class VollUtil {
    private StringRequest stringRequest;
    private JsonObjectRequest jsonObjectRequest;
    private Httplim httplim;
    private JSONObject jsonObject;

    public void setHttplim(Httplim httplim) {
         this.httplim=httplim;
    }

    public VollUtil() {
        jsonObject=new JSONObject();
    }

    public void GET(String url, String token){
            if (token.equals("")){
                stringRequest=new StringRequest(Request.Method.GET, url.trim(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            String s1 = new String(s.getBytes("ISO8859-1"), "UTF-8");
                            httplim.onResponse(s1);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        httplim.onErrorResponse(volleyError);
                    }
                });
            }else {
                stringRequest=new StringRequest(Request.Method.GET, url.trim(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            String s1 = new String(s.getBytes("ISO8859-1"), "UTF-8");
                            httplim.onResponse(s1);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        httplim.onErrorResponse(volleyError);
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> map=new HashMap<>();
                        map.put("Authorization",token);
                        return map;
                    }
                };
            }
            MyApplication.getRequestQueue().getCache().clear();
            MyApplication.getRequestQueue().add(stringRequest);
    }
    public void POST(String url,String token,String... strings){
        for (int i = 0; i < strings.length; i++) {
            if (i%2==0){
                try {
                    jsonObject.put(strings[i],strings[i+1]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (token.equals("")){
                jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        httplim.onResponse(jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        httplim.onErrorResponse(volleyError);
                    }
                });
            }else {
                jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        httplim.onResponse(jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        httplim.onErrorResponse(volleyError);
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> map=new HashMap<>();
                        map.put("Authorization",token);
                        return map;
                    }
                };
            }
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(250*1000,0,0f));
            MyApplication.getRequestQueue().getCache().clear();
            MyApplication.getRequestQueue().add(jsonObjectRequest);
        }





    }
    public void PUT(String url,String token,String... strings){
        for (int i = 0; i < strings.length; i++) {
            if (i%2==0){
                try {
                    jsonObject.put(strings[i],strings[i+1]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (token.equals("")){
                jsonObjectRequest=new JsonObjectRequest(Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        httplim.onResponse(jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        httplim.onErrorResponse(volleyError);
                    }
                });
            }else {
                jsonObjectRequest=new JsonObjectRequest(Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        httplim.onResponse(jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        httplim.onErrorResponse(volleyError);
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> map=new HashMap<>();
                        map.put("Authorization",token);
                        return map;
                    }
                };
            }
            MyApplication.getRequestQueue().getCache().clear();
            MyApplication.getRequestQueue().add(jsonObjectRequest);
        }





    }

}
