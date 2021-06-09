package com.example.datn_nguyentuanngoc_10117039.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.example.datn_nguyentuanngoc_10117039.Fragment.Home;
import com.example.datn_nguyentuanngoc_10117039.Model.Location_model;
import com.example.datn_nguyentuanngoc_10117039.R;

import java.util.ArrayList;

public class Location_Adapter extends RecyclerView.Adapter<Location_Adapter.ViewHolder> {
    private static final String TAG = "locnt";
    public ArrayList<Location_model> listsanphams;
    Context context;

    Test test;
    private int pos;

    public void setTest(Test test) {
        this.test = test;
    }

    public Location_Adapter(ArrayList<Location_model> listsanphams, Context context, int pos) {
        this.listsanphams = listsanphams;
        this.context = context;
        this.pos = pos;
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
        Location_model location_model = listsanphams.get(position);
        holder.textViewName1.setVisibility(View.GONE);
        holder.textViewName.setText(location_model.getName());
        if (pos == position){
            holder.textViewName1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listsanphams.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewName1;
        private  SharedPreferences saveInfoAccount;

        public ViewHolder(View view) {
            super(view);
            saveInfoAccount = context.getSharedPreferences("saveInfo", Context.MODE_PRIVATE);
            textViewName = itemView.findViewById(R.id.tv_name_itP);
            textViewName1 = itemView.findViewById(R.id.tv_name_itP1);

            textViewName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    test.onClick(v, getAdapterPosition());

                }
            });
        }

    }

    public interface Test {
        void onClick(View v, int possition);

    }
}

