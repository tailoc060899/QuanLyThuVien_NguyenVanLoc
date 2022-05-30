package com.example.duanmau_nguyenvanloc_ph13492.Model;

public class Top10 {
    private String ten_sach;
    private int soLuong;

    public Top10() {
    }

    public Top10(String id_sach, int soLuong) {
        this.ten_sach = id_sach;
        this.soLuong = soLuong;
    }

    public String getTen_sach() {
        return ten_sach;
    }

    public void setTen_sach(String ten_sach) {
        this.ten_sach = ten_sach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
