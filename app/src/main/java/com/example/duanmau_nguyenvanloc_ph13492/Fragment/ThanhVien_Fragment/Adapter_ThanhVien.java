package com.example.duanmau_nguyenvanloc_ph13492.Fragment.ThanhVien_Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.PhieuMuonDao;
import com.example.duanmau_nguyenvanloc_ph13492.DAO.ThanhVienDao;
import com.example.duanmau_nguyenvanloc_ph13492.Model.ThanhVien;
import com.example.duanmau_nguyenvanloc_ph13492.R;

import java.util.List;

public class Adapter_ThanhVien extends RecyclerView.Adapter<Adapter_ThanhVien.viewholder> {
    Context context;
    List<ThanhVien> listTV;
    ThanhVienDao thanhVienDao;
    PhieuMuonDao phieuMuonDao;

    public Adapter_ThanhVien(Context context, List<ThanhVien> listTV) {
        this.context = context;
        this.listTV = listTV;
    }

    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_thanhvien,parent,false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        ThanhVien thanhVien = listTV.get(position);
        thanhVienDao = new ThanhVienDao(context);
        phieuMuonDao = new PhieuMuonDao(context);
        holder.tv_tenTV.setText("Tên thành viên: "+thanhVien.getHoten());
        holder.tv_ns.setText("Năm sinh: "+thanhVien.getNamSinh());
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater1 = LayoutInflater.from(context);
                View view1 = inflater1.inflate(R.layout.add_thanhvien,null);
                EditText ed_name = view1.findViewById(R.id.ed_tenTV);
                EditText ed_ns = view1.findViewById(R.id.ed_NamSinhTV);
                TextView textView = view1.findViewById(R.id.tv_TDTV);
                builder.setView(view1);
                textView.setText("Sửa thành viên");
                ed_name.setText(thanhVien.getHoten());
                ed_ns.setText(thanhVien.getNamSinh());
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(ed_name.getText().toString().isEmpty()){
                            Toast.makeText(view1.getContext(), "tên thành viên không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(ed_ns.getText().toString().isEmpty()){
                            Toast.makeText(view1.getContext(), "năm sinh không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        thanhVien.setHoten(ed_name.getText().toString());
                        thanhVien.setNamSinh(ed_ns.getText().toString());
                        if(thanhVienDao.updateTV(thanhVien)){
                            Toast.makeText(view.getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }
                        listTV = thanhVienDao.getAllTv();
                        notifyDataSetChanged();
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
        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Hỏi");
                builder.setMessage("Xóa thành viên những phiếu mượn mang id thành viên cũng bị xóa");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(thanhVienDao.deleteTV(thanhVien)){
                            phieuMuonDao.deleteIDTV(thanhVien.getMaTV());
                            Toast.makeText(view.getContext(), "xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                        listTV = thanhVienDao.getAllTv();
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTV.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tv_tenTV,tv_ns;
        ImageButton btn_edit,btn_del;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_tenTV = itemView.findViewById(R.id.tv_tenTV);
            tv_ns = itemView.findViewById(R.id.tv_namsinh);
            btn_edit = itemView.findViewById(R.id.btn_editTV);
            btn_del  = itemView.findViewById(R.id.btn_deleteTV);
        }
    }
}
