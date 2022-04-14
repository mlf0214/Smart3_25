package com.example.smart3_25.ui.activty.zhsq;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.zhsq.YlinBean;
import com.example.smart3_25.sql.Shape;
import com.example.smart3_25.util.MyApplication;
import com.example.smart3_25.util.adapter.MyBaseAdapter;
import com.example.smart3_25.util.adapter.ViewHolde;
import com.google.gson.Gson;

import org.litepal.LitePal;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class YOuLInshejiaoActivity extends BaseActivity {
    private static final String TAG = "YOuLInshejiaoActivity";
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.ImageView mRightIcon;
    private com.example.smart3_25.util.myview.MyListVIew mMlv;
    private android.widget.RelativeLayout mToolbar;
    private android.widget.ImageButton mAdd;
    private List<YlinBean> ylinBeanList = new ArrayList<>();
    private String[] names, contens;
    private int[] imgs=new int[]{R.drawable.wy_iv,R.drawable.wy_iv};

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {

        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mRightIcon = findViewById(R.id.right_icon);
        mMlv = findViewById(R.id.mlv);
        mTitle.setText("社区贴吧");
        mLeftIcon.setImageResource(R.drawable.arrow_back_ios_24);
        mToolbar = findViewById(R.id.toolbar);
        mAdd = findViewById(R.id.add);
    }
    private Bitmap getbitmap(int id){
        Resources res = getResources();
        Bitmap bmp= BitmapFactory.decodeResource(res, id);
        return bmp;
    }
    private byte[] img(Bitmap bitmap){
        ByteArrayOutputStream bom=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bom);
        byte[] bytes = bom.toByteArray();
        return bytes;
    }
    @Override
    protected void initData() {
        //User mUser=DataSupport.findFrist(User.class);
        //byte[]images=mUser.getHeadshot();
        //Bitmap bitmap=BitmapFactory.decodeByteArray(images,0,images.length);
        names = new String[]{"张三", "李四"};
        contens=new String[]{"哈哈哈哈哈哈哈","55555555555"};
        for (int i = 0; i < names.length; i++) {
            YlinBean ylinBean = new YlinBean();
            ylinBean.setName(names[i]);
//            ylinBean.setImg(img(getbitmap(R.drawable.arrow_back_ios_24)));
            Log.d(TAG, String.valueOf(getbitmap(R.drawable.arrow_back_ios_24)));
            ylinBean.setContent(contens[i]);
            ylinBeanList.add(ylinBean);
            ylinBean.save();
        }
//        LitePal.saveAll(ylinBeanList);
//        setList(LitePal.findAll(YlinBean.class));
    }

    private void setList(List<YlinBean> ylinBeanList) {
        mMlv.setAdapter(new MyBaseAdapter<YlinBean>(ylinBeanList, R.layout.yl_lv, ylinBeanList.size()) {
            private TextView mName;
            private ImageView mIv2;
            private ImageView mIv1;
            private TextView mTvContent;
            private ImageView mSetIv;
            @Override
            public void converView(ViewHolde viewHolde, YlinBean ylinBean, int i) {
                mSetIv =     viewHolde.getVIew(R.id.set_iv);
                mTvContent = viewHolde.getVIew(R.id.tv_content);
                mIv1 =       viewHolde.getVIew(R.id.iv1);
                mIv2 =       viewHolde.getVIew(R.id.iv2);
                mName =      viewHolde.getVIew(R.id.name);
                Glide.with(MyApplication.getContext()).load(ylinBean.getImg()).into(mSetIv);
                mTvContent.setText(ylinBean.getContent());
                mName.setText(ylinBean.getName());
            }
        });
    }

    @Override
    public void setMessg(Messg messg) {
    }

    @Override
    protected void initEvent() {
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goActvity(AddYOulinDataActivity.class);
            }
        });
        mLeftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ylsj;
    }
}
