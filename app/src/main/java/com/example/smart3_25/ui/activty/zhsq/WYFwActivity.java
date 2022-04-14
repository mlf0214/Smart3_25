package com.example.smart3_25.ui.activty.zhsq;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.ui.activty.home.YJFKActivity;
import com.example.smart3_25.util.Date;
import com.example.smart3_25.util.MyApplication;

public class WYFwActivity extends BaseActivity implements View.OnClickListener {
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.ImageView mRightIcon;
    private android.widget.ImageView mIv1;
    private android.widget.TextView mPhone1;
    private android.widget.TextView mPhone2;
    private android.widget.LinearLayout mLlYjkf;
    private android.widget.ImageView mIv2;
    private android.widget.TextView mPhone3;
    private android.widget.TextView mPhone4;
    private android.widget.LinearLayout mLlYjkf1;
    private android.widget.ImageView mIv3;
    private android.widget.TextView mPhone5;
    private android.widget.TextView mPhone6;
    private android.widget.LinearLayout mLlYjkf2;
    private android.widget.ImageView mIvPhone1;
    private android.widget.ImageView mIvPhone2;
    private android.widget.ImageView mIvPhone3;
    private android.widget.ImageView mIvPhone4;
    private android.widget.ImageView mIvPhone5;
    private android.widget.ImageView mIvPhone6;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {

        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mRightIcon = findViewById(R.id.right_icon);
        mIv1 = findViewById(R.id.iv1);
        mPhone1 = findViewById(R.id.phone1);
        mPhone2 = findViewById(R.id.phone2);
        mLlYjkf = findViewById(R.id.ll_yjkf);
        mIv2 = findViewById(R.id.iv2);
        mPhone3 = findViewById(R.id.phone3);
        mPhone4 = findViewById(R.id.phone4);
        mLlYjkf1 = findViewById(R.id.ll_yjkf1);
        mIv3 = findViewById(R.id.iv3);
        mPhone5 = findViewById(R.id.phone5);
        mPhone6 = findViewById(R.id.phone6);
        mLlYjkf2 = findViewById(R.id.ll_yjkf2);
        mIvPhone1 = findViewById(R.id.iv_phone1);
        mIvPhone2 = findViewById(R.id.iv_phone2);
        mIvPhone3 = findViewById(R.id.iv_phone3);
        mIvPhone4 = findViewById(R.id.iv_phone4);
        mIvPhone5 = findViewById(R.id.iv_phone5);
        mIvPhone6 = findViewById(R.id.iv_phone6);

                mTitle.setText("物业服务");
                mLeftIcon.setImageResource(R.drawable.arrow_back_ios_24);
    }

    @Override
    protected void initData() {
        Glide.with(MyApplication.getContext()).load(R.drawable.wy_iv).into(mIv1);
        Glide.with(MyApplication.getContext()).load(R.drawable.wy_iv).into(mIv2);
        Glide.with(MyApplication.getContext()).load(R.drawable.wy_iv).into(mIv3);
    }

    @Override
    protected void initEvent() {
        mIv1    .setOnClickListener(this);
        mPhone1 .setOnClickListener(this);
        mPhone2 .setOnClickListener(this);
        mLlYjkf .setOnClickListener(this);
        mIv2    .setOnClickListener(this);
        mPhone3 .setOnClickListener(this);
        mPhone4 .setOnClickListener(this);
        mLlYjkf1.setOnClickListener(this);
        mIv3    .setOnClickListener(this);
        mPhone5 .setOnClickListener(this);
        mPhone6 .setOnClickListener(this);
        mLlYjkf2.setOnClickListener(this);
        mIvPhone1 .setOnClickListener(this);
        mIvPhone2 .setOnClickListener(this);
        mIvPhone3 .setOnClickListener(this);
        mIvPhone4 .setOnClickListener(this);
        mIvPhone5 .setOnClickListener(this);
        mIvPhone6 .setOnClickListener(this);
          mLeftIcon.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          finish();
                      }
                  });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wyfw;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        Uri uri;
        switch (view.getId()){
            case R.id.ll_yjkf:
            case R.id.ll_yjkf1:
            case R.id.ll_yjkf2:
                goActvity(YJFKActivity.class);
                break;
            case R.id.iv_phone1:
                 intent=new Intent(Intent.ACTION_DIAL);
                uri=Uri.parse("tel:"+mPhone1.getText().toString());
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.iv_phone2:
                intent=new Intent(Intent.ACTION_DIAL);
              uri=Uri.parse("tel:"+mPhone2.getText().toString());
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.iv_phone3:
               intent=new Intent(Intent.ACTION_DIAL);
                uri=Uri.parse("tel:"+mPhone3.getText().toString());
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.iv_phone4:
               Intent intent1=new Intent(Intent.ACTION_DIAL);
               Uri uri1=Uri.parse("tel:"+mPhone4.getText().toString());
                intent1.setData(uri1);
                startActivity(intent1);
                break;
            case R.id.iv_phone5:
                 intent=new Intent(Intent.ACTION_DIAL);
              uri=Uri.parse("tel:"+mPhone5.getText().toString());
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.iv_phone6:
                 intent=new Intent(Intent.ACTION_DIAL);
                uri=Uri.parse("tel:"+mPhone6.getText().toString());
                intent.setData(uri);
                startActivity(intent);
                break;
        }
    }
}
