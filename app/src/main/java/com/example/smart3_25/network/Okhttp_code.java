package com.example.smart3_25.network;

import android.content.Context;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public interface Okhttp_code {
    void onFailure(Call call, IOException e);
    void onResponse(Call call, Response response) throws IOException;
    void getString(String s);
}
