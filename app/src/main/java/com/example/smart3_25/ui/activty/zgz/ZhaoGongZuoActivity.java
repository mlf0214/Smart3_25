package com.example.smart3_25.ui.activty.zgz;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.zgz.ZwListBean;
import com.example.smart3_25.bean.zgz.ZwZpList;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.Shape;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.ui.ShowDiag;
import com.example.smart3_25.util.adapter.MyBaseAdapter;
import com.example.smart3_25.util.adapter.ViewHolde;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ZhaoGongZuoActivity extends BaseActivity {
    private androidx.appcompat.widget.SearchView mSv;
    private com.youth.banner.Banner mBanner;
    private com.example.smart3_25.util.myview.MyGridVIew mMv;
    private com.example.smart3_25.util.myview.MyListVIew mNewsLv;
    private ImageView mLeftIcon;
    private TextView mTitle;
    private ImageView mRightIcon;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {

        mSv = findViewById(R.id.sv);
        mBanner = findViewById(R.id.banner);
        mMv = findViewById(R.id.mv);
        mNewsLv = findViewById(R.id.news_lv);


        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mRightIcon = findViewById(R.id.right_icon);

        mTitle.setText("主页");
        mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
    }

    @Override
    protected void initData() {
        selectGvZw();
        selectMlvZWList("");
    }

    private void selectGvZw() {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI + "/prod-api/api/job/profession/list", Shape.getValue("token", ""));
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZwListBean zwListBean = new Gson().fromJson(string, ZwListBean.class);
                if (zwListBean.getMsg().contains("认证失败")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ShowDiag.showToken(ZhaoGongZuoActivity.this);
                        }
                    });
                } else if (zwListBean.getCode().equals("200")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<ZwListBean.RowsDTO> rows = zwListBean.getRows();
                            mMv.setAdapter(new MyBaseAdapter<ZwListBean.RowsDTO>(rows, R.layout.item_zw, 9) {
                                private TextView mTv;

                                @Override
                                public void converView(ViewHolde viewHolde, ZwListBean.RowsDTO rowsDTO, int i) {
                                    mTv = viewHolde.getVIew(R.id.tv);
                                    mTv.setText(rowsDTO.getProfessionName());
                                }
                            });
                            mMv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    String professionName = rows.get(i).getProfessionName();
                                    selectMlvZWList(professionName);
                                }
                            });

                        }
                    });

                }

            }

            @Override
            public void getString(String s) {

            }


        });
    }

    private void selectMlvZWList(String professionName) {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI + "/prod-api/api/job/post/list?name=" + professionName, "");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZwZpList zwZpList = new Gson().fromJson(string, ZwZpList.class);
                List<ZwZpList.RowsDTO> rows = zwZpList.getRows();
                //信息包括职位名称、岗位职责、公司地点、薪资待遇等
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mNewsLv.setAdapter(new MyBaseAdapter<ZwZpList.RowsDTO>(rows, R.layout.zw_list, rows.size()) {
                            private TextView mTv4;
                            private TextView mTv3;
                            private TextView mTvContent;
                            private TextView mTvTitle;
                            private ImageView mIv;
                            @Override
                            public void converView(ViewHolde viewHolde, ZwZpList.RowsDTO rowsDTO, int i) {
                                mTvTitle =   viewHolde.getVIew(R.id.tv_title);
                                mTvContent = viewHolde.getVIew(R.id.tv_content);
                                mTv3 =       viewHolde.getVIew(R.id.tv3);
                                mTv4 =       viewHolde.getVIew(R.id.tv4);
                                mTvTitle.setText(rowsDTO.getName());
                                mTvContent.setText(rowsDTO.getObligation());
                                mTv3.setText("公司地点"+rowsDTO.getAddress());
                                mTv4.setText("薪资待遇:"+rowsDTO.getSalary());
                            }
                        });
                        mNewsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                int id = rows.get(i).getId();
                                Messg messg=new Messg();
                                messg.setSongsiName(String.valueOf(rows.get(i).getCompanyId()));
                                messg.setZgzId(String.valueOf(id));
                                goActvity(ZhaoGongzuoXqActivity.class);
                                EventBus.getDefault().postSticky(messg);
                            }
                        });
                        if (rows.size()==0){
                            Toast.makeText(ZhaoGongZuoActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
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
        mSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSv.setIconified(true);
                selectSousuoMlvZWList(query);
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
                selectMlvZWList("");
                return false;
            }
        });
    }

    private void selectSousuoMlvZWList(String s) {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI + "/prod-api/api/job/post/list", "");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZwZpList zwZpList = new Gson().fromJson(string, ZwZpList.class);
                List<ZwZpList.RowsDTO> rows = zwZpList.getRows();
                List<ZwZpList.RowsDTO> rows1 = new ArrayList<>();
                for (int i = 0; i < rows.size(); i++) {
                    if (rows.get(i).getCompanyName().contains(s)){
                        rows1.add(rows1.get(i));
                    }
                }
                //信息包括职位名称、岗位职责、公司地点、薪资待遇等
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mNewsLv.setAdapter(new MyBaseAdapter<ZwZpList.RowsDTO>(rows1, R.layout.zw_list, rows1.size()) {
                            private TextView mTv4;
                            private TextView mTv3;
                            private TextView mTvContent;
                            private TextView mTvTitle;
                            private ImageView mIv;
                            @Override
                            public void converView(ViewHolde viewHolde, ZwZpList.RowsDTO rowsDTO, int i) {
                                mTvTitle =   viewHolde.getVIew(R.id.tv_title);
                                mTvContent = viewHolde.getVIew(R.id.tv_content);
                                mTv3 =       viewHolde.getVIew(R.id.tv3);
                                mTv4 =       viewHolde.getVIew(R.id.tv4);
                                mTvTitle.setText(rowsDTO.getName());
                                mTvContent.setText(rowsDTO.getObligation());
                                mTv3.setText("公司地点"+rowsDTO.getAddress());
                                mTv4.setText("薪资待遇:"+rowsDTO.getSalary());
                            }
                        });
                        mNewsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                int id = rows1.get(i).getId();
                                Messg messg=new Messg();
                                messg.setSongsiName(String.valueOf(rows1.get(i).getCompanyId()));
                                messg.setZgzId(String.valueOf(id));
                                goActvity(ZhaoGongzuoXqActivity.class);
                                EventBus.getDefault().postSticky(messg);
                            }
                        });
                        if (rows1.size()==0){
                            Toast.makeText(ZhaoGongZuoActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void getString(String s) {

            }


        });
    }

    //1.找工作页面，具备宣传幻灯片、职位搜索、热门职位和职位列表显示功能。
    @Override
    protected int getLayoutId() {
        return R.layout.activity_zgz;
    }
}
