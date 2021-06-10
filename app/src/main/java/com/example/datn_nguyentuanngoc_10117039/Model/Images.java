package com.example.datn_nguyentuanngoc_10117039.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Images {
//    private ArrayList<String> images;
//
//    public Images() {
//        images = new ArrayList<>();
//    }
//
//    public String getImageByID(int id) {
//        return images.get(id);
//    }
//
//    public void updateImageByID(int id, String image) {
//        images.remove(id);
//        images.add(id, image);
//    }
//
//
//    public void addImage(String image2) {
//        images.add(image2);
//    }
//
//    public int getNumberImages() {
//        return images.size();
//    }
    private String image1, image2;

    public Images() {
    }

    public Images(String image1, String image2) {
        this.image1 = image1;
        this.image2 = image2;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }
}
