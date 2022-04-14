package com.example.smart3_25.util.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smart3_25.util.MyApplication;

public class ViewHolde {
    private View contView;
    private SparseArray<View> sparseArray;
    
    public static ViewHolde getVIewHolde(View view, ViewGroup viewGroup,int layoutId){
        if (view==null){
            return new ViewHolde(viewGroup,layoutId);
        }
        return (ViewHolde) view.getTag();
    }
    
    public ViewHolde(ViewGroup viewGroup,int layoutId){
        contView= LayoutInflater.from(MyApplication.getContext()).inflate(layoutId,viewGroup,false);
        contView.setTag(this);
        sparseArray=new SparseArray<>();
    }
    
    public <T extends View> T getVIew(int viewId){
        View view = sparseArray.get(viewId);
        if (view==null){
            view=contView.findViewById(viewId);
            sparseArray.put(viewId,view);
        }
        return (T) view;
    }
    public View getContView(){
        return contView;
    }
    
}
