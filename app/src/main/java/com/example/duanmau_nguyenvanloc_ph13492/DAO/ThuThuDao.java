package com.example.duanmau_nguyenvanloc_ph13492.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duanmau_nguyenvanloc_ph13492.Model.ThuThu;
import com.example.duanmau_nguyenvanloc_ph13492.SQL.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDao {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private Context context;

    public ThuThuDao(Context context) {
        this.context = context;
        openHelper = new SQLiteHelper(context);
        db = openHelper.getWritableDatabase();
    }
    // thêm
    public boolean insertThuThu(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put("MaTT",thuThu.getMaTT());
        values.put("HoTen",thuThu.getHoTen());
        values.put("MatKhau",thuThu.getMatKhau());
        if(db.insert("THUTHU",null,values)<0){
            return false;
        }else {
            return true;
        }
    }
    public long insetAdmin(){
        ContentValues values = new ContentValues();
        values.put("MaTT","admin");
        values.put("HoTen","admin");
        values.put("MatKhau","admin");
        return db.insert("THUTHU",null,values);
    }
    // lấy all
    public List<ThuThu> getAllThuThu(){
        List<ThuThu> listTT = new ArrayList<>();
        Cursor cursor = db.query("THUTHU",null,null,null,
                null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            ThuThu thuThu = new ThuThu();
            thuThu.setMaTT(cursor.getString(0));
            thuThu.setHoTen(cursor.getString(1));
            thuThu.setMatKhau(cursor.getString(2));
            listTT.add(thuThu);
            cursor.moveToNext();
        }
        cursor.close();
        return listTT;
    }
    // edit pass
    public boolean updatePass(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put("MatKhau",thuThu.getMatKhau());
        if(db.update("THUTHU",values,"MaTT=?",new String[]{thuThu.getMaTT()})<0){
            return false;
        }else {
            return true;
        }
    }
    // delete
    public boolean deleteThuThu(ThuThu thuThu){
        if(db.delete("THUTHU","MaTT=?",new String[]{thuThu.getMaTT()})<0){
            return false;
        }else {
            return true;
        }
    }
    // lấy pass
    public String passCu(String MaTT){
        String pass = "";
        Cursor cursor = db.rawQuery("Select MatKhau from THUTHU where MaTT=?",new String[]{MaTT});
        if(cursor.moveToFirst()){
            pass = cursor.getString(0);
        }
        return pass;
    }
    //

    public Boolean checkUser(ThuThu thuThu){
        Cursor cursor = db.rawQuery("SELECT * FROM THUTHU WHERE MaTT=?",new String[]{thuThu.getMaTT()});
        if(cursor.getCount()>0){
            return false;
        }
        return true;
    }

    public Boolean checkThanhCong(String u, String p){
        Cursor cursor = db.rawQuery("SELECT * FROM THUTHU WHERE MaTT=? AND MatKhau=?",new String[]{u, p});
        if(cursor.getCount()>0){
            return true;
        }
        return false;

    }
    public List<ThuThu> getAllTT(){
        List<ThuThu> list = new ArrayList<>();

        Cursor cursor
                 = db.rawQuery("select * from THUTHU where MaTT not in (?)",new String[]{"admin"});
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            ThuThu thuThu = new ThuThu();
            thuThu.setMaTT(cursor.getString(0));
            thuThu.setHoTen(cursor.getString(1));
            thuThu.setMatKhau(cursor.getString(2));
            list.add(thuThu);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<ThuThu> getAlltheoTen(String ten){
        List<ThuThu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from THUTHU where MaTT like ?",new String[]{ten});
        if(cursor.moveToFirst()){
            ThuThu thuThu = new ThuThu();
            thuThu.setMaTT(cursor.getString(0));
            thuThu.setHoTen(cursor.getString(1));
            thuThu.setMatKhau(cursor.getString(2));
            list.add(thuThu);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public String TenTT(String mtt){
        String name="";
        Cursor cursor = db.rawQuery("Select HoTen from THUTHU where MaTT=?",new String[]{mtt});
        if(cursor.moveToFirst()){
            name=cursor.getString(0);
        }
        cursor.close();
        return name;
    }

}
