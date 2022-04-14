package com.example.smart3_25.ui.activty.csdt;

import com.bumptech.glide.Glide;
import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.csdt.ZLTBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.util.MyApplication;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class ZLTActivity extends BaseActivity {
    private android.widget.ImageView mIv;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {

        mIv = findViewById(R.id.iv);
    }

    @Override
    protected void initData() {
        selectZLt();
    }

    private void selectZLt() {
        OKhttpUtil oKhttpUtil=new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI+"/prod-api/api/metro/city","");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZLTBean zltBean=new Gson().fromJson(string,ZLTBean.class);
                ZLTBean.DataDTO data = zltBean.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(MyApplication.getContext()).load(User.URI+data.getImgUrl()).into(mIv);
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

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zlt;
    }
}
