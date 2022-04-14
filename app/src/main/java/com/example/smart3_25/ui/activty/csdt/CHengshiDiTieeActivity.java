package com.example.smart3_25.ui.activty.csdt;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.csdt.SYLIstBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.util.adapter.MyBaseAdapter;
import com.example.smart3_25.util.adapter.ViewHolde;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class CHengshiDiTieeActivity extends BaseActivity {
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.Spinner mSpinner;
    private android.widget.TextView mTvTitle;
    private com.example.smart3_25.util.myview.MyListVIew mMlv;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {
        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mSpinner = findViewById(R.id.spinner);
        mTvTitle = findViewById(R.id.tv_title);
        mMlv = findViewById(R.id.mlv);
        mTvTitle.setText("建国门");
        mTitle.setText("城市地铁");
        mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
    }

    @Override
    protected void initData() {
        selectZhanDianLIst();
        showLInsListVIew("建国门");
    }


    private void showLInsListVIew(String s) {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI + "/prod-api/api/metro/list?currentName=" + s, "");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                SYLIstBean sylIstBean = new Gson().fromJson(string, SYLIstBean.class);
                List<SYLIstBean.DataDTO> data = sylIstBean.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMlv.setAdapter(new MyBaseAdapter<SYLIstBean.DataDTO>(data, R.layout.lv_dtsy, data.size()) {
                            private TextView mTv3;
                            private TextView mTv2;
                            private TextView mTv1;

                            @Override
                            public void converView(ViewHolde viewHolde, SYLIstBean.DataDTO dataDTO, int i) {
                                mTv1 = viewHolde.getVIew(R.id.tv1);
                                mTv2 = viewHolde.getVIew(R.id.tv2);
                                mTv3 = viewHolde.getVIew(R.id.tv3);
                                if (dataDTO.getNextStep()!=null) {
                                    mTv1.setText("下一站名称:" + dataDTO.getNextStep().getName());
                                }
                                mTv2.setText("线路名称:"+dataDTO.getLineName());
                                mTv3.setText("到达本站时长:"+dataDTO.getReachTime()+"分");

                            }
                        });
                        mMlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                int lineId = data.get(i).getLineId();
                                Messg messg=new Messg();
                                messg.setLineId(String.valueOf(lineId));
                                EventBus.getDefault().postSticky(messg);
                                goActvity(ListXqActivity.class);
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

    private void selectZhanDianLIst() {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI + "/prod-api/api/metro/step/list", "");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZhanDIanLIstBean zhanDIanLIstBean = new Gson().fromJson(string, ZhanDIanLIstBean.class);
                List<ZhanDIanLIstBean.DataDTO> data = zhanDIanLIstBean.getData();
                int index = 0;
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getName().equals("建国门")){
                        index=i;
                    }
                }
                int finalIndex = index;
                int finalIndex1 = index;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSpinner.setAdapter(new MyBaseAdapter<ZhanDIanLIstBean.DataDTO>(data, R.layout.item, data.size()) {
                            private TextView mTv;

                            @Override
                            public void converView(ViewHolde viewHolde, ZhanDIanLIstBean.DataDTO dataDTO, int i) {
                                mTv = viewHolde.getVIew(R.id.tv);
                                mTv.setText(dataDTO.getName());
                            }
                        });
                        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String name = data.get(i).getName();
                                mTvTitle.setText(name);
                                showLInsListVIew(name);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        mSpinner.setSelection(finalIndex1);

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
        return R.layout.activity_csdt;
    }
}
