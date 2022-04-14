package com.example.smart3_25.network;

import com.android.volley.VolleyError;

public interface Httplim {
    void onResponse(String s);
    void onErrorResponse(VolleyError volleyError);

}
