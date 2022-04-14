package com.example.smart3_25.ui.activty.login;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.login.ZhuceBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.util.MyApplication;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.Response;

public class ZHuceActiivty extends BaseActivity {
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.EditText mUsername;
    private android.widget.EditText mPassword;
    private android.widget.EditText mYouxiang;
    private android.widget.EditText mPhone;
    private android.widget.RadioButton mNan;
    private android.widget.RadioButton mNv;
    private android.widget.Button mBtn;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {

        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mYouxiang = findViewById(R.id.youxiang);
        mPhone = findViewById(R.id.phone);
        mNan = findViewById(R.id.nan);
        mNv = findViewById(R.id.nv);
        mBtn = findViewById(R.id.btn);
                mTitle.setText("注册页面");
                mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initEvent() {
          mLeftIcon.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          finish();
                      }
                  });
          mBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  sumit(mUsername.getText().toString(),mPassword.getText().toString(),mPhone.getText().toString(),mYouxiang.getText().toString());
              }
          });
    }

    private void sumit(String username, String password, String phone, String youxiang) {
        String sex;
        if (mNan.isChecked()){
            sex="0";
        }else {
            sex="1";
        }
        setOKhp(username,password,phone,youxiang,sex);
    }

    private void setOKhp(String username, String password, String phone, String youxiang, String sex) {
        //{
        //"avatar": "/profile/2020/10/26/27e7fd58-0972-4dbf-941c-590624e6a886.
        //png",
        //"userName": "David",
        //"nickName": "大卫",
        //"password": "123456",
        //"phonenumber": "15840669812",
        //"sex": "0",
        //"email": "David@163.com",
        //"idCard": "210113199808242137"
        //}
        OKhttpUtil oKhttpUtil=new OKhttpUtil(this);
                oKhttpUtil.setpost(User.Ip+"/prod-api/api/register","","username",username,"password",password,"phonenumber",phone
                ,"sex",sex,"email",youxiang);
                oKhttpUtil.setOkhttp_code(new Okhttp_code() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                    }

                    @Override
                    public void getString(String s) {
                        ZhuceBean zhuceBean=new Gson().fromJson(s,ZhuceBean.class);
                        Toast.makeText(ZHuceActiivty.this, zhuceBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhuce;
    }
}
