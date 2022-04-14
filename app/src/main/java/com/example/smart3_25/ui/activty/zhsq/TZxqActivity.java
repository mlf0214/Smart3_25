package com.example.smart3_25.ui.activty.zhsq;

import android.text.TextUtils;
import android.view.View;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;

public class TZxqActivity extends BaseActivity {
    private String date,tzTitile,tzcontent;
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.ImageView mRightIcon;
    private android.widget.TextView mTvContent;
    private android.widget.TextView mTvDate;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {

        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mRightIcon = findViewById(R.id.right_icon);
        mTvContent = findViewById(R.id.tv_content);
        mTvDate = findViewById(R.id.tv_date);

                mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
    }

    @Override
    public void setMessg(Messg messg) {
         date     = messg.getDate();
         tzTitile = messg.getTzTitile();
         tzcontent = messg.getTzcontent();
    }

    @Override
    protected void initData() {
        mTitle.setText(tzTitile);
        mTvContent.setText(tzcontent);
        mTvDate.setText(date);
        mTitle.setEllipsize(TextUtils.TruncateAt.END);
        mTitle.setMaxLines(1);
    }

    @Override
    protected void initEvent() {
  mLeftIcon.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  finish();
              }
          });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhsqxq;
    }
}
