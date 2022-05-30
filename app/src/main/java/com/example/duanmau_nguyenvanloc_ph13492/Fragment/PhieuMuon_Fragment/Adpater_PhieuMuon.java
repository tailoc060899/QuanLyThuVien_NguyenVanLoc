package com.example.duanmau_nguyenvanloc_ph13492.Fragment.PhieuMuon_Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.PhieuMuonDao;
import com.example.duanmau_nguyenvanloc_ph13492.DAO.SachDao;
import com.example.duanmau_nguyenvanloc_ph13492.DAO.ThanhVienDao;
import com.example.duanmau_nguyenvanloc_ph13492.Model.PhieuMuon;
import com.example.duanmau_nguyenvanloc_ph13492.R;

import java.util.Calendar;
import java.util.List;

public class Adpater_PhieuMuon extends RecyclerView.Adapter<Adpater_PhieuMuon.viewholder> {
    Context context;
    List<PhieuMuon> listPM;
    PhieuMuonDao phieuMuonDao;
    SachDao sachDao;
    ThanhVienDao thanhVienDao;
    Spinner sp_maTV,sp_masach;
    CheckBox ck_trasach;
    EditText ed_ngaymuon;
    ArrayAdapter<String> adapterTv;
    ArrayAdapter<String> adapterMS;


    public Adpater_PhieuMuon(Context context, List<PhieuMuon> listPM) {
        this.context = context;
        this.listPM = listPM;
    }

    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_phieumuon,parent,false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        phieuMuonDao = new PhieuMuonDao(context);
        sachDao = new SachDao(context);
        thanhVienDao = new ThanhVienDao(context);
        PhieuMuon phieuMuon = listPM.get(position);
        holder.tv_Tv.setText("Tên thành viên: "+thanhVienDao.TenTV(phieuMuon.getMaTv()+""));
        holder.tv_maTT.setText("Mã thủ thư: "+phieuMuon.getMaTT());
        holder.tv_tenSach.setText("tên sách: "+sachDao.tenSach(phieuMuon.getMaSach()+""));
        holder.tv_tienThue.setText("tiền thuê: "+phieuMuon.getTienThue());
        holder.tv_ngaymuon.setText("ngày mượn: "+phieuMuon.getNgayMuon());
        holder.tv_traSach.setText(phieuMuon.getTraSach()==0?"chưa trả sách":"Đã trả sách");
        holder.tv_traSach.setTextColor(phieuMuon.getTraSach()==0?Color.RED:Color.BLUE);


        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Hỏi");
                builder.setMessage("Bạn có chắc muốn xóa không");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(phieuMuonDao.deletePhieuMuon(phieuMuon)){
                            Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                        listPM = phieuMuonDao.getAllPM();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater1 = LayoutInflater.from(context);
                View view1 = inflater1.inflate(R.layout.add_phieumuon,null);
                sp_maTV  = view1.findViewById(R.id.sp_matv);
                sp_masach = view1.findViewById(R.id.sp_masach);
                ck_trasach = view1.findViewById(R.id.ck_trasach);
                ed_ngaymuon = view1.findViewById(R.id.ed_ngaymuon);
                TextView tv_TD = view1.findViewById(R.id.tv_TDPM);
                ImageView img_date = view1.findViewById(R.id.img_dateNgayMuon);
                builder.setView(view1);
                tv_TD.setText("sửa phiếu mượn");
                ed_ngaymuon.setText(phieuMuon.getNgayMuon());
                if(phieuMuon.getTraSach()==1){
                    ck_trasach.setChecked(true);
                }
                sp_mtv();
                sp_maTV.setSelection(adapterTv.getPosition(thanhVienDao.GetTenTV(phieuMuon.getMaTv())));
                sp_masach();
                sp_masach.setSelection(adapterMS.getPosition(sachDao.tenSach(String.valueOf(phieuMuon.getMaSach()))));
                img_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDatePicker();
                    }
                });
                builder.setPositiveButton("sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(ed_ngaymuon.getText().toString().isEmpty()){
                            Toast.makeText(view1.getContext(), "Ngày mượn không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        thanhVienDao = new ThanhVienDao(context);
                        sachDao = new SachDao(context);
                        phieuMuon.setMaSach(sachDao.GetID(sp_masach.getSelectedItem().toString()));
                        phieuMuon.setMaTv(thanhVienDao.GetID(sp_maTV.getSelectedItem().toString()));
                        phieuMuon.setNgayMuon(ed_ngaymuon.getText().toString());
                        phieuMuon.setTraSach(ck_trasach.isChecked()?1:0);
                        int tien = sachDao.giaThue(sp_masach.getSelectedItem().toString());
                        phieuMuon.setTienThue(tien);
                        if(phieuMuonDao.updatePhieuMuon(phieuMuon)){
                            Toast.makeText(context, "sửa thành công", Toast.LENGTH_SHORT).show();
                        }
                        listPM = phieuMuonDao.getAllPM();
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
        return listPM.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tv_maTT,tv_Tv,tv_tenSach,tv_tienThue,tv_ngaymuon,tv_traSach;
        ImageButton btn_edit,btn_del;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_maTT= itemView.findViewById(R.id.tv_mathuthu);
            tv_Tv = itemView.findViewById(R.id.tv_tenTVPM);
            tv_tenSach = itemView.findViewById(R.id.tv_tenSachPm);
            tv_tienThue = itemView.findViewById(R.id.tv_tienThue);
            tv_ngaymuon = itemView.findViewById(R.id.tv_ngaythue);
            tv_traSach = itemView.findViewById(R.id.tv_Trasach);
            btn_edit = itemView.findViewById(R.id.btn_editPM);
            btn_del = itemView.findViewById(R.id.btn_deletePM);
        }
    }
    public void sp_mtv(){
        thanhVienDao = new ThanhVienDao(context);
        List<String> list = thanhVienDao.getIdTv();
        adapterTv = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,list);
        adapterTv.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_maTV.setAdapter(adapterTv);

    }
    public void sp_masach(){
        sachDao = new SachDao(context);
        List<String> list = sachDao.getAllId();
        adapterMS = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,list);
        adapterMS.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_masach.setAdapter(adapterMS);
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
