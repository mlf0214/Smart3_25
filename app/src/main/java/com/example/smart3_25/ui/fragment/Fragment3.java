package com.example.smart3_25.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseFragment;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.home.ServerBean;
import com.example.smart3_25.bean.zhsq.HdTgBean;
import com.example.smart3_25.bean.zhsq.TzBean;
import com.example.smart3_25.ui.activty.zhsq.CLGLActivity;
import com.example.smart3_25.ui.activty.zhsq.KjGuanliActivity;
import com.example.smart3_25.ui.activty.zhsq.TZxqActivity;
import com.example.smart3_25.ui.activty.zhsq.WYFwActivity;
import com.example.smart3_25.ui.activty.zhsq.YOuLInshejiaoActivity;
import com.example.smart3_25.util.Date;
import com.example.smart3_25.util.adapter.MyBaseAdapter;
import com.example.smart3_25.util.adapter.ViewHolde;
import com.example.smart3_25.util.myview.MyGridVIew;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment3 extends BaseFragment {
    private ImageView mLeftIcon;
    private TextView mTitle;
    private ImageView mRightIcon;
    private ViewPager mVp;
    private ListView mLv;
    private String[] titles, contents, dates;
    private MyGridVIew mMgv;
    private MyGridVIew mServerMgv;

    @Override
    protected void initView() {

        mLeftIcon = getView().findViewById(R.id.left_icon);
        mTitle = getView().findViewById(R.id.title);
        mRightIcon = getView().findViewById(R.id.right_icon);

        mLv = getView().findViewById(R.id.lv);

        mTitle.setText("智慧社区");
        mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
        mMgv = getView().findViewById(R.id.mgv);
        mServerMgv = getView().findViewById(R.id.server_mgv);
    }

    private int index = -1;

    @Override
    protected void initData() {
        titles = new String[]{"社区居委会通知:", "物业通知:", "社区居委会通知:"};
        contents = new String[]{"我爱重庆我爱重庆我爱重庆我爱重庆" +
                "我爱重庆我爱重庆我爱重庆我爱重庆" +
                "我爱重庆我爱重庆我爱重庆我爱重庆我爱重庆" +
                "我爱重庆我爱重庆我爱重庆我爱重庆" +
                "我爱重庆我爱重庆我爱重庆我爱重庆我爱重庆" +
                "", "我爱中国我爱中国我爱中国我爱中国" +
                "我爱中国我爱中国我爱中国我爱中国我爱中国我爱中国我爱中国我爱中国我爱中国我爱中国" +
                "我爱中国我爱中国我爱中国我爱中国我爱中国我爱中国我爱中国我爱中国我爱中国" +
                "我爱中国我爱中国我爱中国" +
                "", "\t\tohohohohohohohohohohohohohohohohohohohohohohohohohohohohoh" +
                "ohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohoh" +
                "ohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohoh"};
        dates = new String[]{Date.getTHisDate(), Date.getTHisDate(), Date.getTHisDate()};
        List<TzBean> tzBeans = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            TzBean tzBean = new TzBean();
            tzBean.setTitle(titles[i]);
            tzBean.setContent(contents[i]);
            tzBean.setDate(dates[i]);
            tzBeans.add(tzBean);
        }
//        mLv.setAdapter(new MyBaseAdapter()<TzBean>(R.layout.tz_zhsq_lv, tzBeans) {
//            private TextView mTv3;
//            private TextView mTv2;
//            private TextView mTv1;
//
//            @Override
//            protected void conview(ViewHolde vIewHolde, TzBean tzBean, int i) {
//                mTv1 = vIewHolde.getVIew(R.id.tv1);
//                mTv2 = vIewHolde.getVIew(R.id.tv2);
//                mTv3 = vIewHolde.getVIew(R.id.tv3);
//                mTv1.setText(tzBean.getTitle());
//                mTv2.setText(tzBean.getContent());
//                mTv3.setText(tzBean.getDate());
//            }
//        });

        mLv.setAdapter(new MyBaseAdapter<TzBean>(tzBeans, R.layout.tz_zhsq_lv, tzBeans.size()) {
            private TextView mTv3;
            private TextView mTv2;
            private TextView mTv1;

            @Override
            public void converView(ViewHolde viewHolde, TzBean tzBean, int i) {

                mTv1 = viewHolde.getVIew(R.id.tv1);
                mTv2 = viewHolde.getVIew(R.id.tv2);
                mTv3 = viewHolde.getVIew(R.id.tv3);
                mTv1.setText(tzBean.getTitle());
                mTv2.setText("\t\t" + tzBean.getContent());
                mTv3.setText(tzBean.getDate());
            }
        });
//        final int[] index = {0};
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mActiivty.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        index += 1;
                        if (index >= mLv.getCount()) {
                            index = 0;
                        }
                        mLv.smoothScrollToPosition(index);
//                        mLv.smoothScrollBy(5,0);
                    }
                });

            }
        }, 0, 2000);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mActiivty, "" + i, Toast.LENGTH_SHORT).show();
                String date = tzBeans.get(i).getDate();
                String title = tzBeans.get(i).getTitle();
                Messg messg = new Messg();
                messg.setDate(date);
                messg.setTzcontent(tzBeans.get(i).getContent());
                messg.setTzTitile(title);
                messg.settZId(String.valueOf(i));
                EventBus.getDefault().postSticky(messg);
                goActvity(TZxqActivity.class);

            }
        });
        setServer();
        setHdTg();

    }

    private void setHdTg() {
        String[] names, titile;
        List<HdTgBean> hdTgBeans = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            HdTgBean hdTgBean = new HdTgBean();
            hdTgBean.setContent("天津狗不理，让你的包子有去无回");
            hdTgBean.setName("天津狗不理包子");
            hdTgBean.setImg(R.drawable.ic_launcher_foreground);
            hdTgBeans.add(hdTgBean);
        }


        mMgv.setAdapter(new MyBaseAdapter<HdTgBean>(hdTgBeans, R.layout.zhsq_hdtg, hdTgBeans.size()) {
            private TextView mTvContent;
            private TextView mTvName;
            private ImageView mIv;
            @Override
            public void converView(ViewHolde viewHolde, HdTgBean hdTgBean, int i) {
                mIv =         viewHolde.getVIew(R.id.iv);
                mTvName =     viewHolde.getVIew(R.id.tv_name);
                mTvContent =  viewHolde.getVIew(R.id.tv_content);
                setIMage(mActiivty,hdTgBean.getImg(),mIv);
                mTvName.setText(hdTgBean.getName());
                mTvContent.setText(hdTgBean.getContent());

            }
        });
        mMgv.setNumColumns(2);
    }

    private String[] servers = new String[]{"物业服务", "快件管理", "友邻社交", "车辆管理"};

    private void setServer() {
        List<ServerBean.RowsDTO> rowsDTOList = new ArrayList<>();
        for (int i = 0; i < servers.length; i++) {
            ServerBean.RowsDTO rowsDTO = new ServerBean.RowsDTO();
            rowsDTO.setServiceName(servers[i]);
            rowsDTO.setId(R.drawable.ic_launcher_foreground);
            rowsDTOList.add(rowsDTO);
        }


        mServerMgv.setAdapter(new MyBaseAdapter<ServerBean.RowsDTO>(rowsDTOList, R.layout.gv_sy, rowsDTOList.size()) {

            private TextView mTv;
            private ImageView mIv;

            @Override
            public void converView(ViewHolde viewHolde, ServerBean.RowsDTO rowsDTO, int i) {
                mIv = viewHolde.getVIew(R.id.iv);
                mTv = viewHolde.getVIew(R.id.tv);
                setIMage(mActiivty, rowsDTO.getId(), mIv);
                mTv.setText(rowsDTO.getServiceName());
                mIv.setMinimumWidth(x/3);
                mIv.setMinimumHeight(x/3);
            }
        });
    }

    @Override
    protected void ininEvent() {
        mServerMgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String server = servers[i];
                Toast.makeText(mActiivty, server, Toast.LENGTH_SHORT).show();
                switch (server){
                    case "物业服务":
                        goActvity(WYFwActivity.class);
                        break;
                    case "快件管理":
                        goActvity(KjGuanliActivity.class);
                        break;
                    case "友邻社交":
                        goActvity(YOuLInshejiaoActivity.class);
                        break;
                    case "车辆管理":
                        goActvity(CLGLActivity.class);
                        break;
                }

            }
        });
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                mLv.smoothScrollBy(5,0);
//
//            }
//        }.start();
//        new Timer().schedule(new TimeTaskScroll(this, list_item), 100, 100);
//        TimeTrik timeTrik=new TimeTrik(mLv);
//        new Timer().schedule(timeTrik,100,100);
//        ListView listVIew = timeTrik.getListVIew();
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                mActiivty.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mLv.smoothScrollBy(5,0);
//                    }
//                });
//            }
//        },0,3000);

//        mLv.requestFocusFromTouch();


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_3;
    }
}
