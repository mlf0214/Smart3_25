package com.example.smart3_25.base;

import android.content.ContextParams;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PerformanceHintManager;
import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentActivity;

import com.example.smart3_25.bean.Messg;
import com.example.smart3_25.util.MyApplication;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends FragmentActivity {
    private int requestCode=100;
    private String[] qaunxians=new String[]{"android.permission.CAMERA","android.permission.CALL_PHONE"};
    private List<String> strings=new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        LitePal.initialize(this);
        EventBus.getDefault().register(this);
        setquanxian();
        initView();
        initData();
        initEvent();
//        fsMessg();
    }

    private void setquanxian(){
        for (int i = 0; i < qaunxians.length; i++) {
            if (ContextCompat.checkSelfPermission(this,qaunxians[i])!= PackageManager.PERMISSION_GRANTED){
               strings.add(qaunxians[i]);
            }
        }
        if (strings.size()>0){
            ActivityCompat.requestPermissions(this,qaunxians,requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isTg=true;
        if (this.requestCode==requestCode){
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i]==-1){
                    isTg=false;
                }
            }
            if (!isTg){
                setquanxian();
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void setMessg(Messg messg){};
    public void fsMessg(){
        Messg messg = EventBus.getDefault().removeStickyEvent(Messg.class);

        fsmesg(messg);
        EventBus.getDefault().postSticky(messg);
    }
    public void goActvity(Class<?> o){
        startActivity(new Intent(MyApplication.getContext(),o));
    }
    protected abstract void fsmesg(Messg messg);


    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initEvent();
    protected abstract int getLayoutId();

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
