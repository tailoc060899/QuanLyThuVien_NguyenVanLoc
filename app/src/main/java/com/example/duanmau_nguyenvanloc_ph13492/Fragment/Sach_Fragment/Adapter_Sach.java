package com.example.duanmau_nguyenvanloc_ph13492.Fragment.Sach_Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.LoaiSachDao;
import com.example.duanmau_nguyenvanloc_ph13492.DAO.PhieuMuonDao;
import com.example.duanmau_nguyenvanloc_ph13492.DAO.SachDao;
import com.example.duanmau_nguyenvanloc_ph13492.Model.Sach;
import com.example.duanmau_nguyenvanloc_ph13492.R;

import java.lang.reflect.Type;
import java.util.List;

public class Adapter_Sach extends RecyclerView.Adapter<Adapter_Sach.viewholder> {
    List<Sach> listSach;
    Context context;
    SachDao sachDao;
    PhieuMuonDao phieuMuonDao;
    LoaiSachDao loaiSachDao;

    public Adapter_Sach(List<Sach> listSach, Context context) {
        this.listSach = listSach;
        this.context = context;
    }

    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_sach,parent,false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        sachDao = new SachDao(context);
        phieuMuonDao = new PhieuMuonDao(context);
        loaiSachDao = new LoaiSachDao(context);
        Sach sach = listSach.get(position);
        holder.tv_ml.setText("Tên loại sách: "+loaiSachDao.GetTenLoai(sach.getMaLoai()));
        holder.tv_tensach.setText("Tên sách: "+sach.getTenSach());
        holder.tv_gia.setText("giá thuê: "+sach.getGiaThue());
        holder.tv_km.setText("Giá km: "+sach.getGiaKM());
        holder.tv_sl.setText("Số lượng: "+sach.getSluong());
        if(sach.getSluong()%5==0){
            holder.tv_sl.setTypeface(null,Typeface.BOLD);
        }
        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Hỏi");
                builder.setMessage("xóa sách những phiếu mượn mang id loại sách cũng bị xóa");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean kq = sachDao.deleteSach(sach);
                        if(kq){
                            phieuMuonDao.deleteIDSach(sach.getMaSach());
                            Toast.makeText(view.getContext(), "xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                        listSach = sachDao.getAllSach();
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
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSachDao loaiSachDao = new LoaiSachDao(context);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater1 = LayoutInflater.from(context);
                View view1 = inflater1.inflate(R.layout.add_sach,null);
                Spinner spinner = view1.findViewById(R.id.sp_maloai);
                TextView td = view1.findViewById(R.id.tv_TDS);
                EditText ed_tenSach = view1.findViewById(R.id.ed_tensach);
                EditText ed_giathue = view1.findViewById(R.id.ed_giathue);
                EditText ed_km = view1.findViewById(R.id.ed_giakm);
                EditText ed_sl = view1.findViewById(R.id.ed_sl);
                builder.setView(view1);
                td.setText("Sửa thông tin sách");
                ed_tenSach.setText(sach.getTenSach());
                ed_giathue.setText(sach.getGiaThue()+"");
                ed_km.setText(sach.getGiaKM()+"");
                ed_sl.setText(sach.getSluong()+"");

                List<String>  spinnerLS= loaiSachDao.allID();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(view1.getContext(), android.R.layout.simple_spinner_item,spinnerLS);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setSelection(adapter.getPosition(loaiSachDao.GetTenLoai(sach.getMaLoai())));
                builder.setPositiveButton("sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(ed_tenSach.getText().toString().isEmpty()){
                            Toast.makeText(view1.getContext(), "tên sách không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(ed_giathue.getText().toString().isEmpty()){
                            Toast.makeText(view1.getContext(), "giá thuê sách không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(ed_km.getText().toString().isEmpty()){
                            Toast.makeText(view1.getContext(), "giá km không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        sach.setMaLoai(loaiSachDao.GetID(spinner.getSelectedItem().toString()));
                        sach.setTenSach(ed_tenSach.getText().toString());
                        sach.setGiaThue(Integer.parseInt(ed_giathue.getText().toString()));
                        sach.setGiaKM(Integer.parseInt(ed_km.getText().toString()));
                        sach.setSluong(Integer.parseInt(ed_sl.getText().toString()));
                        boolean kq = sachDao.editSach(sach);
                        if(kq){
                            Toast.makeText(view.getContext(), "sửa thành công", Toast.LENGTH_SHORT).show();
                        }
                        listSach = sachDao.getAllSach();
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
    }

    @Override
    public int getItemCount() {
        return listSach.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tv_ml,tv_tensach,tv_gia, tv_km, tv_sl;
        ImageButton btn_edit, btn_del;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_ml = itemView.findViewById(R.id.tv_maloai);
            tv_tensach = itemView.findViewById(R.id.tv_tenSach);
            tv_gia = itemView.findViewById(R.id.tv_giathue);
            btn_edit = itemView.findViewById(R.id.btn_edit_sach);
            btn_del = itemView.findViewById(R.id.btn_delete_sach);
            tv_km = itemView.findViewById(R.id.tv_giakm);
            tv_sl = itemView.findViewById(R.id.tv_sl);
        }
    }
}
