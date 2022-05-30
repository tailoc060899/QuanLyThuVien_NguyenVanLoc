package com.example.duanmau_nguyenvanloc_ph13492.Fragment.LoaiSach_Fragment;

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

import com.example.duanmau_nguyenvanloc_ph13492.DAO.LoaiSachDao;
import com.example.duanmau_nguyenvanloc_ph13492.DAO.SachDao;
import com.example.duanmau_nguyenvanloc_ph13492.Model.TheLoai;
import com.example.duanmau_nguyenvanloc_ph13492.R;

import java.util.List;

public class Adapter_LoaiSach extends RecyclerView.Adapter<Adapter_LoaiSach.viewholder> {
    Context context;
    List<TheLoai> listTL;
    LoaiSachDao loaiSachDao;
    SachDao sachDao;

    public Adapter_LoaiSach(Context context, List<TheLoai> listTL) {
        this.context = context;
        this.listTL = listTL;
    }

    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_loaisach,parent,false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        TheLoai theLoai = listTL.get(position);
        loaiSachDao = new LoaiSachDao(context);
        sachDao = new SachDao(context);
        holder.tvten.setText("Tên loại: "+theLoai.getTenTL());
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater1 = LayoutInflater.from(context);
                View view1 = inflater1.inflate(R.layout.add_loaisach,null);
                EditText editText = view1.findViewById(R.id.ed_tenLoai);
                TextView td = view1.findViewById(R.id.tv_TD);
                builder.setView(view1);
                td.setText("Sửa loại sách");
                editText.setText(theLoai.getTenTL());
                builder.setPositiveButton("sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(editText.getText().toString().isEmpty()){
                            Toast.makeText(view1.getContext(), "tên loại sách không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        theLoai.setTenTL(editText.getText().toString());
                        boolean kq = loaiSachDao.updateLoaiSach(theLoai);
                        if(kq){
                            Toast.makeText(view1.getContext(), "sửa thành công", Toast.LENGTH_SHORT).show();
                        }
                        listTL=loaiSachDao.getAllLoaiSach();
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
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Hỏi");
                builder.setMessage("Xóa loại sách những sách mang id loại sách cũng sẽ bị xóa");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean kq = loaiSachDao.xoaLoaiSach(theLoai);
                        if(kq){
                            Toast.makeText(view.getContext(), "xóa thành công", Toast.LENGTH_SHORT).show();
                            sachDao.deleteMaLoai(theLoai.getMaTL());
                        }
                        listTL = loaiSachDao.getAllLoaiSach();
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
        return listTL.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tvten;
        ImageButton btn_edit,btn_delete;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvten = itemView.findViewById(R.id.tv_tenLoaiSach);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
