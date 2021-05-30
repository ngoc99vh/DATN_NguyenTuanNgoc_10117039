package com.example.datn_nguyentuanngoc_10117039.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datn_nguyentuanngoc_10117039.Model.Users;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.simple.eventbus.EventBus;

import java.util.Date;


public class LoginActivity extends AppCompatActivity {

    public SharedPreferences saveInfoAccount;
    Button btn_login, bt_redirect_logout, btn_login_admin;
    EditText userName_login, password_login;
    String parentDbName = "User";
    int role_id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        saveInfoAccount = getSharedPreferences("saveInfo", Context.MODE_PRIVATE);
        init();

    }

    public void init() {
        userName_login = findViewById(R.id.userName_login);
        password_login = findViewById(R.id.password_login);
        btn_login = findViewById(R.id.btn_login);
        bt_redirect_logout = findViewById(R.id.bt_redirect_register);
        btn_login_admin = findViewById(R.id.btn_login_admin);

        bt_redirect_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        btn_login_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login_Admin();
            }
        });
    }

    private void Login_Admin() {

    }

    private void Login() {
        String userName = userName_login.getText().toString();
        String password = password_login.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Vui lòng nhập tên đăng nhập !!!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu !!!", Toast.LENGTH_SHORT).show();
        } else {
            final DatabaseReference data;
            data = FirebaseDatabase.getInstance().getReference();
            data.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child(parentDbName).child(userName).exists()) {
                        Users dataUsers = snapshot.child(parentDbName).child(userName).getValue(Users.class);
                        if (dataUsers.getUserName().contains(userName)) {
                            if (dataUsers.getPassword().contains(password)) {
                                // khai báo
                                String userName = dataUsers.getUserName();
                                String password = dataUsers.getPassword();
                                String phone = dataUsers.getPhone();
                                String fullName = dataUsers.getFullName();
                                String address= dataUsers.getAddress();
                                String birthday = dataUsers.getBirthday();
                                // lưu dữ liệu vào bộ nhớ SharedPreferences
                                SharedPreferences.Editor editor = saveInfoAccount.edit();
                                editor.putString("userName", userName);
                                editor.putString("password", password);
                                editor.putString("phone", phone);
                                editor.putString("fullName", fullName);
                                editor.putString("address", address);
                                editor.putString("birthday", birthday);
                                editor.commit();
                                // kiểm tra đăng nhập
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công !!!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                EventBus.getDefault().post(true,"loginSuccess");
                            }

                        }

                    } else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bạt:", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

}