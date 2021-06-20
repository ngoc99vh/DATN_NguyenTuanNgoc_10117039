package com.example.datn_nguyentuanngoc_10117039.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_nguyentuanngoc_10117039.Model.Automakers;
import com.example.datn_nguyentuanngoc_10117039.Model.Posts;
import com.example.datn_nguyentuanngoc_10117039.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AutomakersAdapter extends RecyclerView.Adapter<AutomakersAdapter.ViewHolder> {
    public ArrayList<Automakers> list;
    Context context;

    public AutomakersAdapter(ArrayList<Automakers> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.from(parent.getContext()).inflate(R.layout.item_automakers, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Automakers automakers = list.get(position);
        holder.textViewName.setText(automakers.getName());
        String img = automakers.getImage();
        Picasso.get().load(img)
                .centerCrop().fit()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            textViewName = itemView.findViewById(R.id.name_itemAutomakers);
            imageView = itemView.findViewById(R.id.img_itemAutomakers);
        }
    }
}
