package com.example.datn_nguyentuanngoc_10117039.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_nguyentuanngoc_10117039.Activity.DetailPostActivity;
import com.example.datn_nguyentuanngoc_10117039.Fragment.AcceptFragment;
import com.example.datn_nguyentuanngoc_10117039.Fragment.NoAcceptFragment;
import com.example.datn_nguyentuanngoc_10117039.Model.Location_model;
import com.example.datn_nguyentuanngoc_10117039.Model.Posts;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class ProductAdminAdapter extends RecyclerView.Adapter<ProductAdminAdapter.ViewHolder> {
    private static final String TAG = "locnt";
    private AcceptFragment acceptFragment;
    private NoAcceptFragment noAcceptFragment;
    public ArrayList<Posts> listsanphams;
    Context context;
    String trangthaidh;
    public ProductAdminAdapter(ArrayList<Posts> listsanphams, Context context) {
        this.listsanphams = listsanphams;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.from(viewGroup.getContext()).inflate(R.layout.item_product_admin, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Posts uploadCurrent = listsanphams.get(position);
        holder.textViewName.setText(uploadCurrent.getpName());
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##", otherSymbols);
        holder.tv_gia.setText(decimalFormat.format(uploadCurrent.getpPice()) + " đ");
        String test = uploadCurrent.getImages().getImage1();
        Log.d(TAG, "img: " + test);
        Picasso.get().load(test)
                .centerCrop().resize(200, 170)
                .into(holder.imageView);
        holder.trangthai_product_admin.setText(uploadCurrent.getpStatus());
        holder.color_product_admin.setText("+ "+uploadCurrent.getpColor());
        holder.engin_product_admin.setText("+ "+uploadCurrent.getpDongco());
        holder.km_product_admin.setText("+ "+uploadCurrent.getpKmuse()+" km");
        holder.username_product_admin.setText(uploadCurrent.getpChuxe());
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
        holder.img_trangthai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(context, holder.img_trangthai);
                popupMenu.getMenuInflater().inflate(R.menu.menu_update_status, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_menu_status_acc:
                                trangthaidh = "Đã xác nhận";
                                holder.updateStatus(trangthaidh,uploadCurrent.getId());
                                listsanphams.remove(position);
                                break;
                            case R.id.item_menu_status_noacc:
                                trangthaidh = "Chưa xác nhận";
                                holder.updateStatus(trangthaidh,uploadCurrent.getId());
                                listsanphams.remove(position);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();

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
        public TextView textViewName, tv_gia, tv_khuvuc,trangthai_product_admin,engin_product_admin,color_product_admin,username_product_admin,km_product_admin;
        public ImageView imageView,img_trangthai;
        private ArrayList<Location_model> listLocations;
        private DatabaseReference mDatabaseRef;
        private AcceptFragment acceptFragment;
        private NoAcceptFragment noAcceptFragment;

        public ViewHolder(View view) {
            super(view);
            img_trangthai = itemView.findViewById(R.id.img_trangthai);
            textViewName = itemView.findViewById(R.id.name_product_admin);
            trangthai_product_admin = itemView.findViewById(R.id.trangthai_product_admin);
            km_product_admin = itemView.findViewById(R.id.km_product_admin);
            username_product_admin = itemView.findViewById(R.id.username_product_admin);
            engin_product_admin = itemView.findViewById(R.id.engin_product_admin);
            color_product_admin = itemView.findViewById(R.id.color_product_admin);
            imageView = itemView.findViewById(R.id.img_product_admin);
            tv_gia = itemView.findViewById(R.id.gia_product_admin);
            tv_khuvuc = itemView.findViewById(R.id.khuvuc_product_admin);

        }
        public void updateStatus(String status,long id){
            mDatabaseRef = FirebaseDatabase.getInstance().getReference("Posts");
            mDatabaseRef.orderByChild("id").equalTo(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot child: snapshot.getChildren()) {
                        String key= child.getKey();
                        mDatabaseRef.child(key).child("pStatus").setValue(status);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

}

