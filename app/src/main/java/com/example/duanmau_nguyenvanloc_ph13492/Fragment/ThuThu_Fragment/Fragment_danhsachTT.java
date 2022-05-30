package com.example.duanmau_nguyenvanloc_ph13492.Fragment.ThuThu_Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.ThuThuDao;
import com.example.duanmau_nguyenvanloc_ph13492.Model.ThuThu;
import com.example.duanmau_nguyenvanloc_ph13492.R;

import java.util.List;


public class Fragment_danhsachTT extends Fragment {

    RecyclerView recyclerView ;
    Adapter_DsThuThu adapter_dsThuThu;
    ThuThuDao thuThuDao;
    List<ThuThu> listTT;
    EditText editText;
    Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danhsach_t_t, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_ds);
        editText = view.findViewById(R.id.ed_tim);
        button = view.findViewById(R.id.btn_tim);
        thuThuDao = new ThuThuDao(view.getContext());
        listTT = thuThuDao.getAllTT();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        adapter_dsThuThu = new Adapter_DsThuThu(view.getContext(), listTT);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_dsThuThu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Nhập để tìm kiếm", Toast.LENGTH_SHORT).show();
                    return;
                }
                adapter_dsThuThu = new Adapter_DsThuThu(view.getContext(), thuThuDao.getAlltheoTen(editText.getText().toString()));
                recyclerView.setAdapter(adapter_dsThuThu);
            }
        });
        return view;

    }

}