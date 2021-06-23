package com.example.datn_nguyentuanngoc_10117039.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datn_nguyentuanngoc_10117039.R;

public class StoreActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        mTextView = (TextView) findViewById(R.id.text);

    }
}