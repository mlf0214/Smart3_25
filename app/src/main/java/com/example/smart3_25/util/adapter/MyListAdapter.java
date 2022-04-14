package com.example.smart3_25.util.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyListAdapter<T> extends BaseAdapter {
    private int layoutId;
    private List<T> list;

    public  MyListAdapter(int layoutId, List<T> list) {
        this.layoutId = layoutId;
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i%list.size());
    }

    @Override
    public long getItemId(int i) {
        return i%list.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolde vIewHolde = getVIewHolde(viewGroup, view);
        int i1 = i % list.size();
        conview(vIewHolde,list.get(i1),i);

        return vIewHolde.getContView();
    }

 protected abstract void conview(ViewHolde vIewHolde, T t, int i);

    private ViewHolde getVIewHolde(ViewGroup viewGroup,View view){
        return ViewHolde.getVIewHolde(view,viewGroup,layoutId);
    }
}
