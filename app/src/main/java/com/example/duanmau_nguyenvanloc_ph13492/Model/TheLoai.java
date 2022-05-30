package com.example.duanmau_nguyenvanloc_ph13492.Model;

public class TheLoai {
    private int MaTL;
    private String tenTL;

    public TheLoai() {
    }

    public TheLoai(int maTL, String tenTL) {
        MaTL = maTL;
        this.tenTL = tenTL;
    }

    public int getMaTL() {
        return MaTL;
    }

    public void setMaTL(int maTL) {
        MaTL = maTL;
    }

    public String getTenTL() {
        return tenTL;
    }

    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
    }
}
