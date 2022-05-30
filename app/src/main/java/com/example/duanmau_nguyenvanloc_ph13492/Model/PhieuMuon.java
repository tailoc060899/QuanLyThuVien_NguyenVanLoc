package com.example.duanmau_nguyenvanloc_ph13492.Model;

public class PhieuMuon {
    private int MaPm;
    private int MaTv;
    private int MaSach;
    private String MaTT;
    private String NgayMuon;
    private int traSach;
    private int tienThue;

    public PhieuMuon() {
    }

    public PhieuMuon(int maPm, int maTv, int maSach, String maTT, String ngayMuon, int traSach, int tienThue) {
        MaPm = maPm;
        MaTv = maTv;
        MaSach = maSach;
        MaTT = maTT;
        NgayMuon = ngayMuon;
        this.traSach = traSach;
        this.tienThue = tienThue;
    }

    public int getMaPm() {
        return MaPm;
    }

    public void setMaPm(int maPm) {
        MaPm = maPm;
    }

    public int getMaTv() {
        return MaTv;
    }

    public void setMaTv(int maTv) {
        MaTv = maTv;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public String getMaTT() {
        return MaTT;
    }

    public void setMaTT(String maTT) {
        MaTT = maTT;
    }

    public String getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        NgayMuon = ngayMuon;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }
}
