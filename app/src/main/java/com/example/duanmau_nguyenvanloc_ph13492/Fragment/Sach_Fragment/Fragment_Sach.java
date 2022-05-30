package com.example.duanmau_nguyenvanloc_ph13492.Fragment.Sach_Fragment;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.LoaiSachDao;
import com.example.duanmau_nguyenvanloc_ph13492.DAO.SachDao;
import com.example.duanmau_nguyenvanloc_ph13492.Model.Sach;
import com.example.duanmau_nguyenvanloc_ph13492.Model.TheLoai;
import com.example.duanmau_nguyenvanloc_ph13492.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class Fragment_Sach extends Fragment {
    RecyclerView recyclerView;
    Adapter_Sach adapter_sach;
    SachDao sachDao;
    FloatingActionButton button;
    List<Sach> listSach;
    List<TheLoai> listTL;
    List<String> spinnerLS;
    LoaiSachDao loaiSachDao;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_sach);
        sachDao = new SachDao(view.getContext());
        button = view.findViewById(R.id.btn_add_sach);
        editText = view.findViewById(R.id.ed_timkiem);
        listSach = sachDao.getAllSach();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter_sach = new Adapter_Sach(listSach,view.getContext());
        recyclerView.setAdapter(adapter_sach);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    adapter_sach = new Adapter_Sach(sachDao.getsll(Integer.parseInt(editText.getText().toString())),view.getContext());
                    recyclerView.setAdapter(adapter_sach);
                    return true;
                }
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loaiSachDao =new LoaiSachDao(view.getContext());
                listTL = loaiSachDao.getAllLoaiSach();
                spinnerLS = loaiSachDao.allID();
                if(listTL.isEmpty()){
                    Toast.makeText(view.getContext(), "chưa có loại sách nào", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater1 = Fragment_Sach.this.getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.add_sach,null);
                Spinner spinner = view1.findViewById(R.id.sp_maloai);
                EditText ed_tenSach = view1.findViewById(R.id.ed_tensach);
                EditText ed_giathue = view1.findViewById(R.id.ed_giathue);
                EditText ed_km = view1.findViewById(R.id.ed_giakm);
                EditText ed_sl = view1.findViewById(R.id.ed_sl);
                builder.setView(view1);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(view1.getContext(), android.R.layout.simple_spinner_item,spinnerLS);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                builder.setPositiveButton("thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(ed_tenSach.getText().toString().isEmpty()){
                            Toast.makeText(view1.getContext(), "tên sách không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(ed_giathue.getText().toString().isEmpty()){
                            Toast.makeText(view1.getContext(), "giá sách không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(ed_km.getText().toString().isEmpty()){
                            Toast.makeText(view1.getContext(), "giá km không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Sach sach = new Sach();
                        sach.setMaLoai(loaiSachDao.GetID(spinner.getSelectedItem().toString()));
                        sach.setTenSach(ed_tenSach.getText().toString());
                        sach.setGiaThue(Integer.parseInt(ed_giathue.getText().toString()));
                        sach.setGiaKM(Integer.parseInt(ed_km.getText().toString()));
                        sach.setSluong(Integer.parseInt(ed_sl.getText().toString()));
                        boolean kq = sachDao.insertSach(sach);
                        if(kq){
                            Toast.makeText(view.getContext(), "thêm thành công", Toast.LENGTH_SHORT).show();
                        }
                        listSach = sachDao.getAllSach();
                        adapter_sach = new Adapter_Sach(listSach,view.getContext());
                        recyclerView.setAdapter(adapter_sach);
                        adapter_sach.notifyDataSetChanged();
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