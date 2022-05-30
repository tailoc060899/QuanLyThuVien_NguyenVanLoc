package com.example.duanmau_nguyenvanloc_ph13492.Model;

public class Sach {
    private int MaSach;
    private int MaLoai;
    private int giaThue;
    private String TenSach;
    private int GiaKM;
    private int sluong;

    public Sach() {
    }

    public Sach(int maSach, int maLoai, int giaThue, String tenSach, int giaKM, int sluong) {
        MaSach = maSach;
        MaLoai = maLoai;
        this.giaThue = giaThue;
        TenSach = tenSach;
        GiaKM = giaKM;
        this.sluong = sluong;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public int getGiaKM() {
        return GiaKM;
    }

    public void setGiaKM(int giaKM) {
        GiaKM = giaKM;
    }

    public int getSluong() {
        return sluong;
    }

    public void setSluong(int sluong) {
        this.sluong = sluong;
    }
}
