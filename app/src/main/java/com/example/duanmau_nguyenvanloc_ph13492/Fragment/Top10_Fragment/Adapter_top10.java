package com.example.duanmau_nguyenvanloc_ph13492.Fragment.Top10_Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_nguyenvanloc_ph13492.Model.Top10;
import com.example.duanmau_nguyenvanloc_ph13492.R;

import java.util.List;

public class Adapter_top10 extends RecyclerView.Adapter<Adapter_top10.viewholder> {
    Context context;
    List<Top10> listTOP;

    public Adapter_top10(Context context, List<Top10> listTOP) {
        this.context = context;
        this.listTOP = listTOP;
    }

    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_top10,parent,false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Top10 top10 = listTOP.get(position);
        holder.tv_sachtop.setText("Tên sách: "+top10.getTen_sach());
        holder.tv_soluong.setText("Số lượng: "+top10.getSoLuong());
    }

    @Override
    public int getItemCount() {
        return listTOP.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tv_sachtop,tv_soluong;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_sachtop = itemView.findViewById(R.id.tv_sachTop);
            tv_soluong = itemView.findViewById(R.id.tv_soluong);
        }
    }
}
