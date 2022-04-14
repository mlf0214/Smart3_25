package com.example.smart3_25.util.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class MyVIewPager extends ViewPager {
    private boolean ishd;

    public boolean isIshd() {
        return ishd;
    }

    public void setIshd(boolean ishd) {
        this.ishd = ishd;
    }

    public MyVIewPager(@NonNull Context context) {
        super(context);
    }

    public MyVIewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return ishd&&super.onInterceptTouchEvent(ev);
    }
}
