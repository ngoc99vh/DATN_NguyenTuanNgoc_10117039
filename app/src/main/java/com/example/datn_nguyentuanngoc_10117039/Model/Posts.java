package com.example.datn_nguyentuanngoc_10117039.Model;

import java.io.Serializable;
import java.util.Date;

public class Posts implements Serializable {
    private String pName, pDate, pDongxe, pColor, pKhuvuc, pThongtin, pChuxe,pStatus,pCondition, pDongco,pCreateat;
    private Images images;
    private String id;
    private Float pPice, pKmuse;

    public Posts(String id,String pName, String pDate, String pDongxe, String pColor, String pKhuvuc, String pThongtin, String pChuxe, String pStatus, String pCondition, String pDongco, Images images, Float pPice, Float pKmuse,String pCreateat) {
        this.pName = pName;
        this.pDate = pDate;
        this.pDongxe = pDongxe;
        this.pColor = pColor;
        this.pKhuvuc = pKhuvuc;
        this.pThongtin = pThongtin;
        this.pChuxe = pChuxe;
        this.pStatus = pStatus;
        this.pCondition = pCondition;
        this.pDongco = pDongco;
        this.images = images;
        this.pPice = pPice;
        this.pKmuse = pKmuse;
        this.id = id;
        this.pCreateat = pCreateat;
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

    public String getpDongxe() {
        return pDongxe;
    }

    public void setpDongxe(String pDongxe) {
        this.pDongxe = pDongxe;
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

    public String getpChuxe() {
        return pChuxe;
    }

    public void setpChuxe(String pChuxe) {
        this.pChuxe = pChuxe;
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

    public String getpDongco() {
        return pDongco;
    }

    public void setpDongco(String pDongco) {
        this.pDongco = pDongco;
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

    public Float getpKmuse() {
        return pKmuse;
    }

    public void setpKmuse(Float pKmuse) {
        this.pKmuse = pKmuse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpCreateat() {
        return pCreateat;
    }

    public void setpCreateat(String pCreateat) {
        this.pCreateat = pCreateat;
    }
}
