package com.example.datn_nguyentuanngoc_10117039.Model;

public class Users {
    private String userName, phone, password, fullName, address, birthday;
    private String role;

    public Users(String userName, String phone, String password, String fullName, String address, String role, String birthday) {
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.role = role;
        this.birthday = birthday;
    }

    public Users() {
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
