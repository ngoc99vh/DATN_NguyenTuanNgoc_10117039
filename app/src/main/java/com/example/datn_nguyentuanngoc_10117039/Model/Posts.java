package com.example.datn_nguyentuanngoc_10117039.Model;

public class Posts {
    private String mName;
    //    private String mImageUrl;
    private Images images;

    public Posts() {
    }

    public Posts(String mName, Images images) {
        this.mName = mName;
        this.images = images;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}
