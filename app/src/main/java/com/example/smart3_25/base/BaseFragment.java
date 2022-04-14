package com.example.smart3_25.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.smart3_25.util.MyApplication;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseFragment extends Fragment {
    private View view;
    public Activity mActiivty;
    public int x,y;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        initView();
        selectWindows();
        initData();
        ininEvent();
        return view;
    }
    public void goActvity(Class<?> o){
        startActivity(new Intent(mActiivty,o));
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void setMesg(){

    }

    public void setIMage(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }
    public void setIMage(Context context, int url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }

    private void selectWindows() {
        DisplayMetrics displayMetrics = mActiivty.getResources().getDisplayMetrics();
        x=displayMetrics.widthPixels;
        y=displayMetrics.heightPixels;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActiivty= (Activity) getContext();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void ininEvent();

    public View getView(){
        return this.view;
    }
    protected abstract int getLayoutId();
}
