package com.example.datn_nguyentuanngoc_10117039.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
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
    private static final String TAG = "locnt";

    RecyclerView rcl;
    ImageView img_location;
    TextView tv_location;
    private DatabaseReference mDatabaseRef;
    private ProductAdapter mAdapter;
    private ArrayList<Posts> mUploads;

    private Dialog mDialog;
    private int mPosition;

    private ArrayList<Location_model> listLocations;
    private Location_Adapter location_adapter;
    String NameLocation = "";

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
//                    Log.d(TAG, "upload: "+upload.getImages().getNumberImages());
                    mUploads.add(upload);
                }
                Log.d(TAG, "mUploads: "+mUploads.size());
//                Log.d(TAG, "mUploads: "+mUploads.get());
                mAdapter = new ProductAdapter(mUploads, getContext());
                rcl.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Dialog
        createDialog(getContext());
        mPosition = 0;

        /// Sự kiện lấy địa chỉ


        img_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();
            }
        });

        if (getArguments() != null) {
            NameLocation = getArguments().getString("NameLocation", "");
            if (!TextUtils.isEmpty(NameLocation))
                Log.d("LOG1", NameLocation);
        } else {
            tv_location.setText("Việt Nam");
        }


        return view;
    }
    public void createDialog(Context context){

        mDialog = new Dialog(context, R.style.FullScreenDialog);
        mDialog.setContentView(R.layout.dialog_location);
        RecyclerView rcl_dialogLocation = mDialog.findViewById(R.id.rcl_location);
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
                listLocations.add(0, new Location_model("-1", "Việt Nam", null, ""));
                location_adapter = new Location_Adapter(listLocations, getContext(), mPosition);
                location_adapter.setTest(new Location_Adapter.Test() {
                    @Override
                    public void onClick(View v, int possition) {
                        mPosition = possition;
                        Location_model location_model = listLocations.get(possition);

                        tv_location.setText(location_model.getName());
                        mDialog.dismiss();
                    }
                });
                rcl_dialogLocation.setAdapter(location_adapter);
                location_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        WindowManager.LayoutParams layoutParams = mDialog.getWindow().getAttributes();
        mDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        mDialog.getWindow().setAttributes(layoutParams);
    }
}