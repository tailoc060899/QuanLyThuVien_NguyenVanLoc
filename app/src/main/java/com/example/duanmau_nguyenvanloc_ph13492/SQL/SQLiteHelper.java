package com.example.duanmau_nguyenvanloc_ph13492.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteHelper extends SQLiteOpenHelper {
    public static String NAME = "LIBMANA111.db";
    public static int Version = 1;
    public SQLiteHelper( Context context) {
        super(context, NAME, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // tạo bảng sách
        String tableSach = "Create table SACH (" +
                "MaSach Integer primary key autoincrement," +
                "MaLoaiSach Integer references LOAISACH(MaLoai)," +
                "GiaThue Integer not null," +
                "TenSach Text not null, " +
                "GiaKM Integer not null, " +
                "SL Integer not null " +
                ") ";
        db.execSQL(tableSach);

        // tạo bảng loại sách
        String tableLoaiSach = "create table LOAISACH (" +
                "MaLoai Integer primary key autoincrement," +
                "TenLoai text not null)";
        db.execSQL(tableLoaiSach);

        // tạo bảng thành viên
        String tableTV = "create table THANHVIEN (" +
                "MaTV Integer primary key autoincrement," +
                "HoTen text not null," +
                "NamSinh date not null)";
        db.execSQL(tableTV);

        // tạo bảng thủ thư
        String tableTT = "create table THUTHU (" +
                "MaTT text primary key," +
                "HoTen text not null," +
                "MatKhau text not null)";
        db.execSQL(tableTT);

        // tạo bảng phiếu mượn
        String tablePM = "create table PHIEUMUON (" +
                "MaPM integer primary key autoincrement," +
                "MaTT text references THUTHU(MaTT)," +
                "MaTV integer references THANHVIEN(MaTV)," +
                "MaSach integer references SACH(MaSach)," +
                "TienThue integer not null," +
                "TraSach integer not null," +
                "NgayMuon date not null )";
        db.execSQL(tablePM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists SACH");
        db.execSQL("drop table if exists LOAISACH");
        db.execSQL("drop table if exists THANHVIEN");
        db.execSQL("drop table if exists THUTHU");
        db.execSQL("drop table if exists PHIEUMUON");
        onCreate(db);
    }
}
