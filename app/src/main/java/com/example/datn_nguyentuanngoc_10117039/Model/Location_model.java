package com.example.datn_nguyentuanngoc_10117039.Model;

public class Location_model {
    private String code, name;
//    private Districts districts;
    private String id;

    public Location_model() {
    }

    public Location_model(String code, String name, Districts districts, String id) {
        this.code = code;
        this.name = name;
//        this.districts = districts;
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Districts getDistricts() {
//        return districts;
//    }
//
//    public void setDistricts(Districts districts) {
//        this.districts = districts;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
