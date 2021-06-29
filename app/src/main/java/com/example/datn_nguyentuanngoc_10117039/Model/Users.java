package com.example.datn_nguyentuanngoc_10117039.Model;

import java.io.Serializable;

public class Users {
    private String userName, phone, password, fullName, address, birthDay;
    private String role;

    public Users( String address,String birthday, String fullName, String password, String phone, String role,String userName) {
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.birthDay = birthday;
        this.role = role;
    }
    public Users(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthDay;
    }

    public void setBirthday(String birthday) {
        this.birthDay = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
