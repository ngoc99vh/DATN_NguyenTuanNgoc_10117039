package com.example.datn_nguyentuanngoc_10117039.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_nguyentuanngoc_10117039.Dialog.Location;
import com.example.datn_nguyentuanngoc_10117039.Model.Location_model;
import com.example.datn_nguyentuanngoc_10117039.R;

import java.util.ArrayList;

public class Location_Adapter extends RecyclerView.Adapter<Location_Adapter.ViewHolder> {
    public ArrayList<Location_model> listsanphams;
    Context context;

    public Location_Adapter(ArrayList<Location_model> listsanphams, Context context) {
        this.listsanphams = listsanphams;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog_location, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Location_model uploadCurrent = listsanphams.get(position);
        holder.textViewName.setText(uploadCurrent.getName());

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
        public TextView textViewName;

        public ViewHolder(View view) {
            super(view);
            textViewName = itemView.findViewById(R.id.tv_name_itP);

        }
    }
}

