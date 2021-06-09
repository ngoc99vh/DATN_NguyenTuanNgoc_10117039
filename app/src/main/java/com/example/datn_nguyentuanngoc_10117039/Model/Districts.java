package com.example.datn_nguyentuanngoc_10117039.Model;

public class Districts {
    private int id;
    private String name;
    private Streets streets;
    private Wards wards;

    public Districts() {
    }

    public Districts(int id, String name, Streets streets, Wards wards) {
        this.id = id;
        this.name = name;
        this.streets = streets;
        this.wards = wards;
    }
}
