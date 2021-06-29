package com.example.datn_nguyentuanngoc_10117039.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.datn_nguyentuanngoc_10117039.Fragment.AcceptFragment;
import com.example.datn_nguyentuanngoc_10117039.Fragment.Accept_Store_Fragment;
import com.example.datn_nguyentuanngoc_10117039.Fragment.NoAccep_Store_Fragment;
import com.example.datn_nguyentuanngoc_10117039.Fragment.NoAcceptFragment;

public class Fragment_Store_Adapter extends FragmentStatePagerAdapter {
    public Fragment_Store_Adapter(@NonNull FragmentManager fragmentManager, int behavior) {
        super(fragmentManager, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new Accept_Store_Fragment();

            case 0:
                return new NoAccep_Store_Fragment();
            default:
                return new Accept_Store_Fragment();
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