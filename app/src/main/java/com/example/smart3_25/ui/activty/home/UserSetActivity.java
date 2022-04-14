package com.example.smart3_25.ui.activty.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.home.UserBean;
import com.example.smart3_25.bean.login.ZhuceBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.Shape;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.ui.ShowDiag;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class UserSetActivity extends BaseActivity {
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.ImageView mRightIcon;
    private android.widget.ImageView mIv;
    private android.widget.TextView mTvSettp;
    private android.widget.EditText mEtName;
    private android.widget.RadioButton mRb1;
    private android.widget.RadioButton mRb2;
    private android.widget.EditText mEtPhone;
    private android.widget.Button mBtn;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {
    //可修改内容为：头像、昵称、性别、联系电话，注：手机号码后四位使用*号代替。
        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mRightIcon = findViewById(R.id.right_icon);
        mIv = findViewById(R.id.iv);
        mTvSettp = findViewById(R.id.tv_settp);
        mEtName = findViewById(R.id.et_name);
        mRb1 = findViewById(R.id.rb1);
        mRb2 = findViewById(R.id.rb2);
        mEtPhone = findViewById(R.id.et_phone);

                mTitle.setText("个人信息修改");
                mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
        mBtn = findViewById(R.id.btn);
    }

    @Override
    protected void initData() {
        selectUserDta();
    }

    private void selectUserDta() {
        OKhttpUtil oKhttpUtil=new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI+"/prod-api/api/common/user/getInfo", Shape.getValue("token",""));
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                UserBean userBean =new Gson().fromJson(string,UserBean.class);
                UserBean.UserDTO user = userBean.getUser();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String nickName = user.getNickName();
                        mEtName.setText(nickName);
                        String phonenumber = user.getPhonenumber();
                        String s = phonenumber.substring(0, phonenumber.length() - 4) + "****";
                        mEtPhone.setText(s);
                        String sex = user.getSex();
                        if (sex.equals("0")){
                            mRb1.setChecked(true);
                        }else {
                            mRb2.setChecked(true);
                        }

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
        mTvSettp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDiag.showZpdig(UserSetActivity.this,UserSetActivity.this,100);
            }
        });
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //{
                //"email": "lixl@163.com",
                //"idCard": "210882199807251656",
                //"nickName": "大卫王",.....
                //"phonenumber": "15898125461",
                //"sex": "0"
                //}
                String sex;
                if (mRb1.isChecked()){
                    sex="0";
                }else {
                    sex="1";
                }
                setUserData(mEtName.getText().toString(),sex,mEtPhone.getText().toString());
            }
        });

    }

    private void setUserData(String nickname, String sex, String phone) {
        OKhttpUtil oKhttpUtil=new OKhttpUtil(this);
        oKhttpUtil.setput(User.Ip+"/prod-api/api/common/user\n".trim(),Shape.getValue("token",""));
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZhuceBean zhuceBean=new Gson().fromJson(string,ZhuceBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int code = zhuceBean.getCode();
                        if (code==200){
                            Toast.makeText(UserSetActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(UserSetActivity.this, zhuceBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void getString(String s) {

            }

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==RESULT_OK){
            Bundle bundle = data.getExtras();
            Bitmap bitmap= (Bitmap) bundle.get("data");
            Glide.with(this).load(bitmap).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIv);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }
}
