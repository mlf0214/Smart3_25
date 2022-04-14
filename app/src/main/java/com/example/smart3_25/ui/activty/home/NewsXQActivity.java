package com.example.smart3_25.ui.activty.home;

import android.app.Activity;
import android.text.Html;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.home.NewsXQBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.util.MyApplication;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class NewsXQActivity extends BaseActivity {
    private String newId;
    private static final String TAG = "NewsXQActivity";
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.ImageView mIv;
    private android.widget.TextView mTv1;
    private android.widget.TextView mTv2;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {

        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mIv = findViewById(R.id.iv);
        mTv1 = findViewById(R.id.tv1);
        mTv2 = findViewById(R.id.tv2);
                mTitle.setText("新闻详情");
                mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setMessg(Messg messg) {
        newId = messg.getNewId();
        Log.d(TAG,"newId:"+newId);
        selectCOntent(newId);
    }

    private void selectCOntent(String newId) {

        OKhttpUtil oKhttpUtil=new OKhttpUtil(this);
                 oKhttpUtil.setget(User.URI+"/prod-api/press/press/"+newId,"");
                 oKhttpUtil.setOkhttp_code(new Okhttp_code() {
                     @Override
                     public void onFailure(Call call, IOException e) {

                     }

                     @Override
                     public void onResponse(Call call, Response response) throws IOException {
                         String string = response.body().string();
                         NewsXQBean newsXQBean=new Gson().fromJson(string,NewsXQBean.class);
                         NewsXQBean.DataDTO data = newsXQBean.getData();
                         runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 Glide.with(MyApplication.getContext()).load(User.URI+data.getCover()).into(mIv);
                                 mTv1.setText(data.getTitle());
                                 mTv2.setText("\t\t"+Html.fromHtml(data.getContent()));
                             }
                         });
                     }

                     @Override
                     public void getString(String s) {

                     }


                 });
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
        return R.layout.activity_newsxq;
    }
}
