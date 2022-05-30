package com.example.duanmau_nguyenvanloc_ph13492.Fragment.LoaiSach_Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.LoaiSachDao;
import com.example.duanmau_nguyenvanloc_ph13492.Model.TheLoai;
import com.example.duanmau_nguyenvanloc_ph13492.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class Fragment_LoaiSach extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton button;
    List<TheLoai> list;
    LoaiSachDao loaiSachDao;
    Adapter_LoaiSach adapter_loaiSach;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_loaisach, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        button = view.findViewById(R.id.btn_add);
        loaiSachDao = new LoaiSachDao(view.getContext());
        list = loaiSachDao.getAllLoaiSach();
        adapter_loaiSach = new Adapter_LoaiSach(view.getContext(),list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_loaiSach);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater1 = Fragment_LoaiSach.this.getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.add_loaisach,null);
                EditText editText = view1.findViewById(R.id.ed_tenLoai);
                builder.setView(view1);
                builder.setPositiveButton("thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(editText.getText().toString().isEmpty()){
                            Toast.makeText(view1.getContext(), "tên loại sách không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        TheLoai theLoai = new TheLoai();
                        theLoai.setTenTL(editText.getText().toString());
                        boolean kq = loaiSachDao.insertLoaiSach(theLoai);
                        if(kq){
                            Toast.makeText(view1.getContext(), "thêm thành công", Toast.LENGTH_SHORT).show();
                        }
                        list = loaiSachDao.getAllLoaiSach();
                        adapter_loaiSach = new Adapter_LoaiSach(view.getContext(),list);
                        recyclerView.setAdapter(adapter_loaiSach);
                        adapter_loaiSach.notifyDataSetChanged();
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