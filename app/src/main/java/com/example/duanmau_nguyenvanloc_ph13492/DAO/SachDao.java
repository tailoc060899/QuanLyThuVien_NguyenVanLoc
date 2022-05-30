package com.example.duanmau_nguyenvanloc_ph13492.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duanmau_nguyenvanloc_ph13492.Model.Sach;
import com.example.duanmau_nguyenvanloc_ph13492.Model.Top10;
import com.example.duanmau_nguyenvanloc_ph13492.SQL.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class SachDao {
    SQLiteOpenHelper sqLiteHelper;
    SQLiteDatabase db;
    private Context context;

    public SachDao(Context context) {
        this.context = context;
        sqLiteHelper = new SQLiteHelper(context);
        db = sqLiteHelper.getWritableDatabase();
    }

    // thêm sách
    public boolean insertSach(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("MaLoaiSach", sach.getMaLoai());
        values.put("GiaThue", sach.getGiaThue());
        values.put("TenSach", sach.getTenSach());
        values.put("GiaKM",sach.getGiaKM());
        values.put("SL",sach.getSluong());
        if (db.insert("SACH", null, values) < 0) {
            return false;
        } else {
            return true;
        }
    }

    // lấy all
    public List<Sach> getAllSach() {
        List<Sach> listSach = new ArrayList<>();
        Cursor cursor = db.query("SACH", null, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Sach sach = new Sach();
            sach.setMaSach(cursor.getInt(0));
            sach.setMaLoai(cursor.getInt(1));
            sach.setGiaThue(cursor.getInt(2));
            sach.setTenSach(cursor.getString(3));
            sach.setGiaKM(cursor.getInt(4));
            sach.setSluong(cursor.getInt(5));
            listSach.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return listSach;
    }

    // edit
    public boolean editSach(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("MaLoaiSach", sach.getMaLoai());
        values.put("GiaThue", sach.getGiaThue());
        values.put("TenSach", sach.getTenSach());
        values.put("GiaKM",sach.getGiaKM());
        values.put("SL",sach.getSluong());
        if (db.update("SACH", values, "MaSach=?", new String[]{String.valueOf(sach.getMaSach())}) < 0) {
            return false;
        } else {
            return true;
        }
    }

    // xóa
    public boolean deleteSach(Sach sach) {
        if (db.delete("SACH", "MaSach=?", new String[]{String.valueOf(sach.getMaSach())}) < 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteMaLoai(int maloai) {
        if (db.delete("SACH", "MaLoaiSach=?", new String[]{String.valueOf(maloai)}) < 0) {
            return false;
        } else {
            return true;
        }
    }

    // lấy id
    public List<String> getAllId() {
        List<String> listSachId = new ArrayList<>();
        Cursor cursor = db.query("SACH", null, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Sach sach = new Sach();
            sach.setMaSach(cursor.getInt(0));
            sach.setMaLoai(cursor.getInt(1));
            sach.setGiaThue(cursor.getInt(2));
            sach.setTenSach(cursor.getString(3));
            listSachId.add(sach.getTenSach());
            cursor.moveToNext();
        }
        cursor.close();
        return listSachId;
    }

    // lấy giá theo id;
    public int giaThue(String TenSach) {
        int gia = 0;
        Cursor cursor = db.rawQuery("Select GiaThue from SACH where TenSach=?", new String[]{TenSach});
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                gia = Integer.parseInt(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return gia;
    }
    // lấy tên sách theo id
    public String tenSach(String id){
        String tenSach = "";
        Cursor cursor = db.rawQuery("select TenSach from SACH where MaSach=?",new String[]{id});
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                tenSach = cursor.getString(0);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return tenSach;
    }
    // lấy id theo tên
    public int GetID(String name){
        int id = 0;
        Cursor cursor = db.rawQuery("Select MaSach from SACH where TenSach=?",new String[]{name});
        if(cursor.moveToFirst()){
            id =  cursor.getInt(0);
        }
        cursor.close();
        return id;
    }
    public List<Sach> getsll(int sl){
        List<Sach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from SACH where SL<=? ",new String[]{String.valueOf(sl)});
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Sach sach = new Sach();
            sach.setMaSach(cursor.getInt(0));
            sach.setMaLoai(cursor.getInt(1));
            sach.setGiaThue(cursor.getInt(2));
            sach.setTenSach(cursor.getString(3));
            sach.setGiaKM(cursor.getInt(4));
            sach.setSluong(cursor.getInt(5));
            list.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }




}
