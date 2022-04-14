package com.example.smart3_25.util.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.TimerTask;

public class TimeTrik extends TimerTask {
   private ListView list;
   private Context context;

    public TimeTrik(ListView list) {
        this.list = list;
    }
    public ListView getListVIew(){
        return this.list;
    }
    @Override
    public void run() {
        list.smoothScrollBy(5,0);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, ""+i, Toast.LENGTH_SHORT).show();
            }
        });
    }



}
