package com.example.duanmau_nguyenvanloc_ph13492.Fragment.PhieuMuon_Fragment;

import static com.example.duanmau_nguyenvanloc_ph13492.Login.Login.KEY_USERNAME;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.PhieuMuonDao;
import com.example.duanmau_nguyenvanloc_ph13492.DAO.SachDao;
import com.example.duanmau_nguyenvanloc_ph13492.DAO.ThanhVienDao;
import com.example.duanmau_nguyenvanloc_ph13492.Model.PhieuMuon;
import com.example.duanmau_nguyenvanloc_ph13492.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;


public class Fragment_PhieuMuon extends Fragment {
    RecyclerView recyclerView;
    Adpater_PhieuMuon Adpater_PhieuMuon;
    List<PhieuMuon> listPM;
    PhieuMuonDao phieuMuonDao;
    FloatingActionButton button;
    Spinner sp_maTV,sp_masach;
    Context context;
    SachDao sachDao;
    CheckBox ck_trasach;
    EditText ed_ngaymuon;
    ThanhVienDao thanhVienDao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_pm);
        button = view.findViewById(R.id.btn_add_pm);
        phieuMuonDao = new PhieuMuonDao(view.getContext());
        listPM = phieuMuonDao.getAllPM();
        context = view.getContext();
        sachDao = new SachDao(context);
        thanhVienDao = new ThanhVienDao(context);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        Adpater_PhieuMuon = new Adpater_PhieuMuon(view.getContext(),listPM);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(Adpater_PhieuMuon);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sachDao.getAllSach().isEmpty()){
                    Toast.makeText(context, "Chưa có sách nào", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(thanhVienDao.getAllTv().isEmpty()){
                    Toast.makeText(context, "Chưa có thành viên nào", Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater1 = Fragment_PhieuMuon.this.getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.add_phieumuon,null);
                sp_maTV  = view1.findViewById(R.id.sp_matv);
                sp_masach = view1.findViewById(R.id.sp_masach);
                ck_trasach = view1.findViewById(R.id.ck_trasach);
                ed_ngaymuon = view1.findViewById(R.id.ed_ngaymuon);
                ImageView img_date = view1.findViewById(R.id.img_dateNgayMuon);
                builder.setView(view1);
                sp_mtv();
                sp_masach();
                img_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDatePicker();
                    }
                });
                builder.setPositiveButton("thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(ed_ngaymuon.getText().toString().isEmpty()){
                            Toast.makeText(view1.getContext(), "Ngày mượn không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent = getActivity().getIntent();
                        PhieuMuon phieuMuon = new PhieuMuon();
                        phieuMuon.setMaTT(intent.getStringExtra(KEY_USERNAME));
                        phieuMuon.setMaSach(sachDao.GetID(sp_masach.getSelectedItem().toString()));
                        phieuMuon.setMaTv(thanhVienDao.GetID(sp_maTV.getSelectedItem().toString()));
                        phieuMuon.setNgayMuon(ed_ngaymuon.getText().toString());
                        phieuMuon.setTraSach(ck_trasach.isChecked()?1:0);
                        int tien = sachDao.giaThue(sp_masach.getSelectedItem().toString());
                        phieuMuon.setTienThue(tien);
                        if(phieuMuonDao.insertPhieuMuon(phieuMuon)){
                            Toast.makeText(context, "thêm thành công", Toast.LENGTH_SHORT).show();
                        }
                        listPM = phieuMuonDao.getAllPM();
                        Adpater_PhieuMuon = new Adpater_PhieuMuon(view.getContext(),listPM);
                        recyclerView.setAdapter(Adpater_PhieuMuon);
                        Adpater_PhieuMuon.notifyDataSetChanged();
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

    public void sp_mtv(){
        thanhVienDao = new ThanhVienDao(context);
        List<String> list = thanhVienDao.getIdTv();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_maTV.setAdapter(adapter);
    }
    public void sp_masach(){
        sachDao = new SachDao(context);
        List<String> list = sachDao.getAllId();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_masach.setAdapter(adapter);
    }

    public void showDatePicker(){
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                ed_ngaymuon.setText(i+"-"+(i1+1)+"-"+i2);
            }
        },y,m,d);
        datePickerDialog.show();
    }

}