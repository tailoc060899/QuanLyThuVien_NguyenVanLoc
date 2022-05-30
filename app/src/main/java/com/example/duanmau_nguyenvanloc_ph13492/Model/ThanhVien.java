package com.example.duanmau_nguyenvanloc_ph13492.Model;

public class ThanhVien {
    private int MaTV;
    private String Hoten;
    private String NamSinh;

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String hoten, String namSinh) {
        MaTV = maTV;
        Hoten = hoten;
        NamSinh = namSinh;
    }

    public int getMaTV() {
        return MaTV;
    }

    public void setMaTV(int maTV) {
        MaTV = maTV;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String namSinh) {
        NamSinh = namSinh;
    }
}
