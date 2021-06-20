package com.example.datn_nguyentuanngoc_10117039.Model;

public class Automakers {
    private String name, image;


    public Automakers(String image, String name) {
        this.name = name;
        this.image = image;

    }

    public Automakers() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
