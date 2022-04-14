package com.example.smart3_25.ui.activty.csdt;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.csdt.LIstXqBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.util.adapter.MyBaseAdapter;
import com.example.smart3_25.util.adapter.ViewHolde;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ListXqActivity extends BaseActivity {
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.TextView mTvFirst;
    private android.widget.TextView mTvEnd;
    private android.widget.TextView mTvDate;
    private android.widget.TextView mTvKm;
    private com.example.smart3_25.util.myview.MyGridVIew mMv;
    private String lineId;
    private ImageView mRightIcon;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {

        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mTvFirst = findViewById(R.id.tv_first);
        mTvEnd = findViewById(R.id.tv_end);
        mTvDate = findViewById(R.id.tv_date);
        mTvKm = findViewById(R.id.tv_km);
        mMv = findViewById(R.id.mv);
                mTitle.setText("站点详情");
                mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
        mRightIcon = findViewById(R.id.right_icon);
        mRightIcon.setBackgroundResource(R.drawable.ic_baseline_face_24);
    }

    @Override
    protected void initData() {
        selectXQ(lineId);
    }

    private void selectXQ(String lineId) {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI + "/prod-api/api/metro/line/" + lineId, "");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                LIstXqBean lIstXqBean = new Gson().fromJson(string, LIstXqBean.class);
                List<LIstXqBean.DataDTO.MetroStepListDTO> list = lIstXqBean.getData().getMetroStepList();
                LIstXqBean.DataDTO data = lIstXqBean.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String endTime = data.getEndTime();
                        String first = data.getFirst();
                        int km = data.getKm();
                        String end = data.getEnd();
                        mTvEnd.setText(end);
                        mTvFirst.setText(first);
                        mTvKm.setText("" + km+"KM");
                        mTvDate.setText(endTime);
                        mMv.setAdapter(new MyBaseAdapter<LIstXqBean.DataDTO.MetroStepListDTO>(list, R.layout.zd_gv, list.size()) {
                            private TextView mTv;
                            private ImageView mYuan;
                            @Override
                            public void converView(ViewHolde viewHolde, LIstXqBean.DataDTO.MetroStepListDTO metroStepListDTO, int i) {
                                mYuan = viewHolde.getVIew(R.id.yuan);
                                mTv =   viewHolde.getVIew(R.id.tv);
                                if (data.getRunStationsName().equals(metroStepListDTO.getName())){
                                    mYuan.setSelected(true);
                                }
                                mTv.setText(metroStepListDTO.getName());
                            }
                        });
                        mMv.setNumColumns(list.size());
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
          mRightIcon.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  goActvity(ZLTActivity.class);
              }
          });
    }

    @Override
    public void setMessg(Messg messg) {
        lineId = messg.getLineId();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_listxq;
    }
}
