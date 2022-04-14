package com.example.smart3_25.ui.activty.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.ui.fragment.Fragment1;
import com.example.smart3_25.ui.fragment.Fragment2;
import com.example.smart3_25.ui.fragment.Fragment3;
import com.example.smart3_25.ui.fragment.Fragment4;
import com.example.smart3_25.ui.fragment.Fragment5;
import com.example.smart3_25.util.adapter.MYFragmentAdapter;
import com.example.smart3_25.util.myview.MyVIewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mLeftIcon;
    private TextView mTitle;
    private ImageView mIvTab1;
    private TextView mTvTab1;
    private ImageView mIvTab2;
    private TextView mTvTab2;
    private ImageView mIvTab3;
    private TextView mTvTab3;
    private ImageView mIvTab4;
    private TextView mTvTab4;
    private ImageView mIvTab5;
    private TextView mTvTab5;


    @Override
    protected void fsmesg(Messg messg) {

    }

    protected void initView() {
        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mIvTab1 = findViewById(R.id.iv_tab1);
        mTvTab1 = findViewById(R.id.tv_tab1);
        mIvTab2 = findViewById(R.id.iv_tab2);
        mTvTab2 = findViewById(R.id.tv_tab2);
        mIvTab3 = findViewById(R.id.iv_tab3);
        mTvTab3 = findViewById(R.id.tv_tab3);
        mIvTab4 = findViewById(R.id.iv_tab4);
        mTvTab4 = findViewById(R.id.tv_tab4);
        mIvTab5 = findViewById(R.id.iv_tab5);
        mTvTab5 = findViewById(R.id.tv_tab5);

        mIvTab1.setClickable(true);
        mIvTab2.setClickable(true);
        mIvTab3.setClickable(true);
        mIvTab4.setClickable(true);
        mIvTab5.setClickable(true);
        mIvTab1 .setOnClickListener(this);
        mTvTab1 .setOnClickListener(this);
        mIvTab2 .setOnClickListener(this);
        mTvTab2 .setOnClickListener(this);
        mIvTab3 .setOnClickListener(this);
        mTvTab3 .setOnClickListener(this);
        mIvTab4 .setOnClickListener(this);
        mTvTab4 .setOnClickListener(this);
        mIvTab5 .setOnClickListener(this);
        mTvTab5 .setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        setTab(0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_tab1:
            case R.id.iv_tab1:
               setTab(0);
                break;
            case R.id.tv_tab2:
            case R.id.iv_tab2:
                setTab(1);
                break;
            case R.id.tv_tab3:
            case R.id.iv_tab3:
                setTab(2);
                break;
            case R.id.tv_tab4:
            case R.id.iv_tab4:
                setTab(3);
                break;
            case R.id.tv_tab5:
            case R.id.iv_tab5:
                setTab(4);
                break;
        }
    }
    private void setFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content,fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
    public void setTab(int i) {
        initTab();
        switch (i){
            case 0:
                mIvTab1.setSelected(true);
                mTvTab1.setSelected(true);
                setFragment(new Fragment1());
                break;
            case 1:
                mIvTab2.setSelected(true);
                mTvTab2.setSelected(true);
                setFragment(new Fragment2());

                break;
            case 2:
                mIvTab3.setSelected(true);
                mTvTab3.setSelected(true);
                setFragment(new Fragment3());

                break;
            case 3:
                mIvTab4.setSelected(true);
                mTvTab4.setSelected(true);
                setFragment(new Fragment4());

                break;
            case 4:
                mIvTab5.setSelected(true);
                mTvTab5.setSelected(true);
                setFragment(new Fragment5());

                break;
        }
    }

    private void initTab() {
        mIvTab1.setSelected(false);
        mTvTab2.setSelected(false);
        mIvTab2.setSelected(false);
        mTvTab3.setSelected(false);
        mIvTab3.setSelected(false);
        mTvTab4.setSelected(false);
        mIvTab4.setSelected(false);
        mTvTab5.setSelected(false);
        mIvTab5.setSelected(false);
        mTvTab1.setSelected(false);

    }
}