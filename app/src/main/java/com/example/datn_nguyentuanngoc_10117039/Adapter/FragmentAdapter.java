package com.example.datn_nguyentuanngoc_10117039.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.datn_nguyentuanngoc_10117039.Fragment.AcceptFragment;
import com.example.datn_nguyentuanngoc_10117039.Fragment.NoAcceptFragment;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, int behavior) {
        super(fragmentManager, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new AcceptFragment();
            case 0:
                return new NoAcceptFragment();
            default:
                return new AcceptFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return "Đã xác nhận";
            case 0:
                return "Chưa xác nhận";
            default:
                return "Chưa xác nhận";
        }

    }
}