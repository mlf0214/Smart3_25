package com.example.smart3_25.ui.fragment;

import android.text.Html;
import android.util.Log;
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
import com.example.smart3_25.base.BaseFragment;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.home.BannerBean;
import com.example.smart3_25.bean.home.NewLIstBean;
import com.example.smart3_25.bean.home.NewsTabBean;
import com.example.smart3_25.bean.home.ServerBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.ui.activty.csdt.CHengshiDiTieeActivity;
import com.example.smart3_25.ui.activty.home.MainActivity;
import com.example.smart3_25.ui.activty.home.NewsXQActivity;
import com.example.smart3_25.ui.activty.zfz.ZhangfangziZYActivity;
import com.example.smart3_25.ui.activty.zgz.ZhaoGongZuoActivity;
import com.example.smart3_25.util.adapter.MyBaseAdapter;
import com.example.smart3_25.util.adapter.ViewHolde;
import com.example.smart3_25.util.myview.MyGridVIew;
import com.example.smart3_25.util.myview.MyListVIew;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class Fragment1 extends BaseFragment {
    private static final String TAG = "Fragment1";
    private ImageView mLeftIcon;
    private TextView mTitle;
    private SearchView mSv;
    private Banner mBanner;
    private MyGridVIew mMv;
    private MyListVIew mNewsLv;
    private RadioButton mRb1;
    private RadioButton mRb2;
    private RadioButton mRb3;
    private RadioButton mRb4;
    private RadioButton mRb5;
    private RadioButton mRb6;
    private RadioGroup mRg;
    private int newId;
    private MainActivity mAinActivity= (MainActivity) mActiivty;

    @Override
    protected void initView() {

        mLeftIcon = getView().findViewById(R.id.left_icon);
        mTitle = getView().findViewById(R.id.title);
        mSv = getView().findViewById(R.id.sv);
        mBanner = getView().findViewById(R.id.banner);
        mMv = getView().findViewById(R.id.mv);
        mNewsLv = getView().findViewById(R.id.news_lv);
        mTitle.setText("主页");
//                mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
        mRb1 = getView().findViewById(R.id.rb1);
        mRb2 = getView().findViewById(R.id.rb2);
        mRb3 = getView().findViewById(R.id.rb3);
        mRb4 = getView().findViewById(R.id.rb4);
        mRb5 = getView().findViewById(R.id.rb5);
        mRb6 = getView().findViewById(R.id.rb6);
        mRg = getView().findViewById(R.id.rg);
    }
    @Override
    protected void initData() {
        setBanner();
        setServer();
        setNewsTAb();
        setNewLIst("9");
        mRb1.setChecked(true);

    }

    private void setNewsTAb() {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(mActiivty);
        oKhttpUtil.setget(User.URI + "/prod-api/press/category/list", "");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Log.d(TAG,string);
                NewsTabBean newsTabBean = new Gson().fromJson(string, NewsTabBean.class);
                List<NewsTabBean.DataDTO> data = newsTabBean.getData();
                List<String> tabName = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    String name = data.get(i).getName();
                    tabName.add(name);
                }
                mActiivty.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRb1.setText(tabName.get(0));
                        mRb2.setText(tabName.get(1));
                        mRb3.setText(tabName.get(2));
                        mRb4.setText(tabName.get(3));
                        mRb5.setText(tabName.get(4));
                        mRb6.setText(tabName.get(5));
                    }
                });

                mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        int i1 = mRg.indexOfChild(mRg.findViewById(i));
                        Toast.makeText(mActiivty, ""+i1, Toast.LENGTH_SHORT).show();
                        int id = data.get(i1).getId();
                        String s = String.valueOf(id);
                        setNewLIst(s);
                    }
                });
            }

            @Override
            public void getString(String s) {

            }
        });
    }

    private void setNewLIst(String id) {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(mActiivty);
        oKhttpUtil.setget(User.URI + "/prod-api/press/press/list?type="+id, "");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                NewLIstBean newLIstBean = new Gson().fromJson(string, NewLIstBean.class);
                List<NewLIstBean.RowsDTO> rows = newLIstBean.getRows();
                mActiivty.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mNewsLv.setAdapter(new MyBaseAdapter<NewLIstBean.RowsDTO>(rows, R.layout.new_list, rows.size()) {
                            private TextView mTv4;
                            private TextView mTv3;
                            private TextView mTvContent;
                            private TextView mTvTitle;
                            private ImageView mIv;

                            @Override
                            public void converView(ViewHolde viewHolde, NewLIstBean.RowsDTO rowsDTO, int i) {
                                mIv =        viewHolde.getVIew(R.id.iv);
                                mTvTitle =   viewHolde.getVIew(R.id.tv_title);
                                mTvContent = viewHolde.getVIew(R.id.tv_content);
                                mTv3 =       viewHolde.getVIew(R.id.tv3);
                                mTv4 =       viewHolde.getVIew(R.id.tv4);
                                mIv.setScaleType(ImageView.ScaleType.FIT_XY);
                                setIMage(mActiivty,User.URI+rowsDTO.getCover(),mIv);
                                mTvTitle.setText(rowsDTO.getTitle());
                                mTvContent.setText("\t\t"+Html.fromHtml(rowsDTO.getContent()));
                                mTv3.setText("评论总数:"+rowsDTO.getCommentNum());
                                mTv4 .setText("发布时间:"+rowsDTO.getPublishDate());
                            }
                        });
                        mNewsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                newId= rows.get(i).getId();
//                                Messg messg = EventBus.getDefault().removeStickyEvent(Messg.class);
                                Messg messg1=new Messg();
                                messg1.setNewId(String.valueOf(newId));
                                EventBus.getDefault().postSticky(messg1);
//                                startActivity(new Intent(mActiivty, NewsXQActivity.class));
                                goActvity(NewsXQActivity.class);
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

    private void setServer() {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(mActiivty);
        oKhttpUtil.setget(User.URI + "/prod-api/api/service/list", "");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                ServerBean serverBean = new Gson().fromJson(s, ServerBean.class);
                List<ServerBean.RowsDTO> rows = serverBean.getRows();
                ServerBean.RowsDTO zfz = new ServerBean.RowsDTO();
                ServerBean.RowsDTO zgz = new ServerBean.RowsDTO();
                for (int i = 0; i < rows.size(); i++) {
                    if (rows.get(i).getServiceName().equals("找房子")) {
                        zfz.setServiceName("找房子");
                        zfz.setImgUrl(rows.get(i).getImgUrl());
                    }
                    if (rows.get(i).getServiceName().equals("找工作")) {
                        zgz.setServiceName("找工作");
                        zgz.setImgUrl(rows.get(i).getImgUrl());
                    }
                }
                mActiivty.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMv.setNumColumns(4);
                        mMv.setAdapter(new MyBaseAdapter<ServerBean.RowsDTO>(rows, R.layout.gv_sy, 8) {
                            private TextView mTv;
                            private ImageView mIv;

                            @Override
                            public void converView(ViewHolde viewHolde, ServerBean.RowsDTO rowsDTO, int i) {
                                mIv = viewHolde.getVIew(R.id.iv);
                                mTv = viewHolde.getVIew(R.id.tv);
                                mIv.setMinimumWidth(x / 6);
                                mIv.setMinimumHeight(x / 6);
                                switch (i) {
                                    case 1:
                                        mTv.setText("城市地铁");
                                        setIMage(mActiivty, User.URI + rows.get(1).getImgUrl(), mIv);

                                        break;
                                    case 0:
                                        setIMage(mActiivty, R.mipmap.tab1, mIv);
                                        mTv.setText("首页");
                                        break;
                                    case 2:
                                        setIMage(mActiivty, R.mipmap.tab5, mIv);
                                        mTv.setText("个人中心");
                                        break;
                                    case 3:
                                        setIMage(mActiivty, R.mipmap.tab4, mIv);

                                        mTv.setText("数据分析");
                                        break;
                                    case 4:
                                        setIMage(mActiivty, User.URI + zfz.getImgUrl(), mIv);
                                        mTv.setText(zfz.getServiceName());

                                        break;
                                    case 5:
                                        setIMage(mActiivty, User.URI + zgz.getImgUrl(), mIv);
                                        mTv.setText(zgz.getServiceName());
                                        break;
                                    case 6:
                                        setIMage(mActiivty, R.mipmap.tab3, mIv);

                                        mTv.setText("智慧社区");
                                        break;
                                    case 7:
                                        setIMage(mActiivty, R.drawable.ic_baseline_add_24, mIv);
                                        mTv.setText("更多服务");
                                        break;
                                }
                            }
                        });
                        mMv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                mAinActivity= (MainActivity) mActiivty;
                                switch (i){
                                    case 0:
                                        Toast.makeText(mActiivty, "当前页面", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
                                        Toast.makeText(mActiivty, "城市地铁", Toast.LENGTH_SHORT).show();
                                        goActvity(CHengshiDiTieeActivity.class);
                                        break;
                                    case 2:
                                        mAinActivity.setTab(4);
                                        Toast.makeText(mActiivty, "个人中心", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 3:
                                        mAinActivity.setTab(3);

                                        Toast.makeText(mActiivty, "数据分析", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 4:
                                        Toast.makeText(mAinActivity, "找房子", Toast.LENGTH_SHORT).show();
                                        goActvity(ZhangfangziZYActivity.class);
                                        break;
                                    case 5:
                                        Toast.makeText(mActiivty, "找工作", Toast.LENGTH_SHORT).show();
                                        goActvity(ZhaoGongZuoActivity.class);

                                        break;
                                    case 6:
                                        mAinActivity.setTab(2);

                                        Toast.makeText(mActiivty, "智慧社区", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 7:
                                        mAinActivity.setTab(1);

                                        Toast.makeText(mActiivty, "更多服务", Toast.LENGTH_SHORT).show();
                                        break;
                                }
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

    private void setBanner() {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(mActiivty);
        oKhttpUtil.setget(User.URI + "/prod-api/api/rotation/list?type=2", "");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                mActiivty.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BannerBean bean = new Gson().fromJson(string, BannerBean.class);
                        List<BannerBean.RowsDTO> rows = bean.getRows();
                        List<String> imgs = new ArrayList<>();
                        for (int i = 0; i < rows.size(); i++) {
                            String advImg = rows.get(i).getAdvImg();
                            imgs.add(User.URI + advImg);
                        }
                        mBanner.setAdapter(new BannerImageAdapter<BannerBean.RowsDTO>(rows) {
                            @Override
                            public void onBindView(BannerImageHolder bannerImageHolder, BannerBean.RowsDTO rowsDTO, int i, int i1) {
                                Glide.with(mActiivty).load(User.URI + rowsDTO.getAdvImg()).into(bannerImageHolder.imageView);
                            }
                        }).start();
                        mBanner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(Object o, int i) {
                                newId = rows.get(i).getTargetId();
                                Messg messg=new Messg();
                                messg.setNewId(String.valueOf(newId));
                                EventBus.getDefault().postSticky(messg);
                                goActvity(NewsXQActivity.class);
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
    protected void ininEvent() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_1;
    }
}
