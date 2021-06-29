package com.example.datn_nguyentuanngoc_10117039.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.simple.eventbus.EventBus;
import com.example.datn_nguyentuanngoc_10117039.Fragment.Account;

public class SettingActivity extends AppCompatActivity {
    EditText Hoten, ngaysinh, sdt, diachi, mkcu, mkmoi;
    private Toolbar toolbar1;
    Button bt_update;
    CheckBox checkBoxmk;
    TextInputLayout textInputEditText1, textInputEditText2;
    RadioButton radioButton_nam, radioButton_nu;
    String str_mkmoi, str_mkcu, str_hoten, str_diachi, str_ngaysinh, str_sdt;
    String userName="";
    public SharedPreferences saveInfoAccount;
    private DatabaseReference mDatabaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        saveInfoAccount = getSharedPreferences("saveInfo", Context.MODE_PRIVATE);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("User");
        init();
        getData();
        update();
        Actiontoolbar();
    }
    private void init(){
        checkBoxmk = findViewById(R.id.checkboxmk);
        Hoten = findViewById(R.id.hoten_tttk_ac);
        ngaysinh = findViewById(R.id.birthday_tttk_ac);
        diachi = findViewById(R.id.diachi_tttk_ac);
        sdt = findViewById(R.id.sdt_tttk_ac);
        mkcu = findViewById(R.id.edt_mkcu);
        mkmoi = findViewById(R.id.edt_mkmoi);
        bt_update = findViewById(R.id.bt_update_tttk);
        toolbar1 = (Toolbar) findViewById(R.id.toolbar_thongtintk);
        textInputEditText1 = findViewById(R.id.textinput);
        textInputEditText2 = findViewById(R.id.textinput1);
        textInputEditText2.setVisibility(View.GONE);
        textInputEditText1.setVisibility(View.GONE);

    }
    private void Actiontoolbar() {
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void getData(){
       userName = saveInfoAccount.getString("userName","");
        if(!TextUtils.isEmpty(userName)){
            String name = saveInfoAccount.getString("fullName","");
            String birthday = saveInfoAccount.getString("birthday","");
            String address = saveInfoAccount.getString("address","");
            String phone = saveInfoAccount.getString("phone","");
            Hoten.setText(name);
            ngaysinh.setText(birthday);
            diachi.setText(address);
            sdt.setText(phone);
        }
    }
    public void updateTT(){
        str_hoten = Hoten.getText().toString().trim();
        str_ngaysinh = ngaysinh.getText().toString().trim();
        str_diachi = diachi.getText().toString().trim();
        str_sdt  = sdt.getText().toString().trim();
        userName = saveInfoAccount.getString("userName","");
        mDatabaseRef.orderByChild("userName").equalTo(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child: snapshot.getChildren()) {
                    String key = child.getKey();
                    mDatabaseRef.child(key).child("fullName").setValue(str_hoten);
                    mDatabaseRef.child(key).child("address").setValue(str_diachi);
                    mDatabaseRef.child(key).child("birthDay").setValue(str_ngaysinh);
                    mDatabaseRef.child(key).child("phone").setValue(str_sdt);

                    Toast.makeText(SettingActivity.this, "Lưu thông tin thành công !", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = saveInfoAccount.edit();
                    editor.putString("userName", userName);;
                    editor.putString("phone", str_sdt);
                    editor.putString("fullName", str_hoten);
                    editor.putString("address", str_diachi);
                    editor.putString("birthday", str_ngaysinh);
                    editor.commit();
                    getData();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void eventupdateMK(){
        str_hoten = Hoten.getText().toString().trim();
        str_ngaysinh = ngaysinh.getText().toString().trim();
        str_diachi = diachi.getText().toString().trim();
        str_sdt  = sdt.getText().toString().trim();
        str_mkcu = mkcu.getText().toString().trim();
        str_mkmoi = mkmoi.getText().toString().trim();
        String mkold= saveInfoAccount.getString("password","");
        userName = saveInfoAccount.getString("userName","");
        int ss;
        ss = mkold.compareTo(str_mkcu);
        if (ss < 0 || ss > 0) {
            Toast.makeText(this, "Mật khẩu hiện tại không đúng ! Mời nhập lại !", Toast.LENGTH_SHORT).show();
        } else if (str_mkmoi.equals("")) {
            Toast.makeText(this, "Nhập mật khẩu mới !", Toast.LENGTH_SHORT).show();
        } else {
            mDatabaseRef.orderByChild("userName").equalTo(userName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot child: snapshot.getChildren()) {
                        String key = child.getKey();
                        mDatabaseRef.child(key).child("fullName").setValue(str_hoten);
                        mDatabaseRef.child(key).child("address").setValue(str_diachi);
                        mDatabaseRef.child(key).child("birthDay").setValue(str_ngaysinh);
                        mDatabaseRef.child(key).child("phone").setValue(str_sdt);
                        mDatabaseRef.child(key).child("password").setValue(str_mkmoi);

                        Toast.makeText(SettingActivity.this, "Lưu thông tin thành công !", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = saveInfoAccount.edit();
                        editor.putString("userName", userName);;
                        editor.putString("phone", str_sdt);
                        editor.putString("fullName", str_hoten);
                        editor.putString("address", str_diachi);
                        editor.putString("birthday", str_ngaysinh);
                        editor.putString("password", str_mkmoi);
                        editor.commit();
                        getData();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
    public Boolean updatemk() {
        checkBoxmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxmk.isChecked()) {
                    textInputEditText1.setVisibility(View.VISIBLE);
                    textInputEditText2.setVisibility(View.VISIBLE);

                } else {
                    textInputEditText2.setVisibility(View.GONE);
                    textInputEditText1.setVisibility(View.GONE);

                }
            }
        });
        return false;
    }
    public void update() {
        updatemk();
        EventBus.getDefault().post(true, "loginSuccess");
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxmk.isChecked()) {
                    eventupdateMK();
                } else {
                   updateTT();
                }
            }
        });
    }

}