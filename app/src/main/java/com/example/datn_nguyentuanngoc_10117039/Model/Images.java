package com.example.datn_nguyentuanngoc_10117039.Model;

import java.io.Serializable;

public class Images implements Serializable {
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
