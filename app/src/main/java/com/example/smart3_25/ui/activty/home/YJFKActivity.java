package com.example.smart3_25.ui.activty.home;

import android.view.View;
import android.widget.Toast;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.login.ZhuceBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.Shape;
import com.example.smart3_25.sql.User;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class YJFKActivity extends BaseActivity {
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.ImageView mRightIcon;
    private android.widget.EditText mEt;
    private android.widget.Button mBtn;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {

        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mRightIcon = findViewById(R.id.right_icon);
        mEt = findViewById(R.id.et);
        mBtn = findViewById(R.id.btn);
        
                mTitle.setText("意见反馈");
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
                  addYJfk(mEt.getText().toString());
              }
          });
    }

    private void addYJfk(String content) {
        OKhttpUtil oKhttpUtil=new OKhttpUtil(this);
        oKhttpUtil.setpost(User.Ip+"/prod-api/api/common/feedback", Shape.getValue("token",""),"content",content);
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZhuceBean zhuceBean=new Gson().fromJson(string,ZhuceBean.class);
                int code = zhuceBean.getCode();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code==200){
                            Toast.makeText(YJFKActivity.this, "反馈成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(YJFKActivity.this, zhuceBean.getMsg(), Toast.LENGTH_SHORT).show();
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
    protected int getLayoutId() {
        return R.layout.activity_yjkf;
    }
}
