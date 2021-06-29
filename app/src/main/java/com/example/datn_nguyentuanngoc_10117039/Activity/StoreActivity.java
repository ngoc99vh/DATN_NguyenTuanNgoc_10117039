package com.example.datn_nguyentuanngoc_10117039.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.datn_nguyentuanngoc_10117039.Adapter.AutomakersAdapter;
import com.example.datn_nguyentuanngoc_10117039.Adapter.FragmentAdapter;
import com.example.datn_nguyentuanngoc_10117039.Adapter.Fragment_Store_Adapter;
import com.example.datn_nguyentuanngoc_10117039.Adapter.ProductAdapter;
import com.example.datn_nguyentuanngoc_10117039.Adapter.StoreAdapter;
import com.example.datn_nguyentuanngoc_10117039.Model.Posts;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    private StoreAdapter mAdapter;
    private ArrayList<Posts> mUploads;
    private static SharedPreferences saveInfoAccount;
    private RecyclerView rcl_store;
    public String userName = "";
    TabLayout tabLayout;
    ViewPager pager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        saveInfoAccount = getSharedPreferences("saveInfo", Context.MODE_PRIVATE);
        userName = saveInfoAccount.getString("userName", null);
//        rcl_store = findViewById(R.id.rcl_store);
//        rcl_store.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
//        rcl_store.setLayoutManager(layoutManager);
//        rcl_store.setNestedScrollingEnabled(false);

//        loadProduct();
        tabLayout = findViewById(R.id.tab_layout_store);
        pager2 = findViewById(R.id.view_pager_store);
        Fragment_Store_Adapter adapter = new Fragment_Store_Adapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pager2.setAdapter(adapter);
        pager2.setSaveEnabled(false);

        tabLayout.setupWithViewPager(pager2);
    }
//    public void loadProduct() {
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Posts");
//        mDatabaseRef.orderByChild("pChuxe").equalTo(userName).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                mUploads = new ArrayList<>();
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    Posts upload = postSnapshot.getValue(Posts.class);
//                    mUploads.add(upload);
//                }
//                mAdapter = new StoreAdapter(mUploads, StoreActivity.this);
//                rcl_store.setAdapter(mAdapter);
//                mAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(StoreActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}