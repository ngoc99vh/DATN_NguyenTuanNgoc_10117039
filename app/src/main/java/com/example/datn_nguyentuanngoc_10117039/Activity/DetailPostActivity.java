package com.example.datn_nguyentuanngoc_10117039.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.datn_nguyentuanngoc_10117039.Adapter.ProductAdapter;
import com.example.datn_nguyentuanngoc_10117039.Model.Posts;
import com.example.datn_nguyentuanngoc_10117039.Model.Users;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailPostActivity extends AppCompatActivity {
    ImageSlider imageSlider;
    TextView tv_name, tv_dongco, tv_namSX, tv_dongxe, tv_tinhtrang, tv_khuvuc_detail, tv_TTthem_detail, tv_chuxe_detail;
    Toolbar toolbar;
    Button btn_call, btn_send;
    private Posts posts;
    private DatabaseReference mDatabaseRef;
    Users users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();
        posts = (Posts) intent.getSerializableExtra("sanpham");
        Init();
        Actiontoolbar();
        getUser();
        call();
        sendSMS();

    }

    public void Init() {

        btn_send = findViewById(R.id.btn_send);
        btn_call = findViewById(R.id.btn_call);
        tv_dongxe = findViewById(R.id.tv_dongxe);
        tv_chuxe_detail = findViewById(R.id.tv_chuxe_detail);
        tv_TTthem_detail = findViewById(R.id.tv_TTthem_detail);
        tv_khuvuc_detail = findViewById(R.id.tv_khuvuc_detail);
        tv_tinhtrang = findViewById(R.id.tv_tinhtrang);
        tv_name = findViewById(R.id.tv_nameProduct);
        toolbar = findViewById(R.id.toolbar_detail);
        imageSlider = findViewById(R.id.slider);
        tv_dongco = findViewById(R.id.tv_dongco_detail);
        tv_namSX = findViewById(R.id.tv_namSx_detail);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(posts.getImages().getImage1()));
        slideModels.add(new SlideModel(posts.getImages().getImage2()));
        imageSlider.setImageList(slideModels, true);

        tv_dongco.setText(posts.getpDongco());
        tv_khuvuc_detail.setText(posts.getpKhuvuc());
        tv_namSX.setText(posts.getpDate());
        tv_dongxe.setText(posts.getpDongxe());
        tv_tinhtrang.setText(posts.getpCondition());
        tv_TTthem_detail.setText(posts.getpThongtin());
        tv_name.setText(posts.getpName());
    }

    public void getUser() {
        ArrayList<Users> listUser = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("User");
        mDatabaseRef.orderByChild("userName").equalTo(posts.getpChuxe()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    users = postSnapshot.getValue(Users.class);
                    tv_chuxe_detail.setText(users.getFullName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Actiontoolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(posts.getpName());
        toolbar.setTitleTextColor(Color.WHITE);
        (toolbar).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void call() {
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "tel:" + users.getPhone();
                if (phone != null && phone != "") {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(phone));
                    startActivity(intent);
                }
            }
        });
    }

    public void sendSMS() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = users.getPhone();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phone, null)));
            }
        });
    }


}
