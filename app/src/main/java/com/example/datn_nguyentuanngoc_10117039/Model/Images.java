package com.example.datn_nguyentuanngoc_10117039.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Images implements Serializable {
    private ArrayList<String> images;

    public Images() {
        images = new ArrayList<>();
    }

    public String getImageByID(int id) {
        return images.get(id);
    }

    public void updateImageByID(int id, String image) {
        images.remove(id);
        images.add(id, image);
    }


    public void addImage(String image2) {
        images.add(image2);
    }

    public int getNumberImages() {
        return images.size();
    }
}
