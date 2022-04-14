package com.example.smart3_25.ui.activty.zfz;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.bumptech.glide.Glide;
import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.zfz.FangYUanLIst;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.util.MyApplication;
import com.example.smart3_25.util.adapter.MyBaseAdapter;
import com.example.smart3_25.util.adapter.ViewHolde;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ZhangfangziZYActivity extends BaseActivity {
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.ImageView mRightIcon;
    private androidx.appcompat.widget.SearchView mSv;
    private com.youth.banner.Banner mBanner;
    private android.widget.RadioButton mRb1;
    private android.widget.RadioButton mRb2;
    private android.widget.RadioButton mRb3;
    private android.widget.RadioButton mRb4;
    private com.example.smart3_25.util.myview.MyListVIew mMlv;
    private android.widget.RadioGroup mRg;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {
        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mRightIcon = findViewById(R.id.right_icon);
        mSv = findViewById(R.id.sv);
        mBanner = findViewById(R.id.banner);
        mRb1 = findViewById(R.id.rb1);
        mRb2 = findViewById(R.id.rb2);
        mRb3 = findViewById(R.id.rb3);
        mRb4 = findViewById(R.id.rb4);
        mMlv = findViewById(R.id.mlv);
        mRg = findViewById(R.id.rg);

                mTitle.setText("主页");
                mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
    }

    @Override
    protected void initData() {
        selectFyuanLIstTYpe("");
        selectFyuanLIstName("");


    }

    private void selectFyuanLIstName(String name) {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI + "/prod-api/api/house/housing/list", "");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                FangYUanLIst fangYUanLIst = new Gson().fromJson(string, FangYUanLIst.class);
                List<FangYUanLIst.RowsDTO> rows = fangYUanLIst.getRows();
                List<FangYUanLIst.RowsDTO> rowsDTOS=new ArrayList<>();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < rows.size(); i++) {
                            if (rows.get(i).getSourceName().contains(name)){
                                rowsDTOS.add(rows.get(i));
                            }
                        }
                        mMlv.setAdapter(new MyBaseAdapter<FangYUanLIst.RowsDTO>(rowsDTOS, R.layout.new_list, rowsDTOS.size()) {
                            private TextView mTv4;
                            private TextView mTv3;
                            private TextView mTvContent;
                            private TextView mTvTitle;
                            private ImageView mIv;
                            @Override
                            public void converView(ViewHolde viewHolde, FangYUanLIst.RowsDTO rowsDTO, int i) {
                                mIv =        viewHolde.getVIew(R.id.iv);
                                mTvTitle =   viewHolde.getVIew(R.id.tv_title);
                                mTvContent = viewHolde.getVIew(R.id.tv_content);
                                mTv3 =       viewHolde.getVIew(R.id.tv3);
                                mTv4 =       viewHolde.getVIew(R.id.tv4);
                                Glide.with(MyApplication.getContext()).load(User.URI+rowsDTO.getPic()).into(mIv);
                                mTvTitle.setText(rowsDTO.getAddress());
                                mTvContent.setText(rowsDTO.getDescription());
                                mTv3.setText("房源面积:"+rowsDTO.getAreaSize());
                                mTv4.setText("价格:"+rowsDTO.getPrice());
                            }
                        });
                        mMlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                int id = rowsDTOS.get(i).getId();
                                Messg messg=new Messg();
                                messg.setFyId(String.valueOf(id));
                                EventBus.getDefault().postSticky(messg);
                                goActvity(ZFZxqActivity.class);
                            }
                        });
                        if (rowsDTOS.size()==0){
                            Toast.makeText(ZhangfangziZYActivity.this, "没有数据", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void getString(String s) {
            }
        });
    }

    private void selectFyuanLIstTYpe(String s) {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI + "/prod-api/api/house/housing/list?houseType=" + s, "");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                FangYUanLIst fangYUanLIst = new Gson().fromJson(string, FangYUanLIst.class);
                List<FangYUanLIst.RowsDTO> rows = fangYUanLIst.getRows();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMlv.setAdapter(new MyBaseAdapter<FangYUanLIst.RowsDTO>(rows, R.layout.new_list, rows.size()) {
                            private TextView mTv4;
                            private TextView mTv3;
                            private TextView mTvContent;
                            private TextView mTvTitle;
                            private ImageView mIv;
                            @Override
                            public void converView(ViewHolde viewHolde, FangYUanLIst.RowsDTO rowsDTO, int i) {
                                mIv =        viewHolde.getVIew(R.id.iv);
                                mTvTitle =   viewHolde.getVIew(R.id.tv_title);
                                mTvContent = viewHolde.getVIew(R.id.tv_content);
                                mTv3 =       viewHolde.getVIew(R.id.tv3);
                                mTv4 =       viewHolde.getVIew(R.id.tv4);
                                Glide.with(MyApplication.getContext()).load(User.URI+rowsDTO.getPic()).into(mIv);
                                mTvTitle.setText(rowsDTO.getAddress());
                                mTvContent.setText(rowsDTO.getDescription());
                                mTv3.setText("房源面积:"+rowsDTO.getAreaSize());
                                mTv4.setText("价格:"+rowsDTO.getPrice());
                            }
                        });
                        mMlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                int id = rows.get(i).getId();
                                Messg messg=new Messg();
                                messg.setFyId(String.valueOf(id));
                                EventBus.getDefault().postSticky(messg);
                                goActvity(ZFZxqActivity.class);
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
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton rb = findViewById(checkedRadioButtonId);
                setMbTa(rb);
            }
        });
        mRb1.setChecked(true);
        mSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSv.setIconified(true);
                selectFyuanLIstName(query);
                mRg.setVisibility(View.GONE);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        mSv.setIconified(false);
        mSv.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                selectFyuanLIstTYpe("");
                mRg.setVisibility(View.VISIBLE);
                Toast.makeText(ZhangfangziZYActivity.this, "搜索框被关闭", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void setMbTa(RadioButton mrb) {
        if (mrb.isChecked()) {
            selectFyuanLIstTYpe(mrb.getText().toString());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zfz;
    }
}
