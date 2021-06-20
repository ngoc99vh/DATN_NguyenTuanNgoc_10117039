package com.example.datn_nguyentuanngoc_10117039.Model;

import java.io.Serializable;

public class Posts implements Serializable {
    private String pName, pDate, pDongxe, pColor, pKhuvuc, pThongtin, pChuxe,pStatus,pCondition;
    private Images images;
    private Float pPice, pKmuse;

    public Posts(String pName, String pDate, String pDongco, String pColor, String pKhuvuc, String pThongtin, Images images, Float pPice, String pStatus, String pChuxe, String pCondition, Float pKmuse) {
        this.pName = pName;
        this.pDate = pDate;
        this.pDongxe = pDongco;
        this.pColor = pColor;
        this.pKhuvuc = pKhuvuc;
        this.pThongtin = pThongtin;
        this.images = images;
        this.pPice = pPice;
        this.pStatus = pStatus;
        this.pChuxe = pChuxe;
        this.pCondition = pCondition;
        this.pKmuse = pKmuse;
    }
    public Posts(){}

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDate() {
        return pDate;
    }

    public void setpDate(String pDate) {
        this.pDate = pDate;
    }

    public String getpDongco() {
        return pDongxe;
    }

    public void setpDongco(String pDongco) {
        this.pDongxe = pDongco;
    }

    public String getpColor() {
        return pColor;
    }

    public void setpColor(String pColor) {
        this.pColor = pColor;
    }

    public String getpKhuvuc() {
        return pKhuvuc;
    }

    public void setpKhuvuc(String pKhuvuc) {
        this.pKhuvuc = pKhuvuc;
    }

    public String getpThongtin() {
        return pThongtin;
    }

    public void setpThongtin(String pThongtin) {
        this.pThongtin = pThongtin;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Float getpPice() {
        return pPice;
    }

    public void setpPice(Float pPice) {
        this.pPice = pPice;
    }

    public String getpDongxe() { return pDongxe; }

    public void setpDongxe(String pDongxe) {
        this.pDongxe = pDongxe;
    }

    public String getpChuxe() {
        return pChuxe;
    }

    public void setpChuxe(String pChuxe) {
        this.pChuxe = pChuxe;
    }

    public Float getpKmuse() {
        return pKmuse;
    }

    public void setpKmuse(Float pKmuse) {
        this.pKmuse = pKmuse;
    }

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public String getpCondition() {
        return pCondition;
    }

    public void setpCondition(String pCondition) {
        this.pCondition = pCondition;
    }
}
