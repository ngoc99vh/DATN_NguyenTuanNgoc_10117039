package com.example.datn_nguyentuanngoc_10117039.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datn_nguyentuanngoc_10117039.Activity.ProducrActivity;
import com.example.datn_nguyentuanngoc_10117039.Adapter.Location_Adapter;
import com.example.datn_nguyentuanngoc_10117039.Adapter.ProductAdapter;
import com.example.datn_nguyentuanngoc_10117039.Dialog.Location;
import com.example.datn_nguyentuanngoc_10117039.Model.Location_model;
import com.example.datn_nguyentuanngoc_10117039.Model.Posts;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    RecyclerView rcl;
    ImageView img_location;
    TextView tv_location;
    private DatabaseReference mDatabaseRef;
    private ProductAdapter mAdapter;
    private ArrayList<Posts> mUploads;


    private ArrayList<Location_model> listLocations;
    private Location_Adapter location_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        // ánh xạ

        rcl = view.findViewById(R.id.rcl_home);
        img_location = view.findViewById(R.id.img_location);
        tv_location = view.findViewById(R.id.tv_location);


        // Sự kiện

        /// Sự kiện load images
        rcl.setHasFixedSize(true);
        rcl.setLayoutManager(new LinearLayoutManager(getActivity()));
        mUploads = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        mDatabaseRef.orderByChild("mName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Posts upload = postSnapshot.getValue(Posts.class);
                    mUploads.add(upload);
                }
                mAdapter = new ProductAdapter(mUploads, getContext());
                rcl.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        /// Sự kiện lấy địa chỉ

        img_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAndShowDialog(getContext());
            }
        });

        return view;
    }
//    public void onpenDialogLoction(){
//        DialogFragment dialogFragment = new Location();
//        dialogFragment.show(getFragmentManager(), "example dialog");
//
//    }
    public void createAndShowDialog(Context context) {

        Dialog dialog = new Dialog(context, R.style.FullScreenDialog);
        dialog.setContentView(R.layout.dialog_location);
        RecyclerView rcl_dialogLocation = dialog.findViewById(R.id.rcl_location);
        rcl_dialogLocation.setHasFixedSize(true);
        rcl_dialogLocation.setLayoutManager(new LinearLayoutManager(getActivity()));
        listLocations = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("local");
        mDatabaseRef.orderByChild("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Location_model upload = postSnapshot.getValue(Location_model.class);
                    listLocations.add(upload);
                }
                location_adapter = new Location_Adapter(listLocations, getContext());
                rcl_dialogLocation.setAdapter(location_adapter);
                location_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }
}