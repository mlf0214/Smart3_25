package com.example.smart3_25.ui.activty.zfz;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.zfz.ZfzXQBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.util.MyApplication;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class ZFZxqActivity extends BaseActivity
{
    private String id;
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.ImageView mRightIcon;
    private android.widget.ImageView mIv;
    private android.widget.TextView mTvName;
    private android.widget.TextView mTv2;
    private android.widget.TextView mTv3;
    private android.widget.TextView mTv4;
    private android.widget.TextView mTv5;
    private android.widget.Button mBtn1;
    private android.widget.Button mBtn2;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    public void setMessg(Messg messg) {
        String fyId = messg.getFyId();
        id=fyId;
    }

    @Override
    protected void initView() {

        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mRightIcon = findViewById(R.id.right_icon);
        mIv = findViewById(R.id.iv);
        mTvName = findViewById(R.id.tv_name);
        mTv2 = findViewById(R.id.tv2);
        mTv3 = findViewById(R.id.tv3);
        mTv4 = findViewById(R.id.tv4);
        mTv5 = findViewById(R.id.tv5);
        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);

                mTitle.setText("详情页面");
                mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
    }

    @Override
    protected void initData() {
        selectData(id);
    }

    private void selectData(String id) {
        OKhttpUtil oKhttpUtil=new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI+"/prod-api/api/house/housing/"+id,"");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZfzXQBean zfzXQBean=new Gson().fromJson(string,ZfzXQBean.class);
                ZfzXQBean.DataDTO data = zfzXQBean.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //<!--
                        //信息详情页，点击房源列表中的某个房源，
                        //进入到房源详情页面。详情页面分别展示房源图片、
                        //房源名称、建筑面积、房源单价、房源类型、房源介绍等信息。
                        //底部展示主页和打电话按钮，点击“主页”，返回找房主主页，点击
                        //“打电话”可以直接系统拨号页面，联系对应详情中的电话号码
                        //
                        //
                        //-->
                        Glide.with(MyApplication.getContext()).load(User.URI+data.getPic()).into(mIv);
                        mTvName.setText(data.getSourceName());
                        mTv2.setText("建筑面积:"+data.getAreaSize());
                        mTv3.setText("房源单价:"+data.getPrice());
                        mTv4.setText("房源类型:"+data.getHouseType());
                        mTv5.setText("房源介绍:"+data.getDescription());
                        mBtn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dadianhua(data.getTel());
                            }
                        });

                    }
                });
            }

            @Override
            public void getString(String s) {

            }


        });
    }

    private void dadianhua(String tel) {
        Intent intent=new Intent(Intent.ACTION_DIAL);
        Uri parse = Uri.parse("tel:"+tel);
        intent.setData(parse);
        startActivity(intent);
    }

    @Override
    protected void initEvent() {
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
      mLeftIcon.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      finish();
                  }
              });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zfzxq;
    }
}
