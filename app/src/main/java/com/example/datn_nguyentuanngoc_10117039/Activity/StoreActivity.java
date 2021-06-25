package com.example.datn_nguyentuanngoc_10117039.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.datn_nguyentuanngoc_10117039.Adapter.AutomakersAdapter;
import com.example.datn_nguyentuanngoc_10117039.Adapter.ProductAdapter;
import com.example.datn_nguyentuanngoc_10117039.Adapter.StoreAdapter;
import com.example.datn_nguyentuanngoc_10117039.Model.Posts;
import com.example.datn_nguyentuanngoc_10117039.R;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        saveInfoAccount = getSharedPreferences("saveInfo", Context.MODE_PRIVATE);
        userName = saveInfoAccount.getString("userName", null);
        rcl_store = findViewById(R.id.rcl_store);
        rcl_store.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        rcl_store.setLayoutManager(layoutManager);
        rcl_store.setNestedScrollingEnabled(false);

        loadProduct();
    }
    public void loadProduct() {
        mUploads = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Posts");
        mDatabaseRef.orderByChild("pChuxe").equalTo(userName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Posts upload = postSnapshot.getValue(Posts.class);
                    mUploads.add(upload);
                }
                mAdapter = new StoreAdapter(mUploads, StoreActivity.this);
                rcl_store.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(StoreActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}