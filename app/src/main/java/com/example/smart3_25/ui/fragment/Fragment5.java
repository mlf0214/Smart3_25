package com.example.smart3_25.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseFragment;
import com.example.smart3_25.ui.activty.home.PasswordSetActivity;
import com.example.smart3_25.ui.activty.home.UserSetActivity;
import com.example.smart3_25.ui.activty.home.YJFKActivity;

public class Fragment5 extends BaseFragment {
    private ImageView mLeftIcon;
    private TextView mTitle;
    private ImageView mRightIcon;
    private ImageView mIv;
    private LinearLayout mLl1;
    private LinearLayout mLl2;
    private LinearLayout mLl3;
    private Button mBtnOutlogin;

    @Override
    protected void initView() {

        mLeftIcon =    getView().findViewById(R.id.left_icon);
        mTitle =       getView().findViewById(R.id.title);
        mRightIcon =   getView().findViewById(R.id.right_icon);
        mIv =          getView().findViewById(R.id.iv);
        mLl1 =         getView().findViewById(R.id.ll1);
        mLl2 =         getView().findViewById(R.id.ll2);
        mLl3 =         getView().findViewById(R.id.ll3);
        mBtnOutlogin = getView().findViewById(R.id.btn_outlogin);

                mTitle.setText("个人中心");
                mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void ininEvent() {
        mLl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goActvity(UserSetActivity.class);
            }
        });
        mLl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goActvity(PasswordSetActivity.class);
            }
        });
        mLl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goActvity(YJFKActivity.class);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_5;
    }
}
