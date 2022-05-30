package com.example.duanmau_nguyenvanloc_ph13492.Fragment.DoanhThu_Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.PhieuMuonDao;
import com.example.duanmau_nguyenvanloc_ph13492.R;

import java.util.Calendar;


public class Fragment_DoanhThu extends Fragment {
    Button btn_tungay,btn_denngay,btn_thongke;
    EditText ed_tungay,ed_denngay;
    TextView tv_doanhthu;
    Context context;
    PhieuMuonDao phieuMuonDao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        btn_tungay = view.findViewById(R.id.btn_tuNgay);
        btn_denngay = view.findViewById(R.id.btn_denNgay);
        btn_thongke = view.findViewById(R.id.btn_thongke);
        ed_tungay = view.findViewById(R.id.ed_tuNgay);
        ed_denngay = view.findViewById(R.id.ed_denNgay);
        tv_doanhthu = view.findViewById(R.id.tv_doanhthu);
        phieuMuonDao = new PhieuMuonDao(view.getContext());
        context = view.getContext();
        btn_tungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TuNgaydatePickDialog();
            }
        });
        btn_denngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                denNgaydatePickDialog();
            }
        });
        btn_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ngay1 = ed_tungay.getText().toString();
                String ngay2 = ed_denngay.getText().toString();
                int t = phieuMuonDao.DoanhThu(ngay1,ngay2);
                tv_doanhthu.setText("DOANH THU: "+t+" VND");
            }
        });
        return view;
    }

    public void TuNgaydatePickDialog(){
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                ed_tungay.setText(i+"-"+(i1+1)+"-"+i2);
            }
        },y,m,d);
        datePickerDialog.show();
    }
    public void denNgaydatePickDialog(){
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                ed_denngay.setText(i+"-"+(i1+1)+"-"+i2);
            }
        },y,m,d);
        datePickerDialog.show();
    }
}