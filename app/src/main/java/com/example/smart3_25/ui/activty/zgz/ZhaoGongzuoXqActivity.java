package com.example.smart3_25.ui.activty.zgz;

import android.graphics.RenderNode;
import android.view.View;
import android.widget.Toast;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseActivity;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.bean.login.ZhuceBean;
import com.example.smart3_25.bean.zgz.GOngsiBean;
import com.example.smart3_25.bean.zgz.JianliListBean;
import com.example.smart3_25.bean.zgz.ZpXqBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.Shape;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.util.Date;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ZhaoGongzuoXqActivity extends BaseActivity {
    private String id,comId;
    private android.widget.ImageView mLeftIcon;
    private android.widget.TextView mTitle;
    private android.widget.ImageView mRightIcon;
    private android.widget.TextView mTvContent;
    private android.widget.TextView mTv1;
    private android.widget.TextView mTv2;
    private android.widget.TextView mTv3;
    private android.widget.TextView mTv4;
    private android.widget.TextView mTv5;
    private android.widget.TextView mTv6;
    private android.widget.TextView mTv7;
    private android.widget.Button mBtn;
    private android.widget.Button mBtn1;

    @Override
    protected void fsmesg(Messg messg) {

    }

    @Override
    protected void initView() {

        mLeftIcon = findViewById(R.id.left_icon);
        mTitle = findViewById(R.id.title);
        mRightIcon = findViewById(R.id.right_icon);
        mTvContent = findViewById(R.id.tv_content);
        mTv1 = findViewById(R.id.tv1);
        mTv2 = findViewById(R.id.tv2);
        mTv3 = findViewById(R.id.tv3);
        mTv4 = findViewById(R.id.tv4);
        mTv5 = findViewById(R.id.tv5);
        mTv6 = findViewById(R.id.tv6);
        mTv7 = findViewById(R.id.tv7);
        mBtn = findViewById(R.id.btn);

                mTitle.setText("详情页面");
                mLeftIcon.setBackgroundResource(R.drawable.arrow_back_ios_24);
        mBtn1 = findViewById(R.id.btn1);
    }

    @Override
    public void setMessg(Messg messg) {

        id = messg.getZgzId();
        comId = messg.getSongsiName();
    }

    @Override
    protected void initData() {
        selectGsData(id);
        selectGwData(comId);
    }

    private void selectGwData(String comName) {
        OKhttpUtil oKhttpUtil=new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI+"/prod-api/api/job/company/"+comName,"");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                GOngsiBean gOngsiBean=new Gson().fromJson(string,GOngsiBean.class);
                GOngsiBean.DataDTO data = gOngsiBean.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvContent.setText(data.getIntroductory());
                    }
                });

            }

            @Override
            public void getString(String s) {

            }


        });
    }

    private void selectGsData(String id) {
        OKhttpUtil oKhttpUtil=new OKhttpUtil(this);
        oKhttpUtil.setget(User.URI+"/prod-api/api/job/post/"+id,"");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZpXqBean zpXqBean=new Gson().fromJson(string,ZpXqBean.class);
                ZpXqBean.DataDTO data = zpXqBean.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //显示信息包括职位名称、岗位职责、
// 公司地点、薪资待遇、联系人、职位描述、
// 工作年限等；
                        //code
                        // 状态码,200 正确,其他错误
                        // string
                        //data
                        // 返回业务数据
                        // 招聘信息
                        //address
                        // 公司地址
                        // string
                        //companyId
                        // 公司
                        // integer(int64)
                        //contacts
                        // 联系人名称
                        // string
                        //id
                        // 主键 id
                        // integer(int64)
                        //name
                        // 岗位名称
                        // string
                        //files
                        // 简历附件地址
                        // string
                        //need
                        // 职位需求
                        // string
                        //obligation
                        // 岗位职责
                        // string
                        //professionId
                        // 职位
                        // integer(int64)
                        //salary
                        // 薪资待遇
                        // string
                        //msg
                        // 返回消息内容
                        // string
                        //companyName
                        // 公司名称
                        // string
                        //professionName
                        // 职位名称
                        // string
                        mTv1.setText("公司地点:"+data.getAddress());
                        mTv2.setText("薪资待遇:"+data.getSalary());
                        mTv3.setText("职位名称:"+data.getName());
                        mTv4.setText("岗位职责:"+data.getObligation());
                        mTv5.setText("联系人:"+data.getContacts());
                        mTv6.setText("职位描述:"+data.getNeed());
                        mTv7.setText("工作年限:"+5+"年");

                        mBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //companyId// 公司 id// true// integer(int64)
                                //money
                                // 薪资
                                // false
                                // string
                                //satrTime// 投递时间// true// string(date-time)
                                //postId// 岗位 id// true// integer(int64)
                                //postName// 岗位名称// true// string
                                toudijianli(String.valueOf(data.getCompanyId()), Date.getTHisDate(),String.valueOf(data.getProfessionId()),String.valueOf(data.getProfessionName()));
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

    private void toudijianli(String valueOf, String valueOf1, String professionId, String name) {
        //companyId// 公司 id// true// integer(int64)
        //satrTime// 投递时间// true// string(date-time)
        //postId// 岗位 id// true// integer(int64)
        //postName// 岗位名称// true// string
        OKhttpUtil oKhttpUtil=new OKhttpUtil(this);
        oKhttpUtil.setpost(User.Ip+"/prod-api/api/job/deliver", Shape.getValue("token",""),"companyId",valueOf,
        "satrTime",valueOf1,"postId",professionId,"postName",name);
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZhuceBean zhuceBean=new Gson().fromJson(string,ZhuceBean.class);
                int code = zhuceBean.getCode();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code==200){
                            Toast.makeText(ZhaoGongzuoXqActivity.this, "投递成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ZhaoGongzuoXqActivity.this, zhuceBean.getMsg(), Toast.LENGTH_SHORT).show();
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
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goActvity(Toudilishi.class);
            }
        });

          mLeftIcon.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          finish();
                      }
                  });
    }



    private void selectJInali() {
        OKhttpUtil oKhttpUtil=new OKhttpUtil(this);
        oKhttpUtil.setget(User.Ip+"/prod-api/api/job/deliver/list", Shape.getValue("token",""));
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                JianliListBean jianliListBean=new Gson().fromJson(string,JianliListBean.class);
                List<JianliListBean.RowsDTO> rows = jianliListBean.getRows();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (rows.size()==0){
                            goActvity(GerenjianliActivity.class);
                        }
                        for (int i = 0; i < rows.size(); i++) {

                        }
                    }
                });
            }

            @Override
            public void getString(String s) {

            }


        });
    }

    //显示信息包括职位名称、岗位职责、
// 公司地点、薪资待遇、联系人、职位描述、
// 工作年限等；显示公司信息，如公司简介等；点击“投简历”按
// 钮实现个人简历投递，如果个人简历中没有工作经历、教育经历信息，
// 则跳转至个人简历页面，完善简历后再进行投递
    @Override
    protected int getLayoutId() {
        return R.layout.activity_zgzxq;
    }
}
