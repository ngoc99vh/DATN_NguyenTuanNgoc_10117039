package com.example.datn_nguyentuanngoc_10117039.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_nguyentuanngoc_10117039.Activity.DetailPostActivity;
import com.example.datn_nguyentuanngoc_10117039.Activity.UpdateStoreActivity;
import com.example.datn_nguyentuanngoc_10117039.Model.Location_model;
import com.example.datn_nguyentuanngoc_10117039.Model.Posts;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
    private static final String TAG = "locnt";
    public ArrayList<Posts> listsanphams;
    Context context;

    public StoreAdapter(ArrayList<Posts> listsanphams, Context context) {
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
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##", otherSymbols);
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
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(context, holder.itemView);
                popupMenu.getMenuInflater().inflate(R.menu.menu_update_delete, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_menu_sua:
                                Intent intent = new Intent(context, UpdateStoreActivity.class);
                                intent.putExtra("sanpham", listsanphams.get(position));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                break;
                            case R.id.item_menu_xoa:
                                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                                alert.setTitle("Xoá bài viết");
                                alert.setMessage("Bạn có thực sự muốn xoá?");
                                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.mDatabaseRef = FirebaseDatabase.getInstance().getReference("Posts");
                                        holder.mDatabaseRef.orderByChild("id").equalTo(uploadCurrent.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                                    dataSnapshot.getRef().removeValue();
                                                    listsanphams.remove(position);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }
                                });
                                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // close dialog
                                        dialog.cancel();
                                    }
                                });
                                alert.show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return true;
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

    }
}
