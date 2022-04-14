package com.example.smart3_25.ui.activty.login;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.login.LoginBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.Shape;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.ui.activty.home.MainActivity;
import com.example.smart3_25.util.MyApplication;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class LogActivity extends BaseActivity {


    private android.widget.EditText mEtUsername;
    private android.widget.EditText mEtPassword;
    private android.widget.TextView mTvGoZhuce;
    private android.widget.Button mBtnLogin;
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private String TAG="LogActivity";

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {
        mEtUsername = findViewById(R.id.et_username);
        mEtPassword = findViewById(R.id.et_password);
        mTvGoZhuce = findViewById(R.id.tv_go_zhuce);
        mBtnLogin = findViewById(R.id.btn_login);
        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mTitle.setText("登录页面");
        mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
        mEtUsername.setText(Shape.getValue("username",""));
        mEtPassword.setText(Shape.getValue("password",""));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        mTvGoZhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyApplication.getContext(),ZHuceActiivty.class));
            }
        });
        mLeftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumit(mEtUsername.getText().toString(),mEtPassword.getText().toString());
            }
        });
    }

    private void sumit(String username, String password) {
//        setVolley(username,password);
        setOKhp(username,password);
    }

    private void setOKhp(String username, String password) {
        OKhttpUtil oKhttpUtil=new OKhttpUtil(this);
        oKhttpUtil.setpost(User.Ip+"prod-api/api/login","","username",username,"password",password);
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                LoginBean loginBean=new Gson().fromJson(s,LoginBean.class);
                int code = loginBean.getCode();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code==200){
                            Toast.makeText(LogActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MyApplication.getContext(),MainActivity.class));
                            Shape.setValue("username",mEtUsername.getText().toString());
                            Shape.setValue("password",mEtPassword.getText().toString());
                            Shape.setValue("token",loginBean.getToken());
                        }else {
                            Toast.makeText(LogActivity.this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void getString(String s) {


            }

        });
    }
//
//    private void setVolley(String username, String password) {
//        VollUtil vollUtil=new VollUtil();
//        vollUtil.POST(User.Ip+"prod-api/api/login","","username",username,"password",password);
//        vollUtil.setHttplim(new Httplim() {
//            @Override
//            public void onResponse(String s) {
//                Log.d(TAG,s);
//                LoginBean loginBean=new Gson().fromJson(s,LoginBean.class);
//                if (loginBean.getCode()==200){
//                    Toast.makeText(LogActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MyApplication.getContext(), MainActivity.class));
//                }else {
//                    Toast.makeText(LogActivity.this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
////                Log.d(TAG,volleyError.toString());
//                Log.d(TAG,"请求失败");
//
//            }
//        });
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

}
