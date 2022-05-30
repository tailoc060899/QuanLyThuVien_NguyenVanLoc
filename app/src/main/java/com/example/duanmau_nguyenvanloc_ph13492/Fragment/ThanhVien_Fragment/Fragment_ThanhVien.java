package com.example.duanmau_nguyenvanloc_ph13492.Fragment.ThanhVien_Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.ThanhVienDao;
import com.example.duanmau_nguyenvanloc_ph13492.Model.ThanhVien;
import com.example.duanmau_nguyenvanloc_ph13492.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;


public class Fragment_ThanhVien extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton button;
    List<ThanhVien> listTV;
    ThanhVienDao thanhVienDao;
    Adapter_ThanhVien Adapter_thanhVien;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_thanhvien, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_TV);
        button = view.findViewById(R.id.btn_add_TV);
        thanhVienDao = new ThanhVienDao(view.getContext());
        listTV = thanhVienDao.getAllTv();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        Adapter_thanhVien =new Adapter_ThanhVien(view.getContext(),listTV);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(Adapter_thanhVien);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater1 = Fragment_ThanhVien.this.getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.add_thanhvien,null);
                EditText ed_name = view1.findViewById(R.id.ed_tenTV);
                EditText ed_ns = view1.findViewById(R.id.ed_NamSinhTV);
                ImageView img_date = view1.findViewById(R.id.img_dateNs);
                builder.setView(view1);
                img_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int y = calendar.get(Calendar.YEAR);
                        int m = calendar.get(Calendar.MONTH);
                        int d = calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                ed_ns.setText(i2+"-"+(i1+1)+"-"+i);
                            }
                        },y,m,d);
                        datePickerDialog.show();
                    }
                });
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ThanhVien thanhVien = new ThanhVien();
                        thanhVien.setHoten(ed_name.getText().toString());
                        thanhVien.setNamSinh(ed_ns.getText().toString());
                        if(thanhVienDao.insertTV(thanhVien)){
                            Toast.makeText(view.getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }
                        listTV = thanhVienDao.getAllTv();
                        Adapter_thanhVien =new Adapter_ThanhVien(view.getContext(),listTV);
                        recyclerView.setAdapter(Adapter_thanhVien);
                        Adapter_thanhVien.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
        return view;
    }
}