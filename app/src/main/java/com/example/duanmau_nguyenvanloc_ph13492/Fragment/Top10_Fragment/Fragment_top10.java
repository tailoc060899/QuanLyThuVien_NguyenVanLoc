package com.example.duanmau_nguyenvanloc_ph13492.Fragment.Top10_Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.PhieuMuonDao;
import com.example.duanmau_nguyenvanloc_ph13492.Model.Top10;
import com.example.duanmau_nguyenvanloc_ph13492.R;

import java.util.List;


public class Fragment_top10 extends Fragment {
    RecyclerView recyclerView;
    List<Top10> listTOP;
    Adapter_top10 adapter_top10;
    PhieuMuonDao phieuMuonDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_top10, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_top10);
        phieuMuonDao = new PhieuMuonDao(view.getContext());
        listTOP = phieuMuonDao.top10();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter_top10 = new Adapter_top10(view.getContext(),listTOP);
        recyclerView.setAdapter(adapter_top10);
        return view;
    }
}