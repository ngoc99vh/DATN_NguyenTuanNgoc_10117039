package com.example.datn_nguyentuanngoc_10117039.Model;



import android.util.Log;

import java.io.Serializable;

public class Posts {
    private static final String TAG = "locnt";
    private String mName;
    //    private String mImageUrl;
//    private Images images;
    private String img1;



    public Posts() {
    }

    public Posts(String mName, Images images) {
        this.mName = mName;
//        this.images = images;
        this.img1 = images.getImageByID(0);
        Log.d(TAG, "Posts: "+img1);
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

//    public Images getImages() {
//        return images;
//    }
//
//    public void setImages(Images images) {
//        this.images = images;
//    }


}
