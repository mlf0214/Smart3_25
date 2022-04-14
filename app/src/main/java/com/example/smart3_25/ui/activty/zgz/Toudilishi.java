package com.example.smart3_25.ui.activty.zgz;

import android.view.View;
import android.widget.TextView;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.zgz.JianliListBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.Shape;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.util.adapter.MyBaseAdapter;
import com.example.smart3_25.util.adapter.ViewHolde;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class Toudilishi extends BaseActivity {
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.ImageView mRightIcon;
    private com.example.smart3_25.util.myview.MyListVIew mMlv;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {

        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mRightIcon = findViewById(R.id.right_icon);
        mMlv = findViewById(R.id.mlv);

                mTitle.setText("投递历史");
                mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
    }

    @Override
    protected void initData() {
        selectLIst();
    }

    private void selectLIst() {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI + "/prod-api/api/job/deliver/list", Shape.getValue("token", ""));
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                JianliListBean jianliListBean = new Gson().fromJson(string, JianliListBean.class);
                List<JianliListBean.RowsDTO> rows = jianliListBean.getRows();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //2.投递记录页面，列表显示已经投简历的公司，信息包括岗位名称、公司名称、薪资和投递简历时间。
                        mMlv.setAdapter(new MyBaseAdapter<JianliListBean.RowsDTO>(rows, R.layout.zw_list, rows.size()) {
                            private TextView mTv4;
                            private TextView mTv3;
                            private TextView mTvContent;
                            private TextView mTvTitle;

                            @Override
                            public void converView(ViewHolde viewHolde, JianliListBean.RowsDTO rowsDTO, int i) {
                                mTvTitle =   viewHolde.getVIew(R.id.tv_title);
                                mTvContent = viewHolde.getVIew(R.id.tv_content);
                                mTv3 =       viewHolde.getVIew(R.id.tv3);
                                mTv4 =       viewHolde.getVIew(R.id.tv4);
                                mTvTitle.setText("岗位名称:"+rowsDTO.getPostName());
                                mTvContent.setText("公司名称:"+rowsDTO.getCompanyName());
                                mTv3.setText("薪资:"+rowsDTO.getMoney());
                                mTv4.setText("投递时间:"+rowsDTO.getCreateTime());

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
        return R.layout.activity_tdlishi;
    }
}
