package com.example.smart3_25.util.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private int LayoutId;
    private List<T> list;
    private int size;

    public MyBaseAdapter(List<T> list,int layoutId, int size) {
        LayoutId = layoutId;
        this.list = list;
        this.size = size;
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolde viewHolde = getViewHolde(view, viewGroup);

        converView(viewHolde,list.get(i),i);


        return viewHolde.getContView();
    }

    public abstract void converView(ViewHolde viewHolde, T t, int i);

    private ViewHolde getViewHolde(View view,ViewGroup viewGroup){
        return ViewHolde.getVIewHolde(view,viewGroup,LayoutId);
    }
}
