package com.example.datn_nguyentuanngoc_10117039.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.datn_nguyentuanngoc_10117039.Fragment.Account;
import com.example.datn_nguyentuanngoc_10117039.Fragment.Home;
import com.example.datn_nguyentuanngoc_10117039.Fragment.Post;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationVieư;
    private FrameLayout main;
    private Fragment home, post, account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setFragment(home);
        bottomNavigationVieư.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.trangchu_fragment:
                        setFragment(home);
                        return true;
                    case R.id.danhmuc_fragment:
                        setFragment(post);
                        return true;
                    case R.id.account_fragment:
                        setFragment(account);
                        return true;
                }
                return false;
            }
        });
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Chạm thêm 1 lần để thoát ", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }

    public void init() {
        bottomNavigationVieư = findViewById(R.id.bottom_navigation);
        main = findViewById(R.id.main_container);
        home = new Home();
        post = new Post();
        account = new Account();

    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.commit();
    }
}


