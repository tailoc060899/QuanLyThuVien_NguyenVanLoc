package com.example.duanmau_nguyenvanloc_ph13492.Fragment.ThuThu_Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.ThuThuDao;
import com.example.duanmau_nguyenvanloc_ph13492.Model.ThuThu;
import com.example.duanmau_nguyenvanloc_ph13492.R;

import java.util.List;

public class Adapter_DsThuThu extends RecyclerView.Adapter<Adapter_DsThuThu.viewholder> {

    Context context;
    List<ThuThu> listTT;
    ThuThuDao thuThuDao;

    public Adapter_DsThuThu(Context context, List<ThuThu> listTT) {
        this.context = context;
        this.listTT = listTT;
    }

    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_ds_thuthu,parent,false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        ThuThu thuThu = listTT.get(position);
        thuThuDao = new ThuThuDao(context);
        if(thuThu!=null){
            if(position %2==0){
                holder.tv_name.setTextColor(Color.RED);
                holder.tv_pass.setTextColor(Color.RED);
                holder.tv_tk.setTextColor(Color.RED);
            }else {
                holder.tv_name.setTextColor(Color.GREEN);
                holder.tv_pass.setTextColor(Color.GREEN);
                holder.tv_tk.setTextColor(Color.GREEN);
            }
        }
        holder.tv_tk.setText("Username: "+thuThu.getMaTT());
        holder.tv_name.setText("Tên thủ thư: "+thuThu.getHoTen());
        holder.tv_pass.setText("Password: "+thuThu.getMatKhau());
        holder.btn_delTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Hỏi");
                builder.setMessage("Bạn có chắc muốn xóa không");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(thuThuDao.deleteThuThu(thuThu)){
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                        listTT = thuThuDao.getAllTT();
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
        return listTT.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tv_tk,tv_name,tv_pass;
        ImageButton btn_delTT;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_tk = itemView.findViewById(R.id.tv_tenTk);
            tv_name = itemView.findViewById(R.id.tv_hoten);
            tv_pass = itemView.findViewById(R.id.tv_matkhau);

            btn_delTT = itemView.findViewById(R.id.btn_delTT);
        }
    }
}
