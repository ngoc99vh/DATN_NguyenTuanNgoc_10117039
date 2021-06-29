package com.example.datn_nguyentuanngoc_10117039.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.datn_nguyentuanngoc_10117039.Activity.StoreActivity;
import com.example.datn_nguyentuanngoc_10117039.Adapter.StoreAdapter;
import com.example.datn_nguyentuanngoc_10117039.Model.Posts;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Accept_Store_Fragment extends Fragment {

    private DatabaseReference mDatabaseRef;
    private StoreAdapter mAdapter;
    private ArrayList<Posts> mUploads;
    private static SharedPreferences saveInfoAccount;
    private RecyclerView rcl_store;
    public String userName = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accept__store_, container, false);
        saveInfoAccount = getActivity().getSharedPreferences("saveInfo", Context.MODE_PRIVATE);
        userName = saveInfoAccount.getString("userName", null);
        rcl_store = view.findViewById(R.id.rcl_home_store_acc);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1, LinearLayoutManager.VERTICAL, false);
        rcl_store.setLayoutManager(layoutManager);
        rcl_store.setNestedScrollingEnabled(false);
        rcl_store.setHasFixedSize(true);
        loadProduct();
        return view;
    }
        public void loadProduct() {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Posts");
        mDatabaseRef.orderByChild("pChuxe").equalTo(userName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Posts upload = postSnapshot.getValue(Posts.class);
                    if(upload.getpStatus().equals("Đã xác nhận")){
                        mUploads.add(upload);
                    }

                }
                mAdapter = new StoreAdapter(mUploads, getContext());
                rcl_store.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}