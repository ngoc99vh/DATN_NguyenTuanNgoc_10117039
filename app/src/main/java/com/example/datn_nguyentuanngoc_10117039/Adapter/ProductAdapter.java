package com.example.datn_nguyentuanngoc_10117039.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_nguyentuanngoc_10117039.Activity.DetailPostActivity;
import com.example.datn_nguyentuanngoc_10117039.Model.Images;
import com.example.datn_nguyentuanngoc_10117039.Model.Location_model;
import com.example.datn_nguyentuanngoc_10117039.Model.Posts;
import com.example.datn_nguyentuanngoc_10117039.Model.Users;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private static final String TAG = "locnt";
    public ArrayList<Posts> listsanphams;
    Context context;

    public ProductAdapter(ArrayList<Posts> listsanphams, Context context) {
        this.listsanphams = listsanphams;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.from(viewGroup.getContext()).inflate(R.layout.item_product, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Posts uploadCurrent = listsanphams.get(position);
        holder.textViewName.setText(uploadCurrent.getpName());


        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##",otherSymbols);
        holder.tv_gia.setText(decimalFormat.format(uploadCurrent.getpPice()) + " đ");
        String test = uploadCurrent.getImages().getImage1();
        Log.d(TAG, "img: " + test);
        Picasso.get().load(test)
                .centerCrop().resize(200, 170)
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailPostActivity.class);
                intent.putExtra("sanpham", listsanphams.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.mDatabaseRef = FirebaseDatabase.getInstance().getReference("local");
        holder.mDatabaseRef.orderByChild("id").equalTo(uploadCurrent.getpKhuvuc()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Location_model upload = postSnapshot.getValue(Location_model.class);
                    holder.tv_khuvuc.setText(upload.getName());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listsanphams.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName, tv_gia, tv_khuvuc;
        public ImageView imageView;
        private ArrayList<Location_model> listLocations;
        private DatabaseReference mDatabaseRef;
        public ViewHolder(View view) {
            super(view);
            textViewName = itemView.findViewById(R.id.tv_name_itP);
            imageView = itemView.findViewById(R.id.image_itP);
            tv_gia = itemView.findViewById(R.id.tv_pirce_itP);
            tv_khuvuc = itemView.findViewById(R.id.tv_khuvuc_itP);

        }
        public void getUser(String id) {

        }
    }
}
