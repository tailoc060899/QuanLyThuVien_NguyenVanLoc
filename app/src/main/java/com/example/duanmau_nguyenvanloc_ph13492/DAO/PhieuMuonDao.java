package com.example.duanmau_nguyenvanloc_ph13492.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duanmau_nguyenvanloc_ph13492.Model.PhieuMuon;
import com.example.duanmau_nguyenvanloc_ph13492.Model.Top10;
import com.example.duanmau_nguyenvanloc_ph13492.SQL.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDao {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private Context context;

    public PhieuMuonDao(Context context) {
        this.context = context;
        openHelper = new SQLiteHelper(context);
        db = openHelper.getWritableDatabase();
    }
    // thêm
    public boolean insertPhieuMuon(PhieuMuon phieuMuon){
        ContentValues values = new ContentValues();
        values.put("MaTT",phieuMuon.getMaTT());
        values.put("MaTV",phieuMuon.getMaTv());
        values.put("MaSach",phieuMuon.getMaSach());
        values.put("TienThue",phieuMuon.getTienThue());
        values.put("TraSach",phieuMuon.getTraSach());
        values.put("NgayMuon",phieuMuon.getNgayMuon());
        if(db.insert("PHIEUMUON",null,values)<0){
            return false;
        }else {
            return true;
        }
    }
    // lấy all
    public List<PhieuMuon> getAllPM(){
        List<PhieuMuon> listPM = new ArrayList<>();
        Cursor cursor = db.query("PHIEUMUON",null,null,null,
                null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            PhieuMuon phieuMuon = new PhieuMuon();
            phieuMuon.setMaPm(cursor.getInt(0));
            phieuMuon.setMaTT(cursor.getString(1));
            phieuMuon.setMaTv(cursor.getInt(2));
            phieuMuon.setMaSach(cursor.getInt(3));
            phieuMuon.setTienThue(cursor.getInt(4));
            phieuMuon.setTraSach(cursor.getInt(5));
            phieuMuon.setNgayMuon(cursor.getString(6));
            listPM.add(phieuMuon);
            cursor.moveToNext();
        }
        cursor.close();
        return listPM;
    }

    // edit
    public boolean updatePhieuMuon(PhieuMuon phieuMuon){
        ContentValues values = new ContentValues();
        values.put("MaTT",phieuMuon.getMaTT());
        values.put("MaTV",phieuMuon.getMaTv());
        values.put("MaSach",phieuMuon.getMaSach());
        values.put("TienThue",phieuMuon.getTienThue());
        values.put("TraSach",phieuMuon.getTraSach());
        values.put("NgayMuon",phieuMuon.getNgayMuon());
        if(db.update("PHIEUMUON",values,"MaPM=?",new String[]{String.valueOf(phieuMuon.getMaPm())})<0){
            return false;
        }else {
            return true;
        }
    }
    // delete
    public boolean deletePhieuMuon(PhieuMuon phieuMuon){
        if(db.delete("PHIEUMUON","MaPM=?",new String[]{String.valueOf(phieuMuon.getMaPm())})<0){
            return false;
        }else {
            return true;
        }
    }
    // delete phiếu mượn theo mã thành viên
    public boolean deleteIDTV(int id){
        if(db.delete("PHIEUMUON","MaTV=?",new String[]{String.valueOf(id)})<0){
            return false;
        }else {
            return true;
        }
    }
    // delete phiếu mượn theo mã sách
    public boolean deleteIDSach(int id){
        if(db.delete("PHIEUMUON","MaSach=?",new String[]{String.valueOf(id)})<0){
            return false;
        }else {
            return true;
        }
    }
    // top 10
    public List<Top10> top10(){
        List<Top10> listTop = new ArrayList<>();
        String sql = "SELECT TenSach, COUNT(PHIEUMUON.MaSach) as SoLuong FROM SACH " +
                     "INNER JOIN PHIEUMUON ON SACH.MaSach = PHIEUMUON.MaSach " +
                     "GROUP BY SACH.MaSach " +
                     "ORDER BY SoLuong DESC LIMIT 10";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Top10 top10 = new Top10();
                top10.setTen_sach(cursor.getString(0));
                top10.setSoLuong(cursor.getInt(1));
                listTop.add(top10);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return listTop;
    }
    // doanh thu
  public int DoanhThu(String tuNgay,String denNgay){
        int doanhthu=0;
        String sql = "SELECT SUM(TienThue) FROM PHIEUMUON " +
                     "WHERE NgayMuon BETWEEN ? AND ?";
        Cursor cursor = db.rawQuery(sql ,new String[]{tuNgay,denNgay});
        if(cursor.moveToFirst()){
                doanhthu = cursor.getInt(0);
        }
        cursor.close();
        return doanhthu;
  }

}
