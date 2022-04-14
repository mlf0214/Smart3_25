package com.example.smart3_25.ui.activty.zhsq;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.zhsq.YlinBean;
import com.example.smart3_25.ui.ShowDiag;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class AddYOulinDataActivity extends BaseActivity {
    private android.widget.RelativeLayout mToolbar;
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.ImageView mRightIcon;
    private android.widget.EditText mEt;
    private android.widget.ImageView mIvadd;
    private android.widget.Button mBtnAdd;
    private Bitmap bitmap;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {

        mToolbar = findViewById(R.id.toolbar);
        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mRightIcon = findViewById(R.id.right_icon);
        mEt = findViewById(R.id.et);
        mIvadd = findViewById(R.id.ivadd);
        mTitle.setText("发表图文信息");
        mLeftIcon.setImageResource(R.drawable.arrow_back_ios_24);
        mBtnAdd = findViewById(R.id.btn_add);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==200&&resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            bitmap= (Bitmap) extras.get("data");
        }
    }

    @Override
    protected void initEvent() {
        mIvadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDiag.showZpdig(AddYOulinDataActivity.this,AddYOulinDataActivity.this,200);
            }
        });
          mLeftIcon.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          finish();
                      }
                  });
          mBtnAdd.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  sumit(mEt);
              }
          });
    }
 private byte[] img(Bitmap bitmap){
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
         return baos.toByteArray();
     }
    private void sumit(EditText mEt) {
        String s = mEt.getText().toString();
        YlinBean ylinBean=new YlinBean();
        byte[] img = img(bitmap);
        ylinBean.setImg(img);
        ylinBean.setContent(s);
        ylinBean.setName("张三");
        ylinBean.save();
        goActvity(YOuLInshejiaoActivity.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addyldate;
    }
}
