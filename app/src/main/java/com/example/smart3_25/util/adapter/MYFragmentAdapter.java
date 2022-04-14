package com.example.smart3_25.util.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.smart3_25.base.BaseFragment;

import java.util.List;

public class MYFragmentAdapter<T extends BaseFragment> extends FragmentStatePagerAdapter {
    private List<T> list;

    public MYFragmentAdapter(@NonNull FragmentManager fm, int behavior,List<T> list) {
        super(fm, behavior);
        this.list=list;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();

    }
}
