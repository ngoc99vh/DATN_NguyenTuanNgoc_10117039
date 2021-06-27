package com.example.datn_nguyentuanngoc_10117039.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datn_nguyentuanngoc_10117039.Adapter.FragmentAdapter;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.android.material.tabs.TabLayout;

public class HomeAdmin extends Fragment {

    TabLayout tabLayout;
    ViewPager pager2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_admin, container, false);

        tabLayout = view.findViewById(R.id.tab_layout);
        pager2 = view.findViewById(R.id.view_pager2);
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pager2.setAdapter(adapter);
        pager2.setSaveEnabled(false);

       tabLayout.setupWithViewPager(pager2);

        return view;
    }
}