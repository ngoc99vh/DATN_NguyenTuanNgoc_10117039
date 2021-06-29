package com.example.datn_nguyentuanngoc_10117039.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.datn_nguyentuanngoc_10117039.Activity.LoginActivity;
import com.example.datn_nguyentuanngoc_10117039.Activity.MainActivity;
import com.example.datn_nguyentuanngoc_10117039.Activity.SettingActivity;
import com.example.datn_nguyentuanngoc_10117039.Activity.StoreActivity;
import com.example.datn_nguyentuanngoc_10117039.Activity.UserActivity;
import com.example.datn_nguyentuanngoc_10117039.R;

import org.simple.eventbus.Subscriber;

public class Account extends Fragment {
    public TextView Login, tv_userName, tv_phone, acc_store, tv_name_cut, acc_setting, acc_qltk;
    Button btn_logout;
    private static SharedPreferences saveInfoAccount;
    private SharedPreferences.Editor editor;
    LinearLayout linearLayout, linearLayout_setting, linearLayout_qltk;
    public String userName = "";
    String role = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_account, container, false);

        //Ánh xạ
        Login = view.findViewById(R.id.tv_Login);
        acc_setting = view.findViewById(R.id.acc_setting);
        tv_userName = view.findViewById(R.id.tv_userName_Account);
        tv_name_cut = view.findViewById(R.id.tv_name_cut);
        tv_phone = view.findViewById(R.id.tv_phone_Account);
        btn_logout = view.findViewById(R.id.btn_logout);
        acc_store = view.findViewById(R.id.acc_store);
        linearLayout = view.findViewById(R.id.ll_onbought);
        linearLayout_setting = view.findViewById(R.id.ll_setting);
        linearLayout_qltk = view.findViewById(R.id.ll_qltk);
        acc_qltk = view.findViewById(R.id.acc_qltk);

        //Sự kiện
        saveInfoAccount = getContext().getSharedPreferences("saveInfo", Context.MODE_PRIVATE);
        init();
        loginSuccess(saveInfoAccount.getString("userName", null) != null);

        acc_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), StoreActivity.class));
            }
        });
        acc_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });
        acc_qltk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserActivity.class));
            }
        });

        return view;
    }

    @Subscriber(tag = "loginSuccess")
    public void loginSuccess(Boolean b) {
        if (b = true) {
            checkData();
        }

    }


    public void init() {
        //Onclick
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = saveInfoAccount.edit();
                editor.clear();
                editor.apply();
                checkData();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

    }

    public void checkData() {
        userName = saveInfoAccount.getString("userName", null);
        role = saveInfoAccount.getString("role", null);
        if (!TextUtils.isEmpty(userName)) {
            Login.setVisibility(View.GONE);
            tv_userName.setText(userName);
            tv_name_cut.setText(userName.substring(0, 1));
            tv_phone.setText(saveInfoAccount.getString("phone", ""));
        } else {
            Login.setVisibility(View.VISIBLE);
            tv_userName.setText("");
            tv_phone.setText("");
        }
        if (!TextUtils.isEmpty(role) && role.equals("0")) {
            linearLayout.setVisibility(View.GONE);
            linearLayout_setting.setVisibility(View.GONE);
        } else {
            linearLayout_qltk.setVisibility(View.GONE);
        }
    }

}