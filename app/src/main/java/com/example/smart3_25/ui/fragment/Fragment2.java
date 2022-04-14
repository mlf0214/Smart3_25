package com.example.smart3_25.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseFragment;
import com.example.smart3_25.bean.home.ServerBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.User;
import com.example.smart3_25.ui.activty.csdt.CHengshiDiTieeActivity;
import com.example.smart3_25.ui.activty.home.MainActivity;
import com.example.smart3_25.ui.activty.zfz.ZFZxqActivity;
import com.example.smart3_25.ui.activty.zfz.ZhangfangziZYActivity;
import com.example.smart3_25.util.adapter.MyBaseAdapter;
import com.example.smart3_25.util.adapter.ViewHolde;
import com.example.smart3_25.util.myview.MyGridVIew;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class Fragment2 extends BaseFragment {
    private ImageView mLeftIcon;
    private TextView mTitle;
    private RadioGroup mRg;
    private RadioButton mRb1;
    private RadioButton mRb2;
    private RadioButton mRb3;
    private RadioButton mRb4;
    private MyGridVIew mMv;
    private List<ServerBean.RowsDTO> lIstData1, lIstData2, lIstData3, lIstData4;

    @Override
    protected void initView() {

        mLeftIcon = getView().findViewById(R.id.left_icon);
        mTitle = getView().findViewById(R.id.title);
        mRg = getView().findViewById(R.id.rg);
        mRb1 = getView().findViewById(R.id.rb1);
        mRb2 = getView().findViewById(R.id.rb2);
        mRb3 = getView().findViewById(R.id.rb3);
        mRb4 = getView().findViewById(R.id.rb4);
        mMv = getView().findViewById(R.id.mv);
    }

    @Override
    protected void initData() {
    }



    @Override
    protected void ininEvent() {
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (mRb1.isChecked()) {
                    selectServerList("");
                }
                if (mRb2.isChecked()) {
                    selectServerList(mRb2.getText().toString());
                }
                if (mRb3.isChecked()) {
                    selectServerList(mRb3.getText().toString());
                }
                if (mRb4.isChecked()) {
                    selectServerList(mRb4.getText().toString());
                }
            }
        });
        mRb1.setChecked(true);
    }

    private void selectServerList(String serverType) {
        OKhttpUtil oKhttpUtil = new OKhttpUtil(mActiivty);
        oKhttpUtil.setget(User.URI + "/prod-api/api/service/list?serviceType=" + serverType, "");
        oKhttpUtil.setOkhttp_code(new Okhttp_code() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ServerBean serverBean = new Gson().fromJson(string, ServerBean.class);
                List<ServerBean.RowsDTO> rows = serverBean.getRows();

                mActiivty.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMv.setAdapter(new MyBaseAdapter<ServerBean.RowsDTO>(rows, R.layout.gv_sy, rows.size()) {
                            private TextView mTv;
                            private ImageView mIv;

                            @Override
                            public void converView(ViewHolde viewHolde, ServerBean.RowsDTO rowsDTO, int i) {
                                mIv = viewHolde.getVIew(R.id.iv);
                                mTv = viewHolde.getVIew(R.id.tv);
                                mIv.setMinimumWidth(x/4);
                                mIv.setMinimumHeight(x/4);
                                setIMage(mActiivty,User.URI+rowsDTO.getImgUrl(),mIv);
                                mTv.setText(rowsDTO.getServiceName());
                            }
                        });
                        mMv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                MainActivity mainActivity= (MainActivity) mActiivty;
                                String serviceName = rows.get(i).getServiceName();
                                Toast.makeText(mActiivty, serviceName, Toast.LENGTH_SHORT).show();
                                switch (serviceName){
                                    case "城市地铁":
                                        goActvity(CHengshiDiTieeActivity.class);
                                        break;
                                    case "数据分析":
                                        mainActivity.setTab(3);
                                        break;
                                    case "找房子":
                                        goActvity(ZhangfangziZYActivity.class);
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_2;
    }
}
