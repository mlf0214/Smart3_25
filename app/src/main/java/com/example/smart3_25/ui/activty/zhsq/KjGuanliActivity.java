package com.example.smart3_25.ui.activty.zhsq;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.zhsq.FjKdBean;
import com.example.smart3_25.bean.zhsq.KdBean;
import com.example.smart3_25.util.Date;
import com.example.smart3_25.util.MyApplication;
import com.example.smart3_25.util.adapter.MyBaseAdapter;
import com.example.smart3_25.util.adapter.ViewHolde;

import java.util.ArrayList;
import java.util.List;

public class KjGuanliActivity extends BaseActivity {
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.ImageView mRightIcon;
    private com.example.smart3_25.util.myview.MyGridVIew mMgv;
    private com.example.smart3_25.util.myview.MyListVIew mMlv;
    private String[] kdname, spname, dates, fjkdnames, kms, yingyedates;
    private int[] imgs;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {
        //3.快件管理：首页设置配送服务功能入口，
        // 点击跳转配送服务页面，显示社区覆盖的快递点的详情，
        // 以及已经代签的包裹信息，支持去指定接收点扫码取快递。
        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mRightIcon = findViewById(R.id.right_icon);
        mMgv = findViewById(R.id.mgv);
        mMlv = findViewById(R.id.mlv);
        mTitle.setText("快件管理");
        mLeftIcon.setImageResource(R.drawable.arrow_back_ios_24);
    }

    @Override
    protected void initData() {
        setQIanshouDAta();
        setfJIkdData();
    }

    private void setfJIkdData() {
        fjkdnames = new String[]{"顺丰快递", "韵达快递", "菜鸟驿站", "合作社"};
        kms = new String[]{"1.0km", "2.5km", "0.5km", "2.0km"};
        yingyedates = new String[]{"8:00-11:00", "10:00-24:00", "8:30-12:00", "9:00-11:30"};
        imgs = new int[]{R.drawable.wy_iv, R.drawable.wy_iv, R.drawable.wy_iv, R.drawable.wy_iv};
        List<FjKdBean> fjKdBeans = new ArrayList<>();
        for (int i = 0; i < fjkdnames.length; i++) {
            FjKdBean fjKdBean = new FjKdBean();
            fjKdBean.setDate(yingyedates[i]);
            fjKdBean.setFjkdname(fjkdnames[i]);
            fjKdBean.setKm(kms[i]);
            fjKdBean.setImgs(imgs[i]);
            fjKdBeans.add(fjKdBean);

        }
        mMlv.setAdapter(new MyBaseAdapter<FjKdBean>(fjKdBeans, R.layout.new_list, fjKdBeans.size()) {
            private TextView mTv4;
            private TextView mTv3;
            private TextView mTvContent;
            private TextView mTvTitle;
            private ImageView mIv;

            @Override
            public void converView(ViewHolde viewHolde, FjKdBean fjKdBean, int i) {
                mIv =        viewHolde.getVIew(R.id.iv);
                mTvTitle =   viewHolde.getVIew(R.id.tv_title);
                mTvContent = viewHolde.getVIew(R.id.tv_content);
                mTv3 =       viewHolde.getVIew(R.id.tv3);
                mTv4 =       viewHolde.getVIew(R.id.tv4);
                mTvTitle.setText(fjKdBean.getFjkdname());
                mIv.setScaleType(ImageView.ScaleType.FIT_XY);
                mTv3.setText("营业时间:"+fjKdBean.getDate());
                mTvContent.setText(fjKdBean.getFjkdname()+"带给您最真诚的服务");
                mTv4.setText("距您:"+fjKdBean.getKm());
                Glide.with(MyApplication.getContext()).load(fjKdBean.getImgs()).into(mIv);
            }
        });
    }

    private void setQIanshouDAta() {
        kdname = new String[]{"韵达快递", "顺丰快递", "圆通速递"};
        spname = new String[]{"iphone10", "神舟笔记本", "信阳毛尖"};
        dates = new String[]{Date.getTHisDate(), Date.getTHisDate(), Date.getTHisDate()};
        List<KdBean> kdBeans = new ArrayList<>();
        for (int i = 0; i < kdname.length; i++) {
            KdBean kdBean = new KdBean();
            kdBean.setDate(dates[i]);
            kdBean.setKdName(kdname[i]);
            kdBean.setSpName(spname[i]);
            kdBeans.add(kdBean);
        }
        mMgv.setAdapter(new MyBaseAdapter<KdBean>(kdBeans, R.layout.kd_qs_gv, kdBeans.size()) {
            private TextView mTv3;
            private TextView mTv2;
            private TextView mTv1;

            @Override
            public void converView(ViewHolde viewHolde, KdBean kdBean, int i) {
                mTv1 = viewHolde.getVIew(R.id.tv1);
                mTv2 = viewHolde.getVIew(R.id.tv2);
                mTv3 = viewHolde.getVIew(R.id.tv3);
                mTv1.setText(kdBean.getKdName());
                mTv2.setText(kdBean.getSpName());
                mTv3.setText(kdBean.getDate());
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
        return R.layout.activity_kjgl;
    }
}
