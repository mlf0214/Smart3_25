package com.example.smart3_25.ui.activty.zhsq;

import android.view.View;
import android.widget.Toast;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.zhsq.CLbean;

import org.litepal.LitePal;

public class CLGLActivity extends BaseActivity {
    private android.widget.RelativeLayout mToolbar;
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.ImageView mRightIcon;
    private android.widget.EditText mEt1;
    private android.widget.EditText mEt2;
    private android.widget.EditText mEt3;
    private android.widget.EditText mEt4;
    private android.widget.EditText mEt5;
    private android.widget.EditText mEt6;
    private android.widget.EditText mEt7;
    private android.widget.Button mBtn1;
    private android.widget.Button mBtn2;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {

        mToolbar = findViewById(R.id.toolbar);
        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mRightIcon = findViewById(R.id.right_icon);
        mEt1 = findViewById(R.id.et1);
        mEt2 = findViewById(R.id.et2);
        mEt3 = findViewById(R.id.et3);
        mEt4 = findViewById(R.id.et4);
        mEt5 = findViewById(R.id.et5);
        mEt6 = findViewById(R.id.et6);
        mEt7 = findViewById(R.id.et7);
        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mTitle.setText("车辆管理");
        mLeftIcon.setImageResource(R.drawable.arrow_back_ios_24);
    }

    @Override
    protected void initData() {
        //.车辆管理：首页设置车辆管理的入口，
        // 点击跳转车辆管理页面，用户可添加个人车辆信息卡，
        // 可编辑完善车辆详情，提供用户可编辑的字段有车牌号码
        // 、车位号、停车卡号、车主姓名、车主手机号、相关住户姓名和地址。
    }

    @Override
    protected void initEvent() {
          mLeftIcon.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          finish();
                      }
                  });
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumit(mEt1.getText().toString(),mEt2.getText().toString(),
                        mEt3.getText().toString(),mEt4.getText().toString(),
                        mEt5.getText().toString(),mEt6.getText().toString(),
                        mEt7.getText().toString());
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CLbean cLbean1 = LitePal.find(CLbean.class,1);

                if (cLbean1==null){
                    Toast.makeText(CLGLActivity.this, "暂无车辆信息", Toast.LENGTH_SHORT).show();
                }else {
                    goActvity(XIugaiClDAtaActivity.class);

                }
            }
        });
    }

    private void sumit(String et1, String et2, String et3, String et4, String et5, String et6, String et7) {
        if (et1.equals("")||et2.equals("")||et3.equals("")||et4.equals("")||
                et5.equals("")||et6.equals("")||et7.equals("")){
            Toast.makeText(this, "请输入完整信息", Toast.LENGTH_SHORT).show();
        }else {
            CLbean cLbean=new CLbean();
            cLbean.setEt1(et1);
            cLbean.setEt2(et2);
            cLbean.setEt3(et3);
            cLbean.setEt4(et4);
            cLbean.setEt5(et5);
            cLbean.setEt6(et6);
            cLbean.setEt7(et7);
            cLbean.save();
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            mEt1.setText("");
            mEt2.setText("");
            mEt3.setText("");
            mEt4.setText("");
            mEt5.setText("");
            mEt6.setText("");
            mEt7.setText("");


        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clgl;
    }
}
