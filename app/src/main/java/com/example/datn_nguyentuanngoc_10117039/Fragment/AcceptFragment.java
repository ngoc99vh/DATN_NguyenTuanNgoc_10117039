package com.example.datn_nguyentuanngoc_10117039.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.datn_nguyentuanngoc_10117039.Adapter.AutomakersAdapter;
import com.example.datn_nguyentuanngoc_10117039.Adapter.ProductAdapter;
import com.example.datn_nguyentuanngoc_10117039.Adapter.ProductAdminAdapter;
import com.example.datn_nguyentuanngoc_10117039.Model.Posts;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AcceptFragment extends Fragment {
    private RecyclerView rcl_home_admin_acc;
    private DatabaseReference mDatabaseRef;
    private ProductAdminAdapter mAdapter;
    private ArrayList<Posts> mUploads;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accept, container, false);
        rcl_home_admin_acc = view.findViewById(R.id.rcl_home_admin_acc);



        loadProduct1();
        return view;
    }

    public void loadProduct1() {
        rcl_home_admin_acc.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        rcl_home_admin_acc.setLayoutManager(layoutManager);
        rcl_home_admin_acc.setNestedScrollingEnabled(false);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Posts");
        mDatabaseRef.orderByChild("pStatus").equalTo("Đã xác nhận").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Posts upload = postSnapshot.getValue(Posts.class);
                    mUploads.add(upload);
                }
                mAdapter = new ProductAdminAdapter(mUploads, getContext());
                rcl_home_admin_acc.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}