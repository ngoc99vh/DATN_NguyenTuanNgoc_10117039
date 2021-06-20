package com.example.datn_nguyentuanngoc_10117039.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.datn_nguyentuanngoc_10117039.Fragment.Account;
import com.example.datn_nguyentuanngoc_10117039.Fragment.Home;
import com.example.datn_nguyentuanngoc_10117039.Fragment.HomeAdmin;
import com.example.datn_nguyentuanngoc_10117039.Fragment.Post;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationVieư;
    private FrameLayout main;
    private Fragment home, post, account, homeAddmin;
    String roleID = "";
    private static SharedPreferences saveInfoAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
        setFragment(home);
        saveInfoAccount = getApplicationContext().getSharedPreferences("saveInfo", Context.MODE_PRIVATE);

        roleID = saveInfoAccount.getString("role", null);
        if (!TextUtils.isEmpty(roleID) && roleID.equals("0")) {
            setFragment(homeAddmin);
            bottomNavigationVieư.getMenu().findItem(R.id.trangchuAdmin_fragment).setVisible(true);
            bottomNavigationVieư.getMenu().findItem(R.id.trangchu_fragment).setVisible(false);
            bottomNavigationVieư.getMenu().findItem(R.id.dangtin_fragment).setVisible(false);
            bottomNavigationVieư.getMenu().findItem(R.id.account_fragment).setVisible(true);

        } else {
            bottomNavigationVieư.getMenu().findItem(R.id.trangchu_fragment).setVisible(true);
            bottomNavigationVieư.getMenu().findItem(R.id.dangtin_fragment).setVisible(true);
            bottomNavigationVieư.getMenu().findItem(R.id.account_fragment).setVisible(true);
            bottomNavigationVieư.getMenu().findItem(R.id.trangchuAdmin_fragment).setVisible(false);

        }

        bottomNavigationVieư.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.trangchu_fragment:
                        setFragment(home);
                        return true;
                    case R.id.dangtin_fragment:
                        setFragment(post);
                        return true;
                    case R.id.account_fragment:
                        setFragment(account);
                        return true;
                    case R.id.trangchuAdmin_fragment:
                        setFragment(homeAddmin);
                        return true;
                }
                return false;
            }
        });
        check();
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
        homeAddmin = new HomeAdmin();

    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.commit();
    }

    private void check() {

    }
}


