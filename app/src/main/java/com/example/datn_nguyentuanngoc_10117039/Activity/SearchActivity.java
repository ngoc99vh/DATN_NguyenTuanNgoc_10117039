package com.example.datn_nguyentuanngoc_10117039.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;

import com.example.datn_nguyentuanngoc_10117039.Adapter.ProductAdapter;
import com.example.datn_nguyentuanngoc_10117039.Model.Posts;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    SearchView searchView;
    RecyclerView recycler_tk;
    private DatabaseReference mDatabaseRef;
    private ProductAdapter mAdapter;
    private ArrayList<Posts> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchview_tk);
        recycler_tk = findViewById(R.id.recycler_tk);
        searchView.setQueryHint("Nhập thông tin cần tìm");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mUploads = new ArrayList<>();
                recycler_tk.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                recycler_tk.setLayoutManager(layoutManager);
                search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
        public void search(){
        String key = searchView.getQuery().toString().trim();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Posts");
        mDatabaseRef.orderByChild("pName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Posts upload = postSnapshot.getValue(Posts.class);
                    if(upload.getpName().toLowerCase().contains(key.toLowerCase())){
                        mUploads.add(upload);
                    }

                }
                mAdapter = new ProductAdapter(mUploads, getApplicationContext());
                recycler_tk.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}