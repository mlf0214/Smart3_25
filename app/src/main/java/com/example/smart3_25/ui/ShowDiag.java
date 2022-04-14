package com.example.smart3_25.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;

import com.example.smart3_25.ui.activty.login.LogActivity;
import com.example.smart3_25.util.MyApplication;

public class ShowDiag {
    private Context context;
    private static Activity activity;

    public static void showZpdig(Activity activity,Context context,int requestcode){
        AlertDialog builder=new AlertDialog.Builder(context)
                .setTitle("选择图片")
                .setNegativeButton("拍照", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),requestcode);
                    }
                }).show();
    }
    public static void showTimeDig(Context context, TextView textView){
        TimePickerDialog timePickerDialog=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                textView.setText(i+":"+i1);
            }
        },9,30,true);
    }
    public static void showDateDig(Context context,TextView textView){
        DatePickerDialog datePickerDialog=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String month;
                if (i1<10){
                    month="0"+i1+1;
                }else {
                    month= String.valueOf(i1+1);
                }
                textView.setText(i+"-"+i1+"-"+i2);
            }
        },2020,10,21);
    }
    public static void showToken(Context context){
        AlertDialog alertDialog=new AlertDialog.Builder(context)
                .setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.startActivity(new Intent(activity, LogActivity.class));
                    }
                }).show();
    }


}
