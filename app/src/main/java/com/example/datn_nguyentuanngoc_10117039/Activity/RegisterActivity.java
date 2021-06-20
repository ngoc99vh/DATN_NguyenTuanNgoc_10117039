package com.example.datn_nguyentuanngoc_10117039.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText userName_register, password_register, phone_register, fulName_register, address_register, birthDay_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    public void init() {
        userName_register = (EditText) findViewById(R.id.userName_register);
        password_register = (EditText) findViewById(R.id.password_register);
        phone_register = (EditText) findViewById(R.id.phone_register);
        fulName_register = (EditText) findViewById(R.id.fulName_register);
        address_register = (EditText) findViewById(R.id.address_register);
        birthDay_register = (EditText) findViewById(R.id.birthDay_register);
        Button btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }

    public void createUser() {
        String userName = userName_register.getText().toString();
        String password = password_register.getText().toString();
        String phone = phone_register.getText().toString();
        String fulName = fulName_register.getText().toString();
        String address = address_register.getText().toString();
        String birthDay = birthDay_register.getText().toString();
        String role = "1";
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(fulName)) {
            Toast.makeText(this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(birthDay)) {
            Toast.makeText(this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
        }else {
            postUser(userName,password,phone,fulName,birthDay,address,role);
        }
    }

    private void postUser(String userName, String password, String phone, String fulName, String birthDay, String address,String role) {
        DatabaseReference data;
        data = FirebaseDatabase.getInstance().getReference();
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(userName).exists())){
                    HashMap<String,Object> userdataMap =new HashMap<>();
                    userdataMap.put("userName",userName);
                    userdataMap.put("password",password);
                    userdataMap.put("phone",phone);
                    userdataMap.put("role",role);
                    userdataMap.put("fulName",fulName);
                    userdataMap.put("birthDay",birthDay);
                    userdataMap.put("address",address);
                    data.child("User").child(userName).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            }else {
                                Toast.makeText(RegisterActivity.this, "Kết nối thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else {
                    Toast.makeText(RegisterActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
