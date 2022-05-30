package com.example.duanmau_nguyenvanloc_ph13492.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duanmau_nguyenvanloc_ph13492.Model.TheLoai;
import com.example.duanmau_nguyenvanloc_ph13492.SQL.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDao {
    SQLiteOpenHelper sqLiteHelper;
    SQLiteDatabase db;
    private Context context;

    public LoaiSachDao(Context context) {
        this.context = context;
        sqLiteHelper = new SQLiteHelper(context);
        db = sqLiteHelper.getWritableDatabase();
    }

    // thêm
    public boolean insertLoaiSach(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("TenLoai",theLoai.getTenTL());
        if(db.insert("LOAISACH",null,values)<0){
            return false;
        }else {
            return true;
        }
    }
    // lấy all
    public List<TheLoai> getAllLoaiSach(){
        List<TheLoai> listTheLoai = new ArrayList<>();
        Cursor cursor = db.query("LOAISACH", null,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            TheLoai theLoai = new TheLoai();
            theLoai.setMaTL(cursor.getInt(0));
            theLoai.setTenTL(cursor.getString(1));
            listTheLoai.add(theLoai);
            cursor.moveToNext();
        }
        cursor.close();
        return listTheLoai;
    }
    // sửa
    public boolean updateLoaiSach(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("TenLoai",theLoai.getTenTL());
        if(db.update("LOAISACH",values,"MaLoai=?",new String[]{String.valueOf(theLoai.getMaTL())})<0){
            return false;
        }else {
            return true;
        }
    }
    // xóa
    public boolean xoaLoaiSach(TheLoai theLoai){
        if(db.delete("LOAISACH","MaLoai=?", new String[]{String.valueOf(theLoai.getMaTL())})<0){
            return false;
        }else {
            return true;
        }
    }

    // all id
    public List<String> allID(){
        List<String> listTheLoai = new ArrayList<>();
        Cursor cursor = db.query("LOAISACH", null,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            TheLoai theLoai = new TheLoai();
            theLoai.setMaTL(cursor.getInt(0));
            theLoai.setTenTL(cursor.getString(1));
            listTheLoai.add(theLoai.getTenTL());
            cursor.moveToNext();
        }
        cursor.close();
        return listTheLoai;
    }

    // lấy id theo tên
    public int GetID(String name){
        int id = 0;
        Cursor cursor = db.rawQuery("Select MaLoai from LOAISACH where TenLoai=?",new String[]{name});
        if(cursor.moveToFirst()){
           id =  cursor.getInt(0);
        }
        cursor.close();
        return id;
    }
    // lấy tên theo id
    public String GetTenLoai(int id){
        String name = "";
        Cursor cursor = db.rawQuery("Select TenLoai from LOAISACH where MaLoai=?",new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()){
            name =  cursor.getString(0);
        }
        cursor.close();
        return name;
    }
}
