package com.example.duanmau_nguyenvanloc_ph13492.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duanmau_nguyenvanloc_ph13492.Model.ThanhVien;
import com.example.duanmau_nguyenvanloc_ph13492.SQL.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDao {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private Context context;

    public ThanhVienDao(Context context) {
        this.context = context;
        openHelper = new SQLiteHelper(context);
        db = openHelper.getWritableDatabase();
    }
    // thêm
    public boolean insertTV(ThanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put("HoTen",thanhVien.getHoten());
        values.put("NamSinh",thanhVien.getNamSinh());
        if(db.insert("THANHVIEN",null,values)<0){
            return false;
        }
        return true;
    }
    //  lấy all
    public List<ThanhVien> getAllTv(){
        List<ThanhVien> listTV = new ArrayList<>();
        Cursor cursor = db.query("THANHVIEN",null,null,null,
                null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            ThanhVien thanhVien = new ThanhVien();
            thanhVien.setMaTV(cursor.getInt(0));
            thanhVien.setHoten(cursor.getString(1));
            thanhVien.setNamSinh(cursor.getString(2));
            listTV.add(thanhVien);
            cursor.moveToNext();
        }
        cursor.close();
        return listTV;
    }
    // edit
    public boolean updateTV(ThanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put("HoTen",thanhVien.getHoten());
        values.put("NamSinh",thanhVien.getNamSinh());
        if(db.update("THANHVIEN",values,"MaTV=?",new String[]{String.valueOf(thanhVien.getMaTV())})<0){
            return false;
        }
        return true;
    }
    // delete
    public boolean deleteTV(ThanhVien thanhVien){
        if(db.delete("THANHVIEN","MaTV=?",new String[]{String.valueOf(thanhVien.getMaTV())})<0){
            return false;
        }
        return true;
    }

    // getId
    public List<String> getIdTv(){
        List<String> list = new ArrayList<>();
        Cursor cursor = db.query("THANHVIEN",null,null,null,
                null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            ThanhVien thanhVien = new ThanhVien();
            thanhVien.setMaTV(cursor.getInt(0));
            thanhVien.setHoten(cursor.getString(1));
            thanhVien.setNamSinh(cursor.getString(2));
            list.add(thanhVien.getHoten());
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public String TenTV(String id){
        String tenTV = "";
        Cursor cursor = db.rawQuery("Select HoTen from THANHVIEN where MaTV=?",new String[]{id});
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                tenTV = cursor.getString(0);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return tenTV;
    }
    // lấy id theo tên
    public int GetID(String name){
        int id = 0;
        Cursor cursor = db.rawQuery("Select MaTV from THANHVIEN where HoTen=?",new String[]{name});
        if(cursor.moveToFirst()){
            id =  cursor.getInt(0);
        }
        cursor.close();
        return id;
    }
    // lấy tên theo id
    public String GetTenTV(int id){
        String name = "";
        Cursor cursor = db.rawQuery("Select HoTen from THANHVIEN where MaTV=?",new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()){
            name =  cursor.getString(0);
        }
        cursor.close();
        return name;
    }
}
