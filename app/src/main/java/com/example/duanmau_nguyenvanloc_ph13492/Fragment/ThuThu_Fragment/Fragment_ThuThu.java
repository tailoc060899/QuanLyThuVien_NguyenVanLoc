package com.example.duanmau_nguyenvanloc_ph13492.Fragment.ThuThu_Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.ThuThuDao;
import com.example.duanmau_nguyenvanloc_ph13492.Model.ThuThu;
import com.example.duanmau_nguyenvanloc_ph13492.R;

import java.util.Locale;


public class Fragment_ThuThu extends Fragment {
    EditText ed_user,ed_name,ed_pass;
    Button btn_dk, btn_huy;
    ThuThuDao thuThuDao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_thu_thu, container, false);
        ed_user = view.findViewById(R.id.ed_usernew);
        ed_name = view.findViewById(R.id.ed_hotennew);
        ed_pass = view.findViewById(R.id.ed_matkhaunew);
        btn_dk = view.findViewById(R.id.btn_dangky);
        btn_huy = view.findViewById(R.id.btn_huyTT);
        thuThuDao = new ThuThuDao(getActivity());
        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = ed_user.getText().toString();
                String n = ed_name.getText().toString();
                String p = ed_pass.getText().toString();
                if(u.isEmpty()){
                    ed_user.setError("Username không dc để trống");
                    return;
                }
                if(n.isEmpty()){
                    ed_name.setError("Tên không dc để trống");
                    return;
                }
                if(p.isEmpty()){
                    ed_pass.setError("Password không dc để trống");
                    return;
                }
                if(n.length()<5){
                    ed_name.setError("Tên người dùng phải lớn hơn 5 kí tự");
                    return;
                }
                if(n.length()>10){
                    ed_name.setError("Ten người dùng phải nhỏ hơn 10 kí tự");
                    return;
                }
                if(n.matches("^[A-Z].*")==false){
                    ed_name.setError("Chữ cái đầu phải viết hoa");
                    return;
                }
                ThuThu thuThu = new ThuThu();
                    thuThu.setMaTT(u);
                    thuThu.setHoTen(n);
                    thuThu.setMatKhau(p);
                if(thuThuDao.checkUser(thuThu)==false){
                    Toast.makeText(view.getContext(), "Username đã tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }
                    if(thuThuDao.insertThuThu(thuThu)){
                        Toast.makeText(view.getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        ed_name.setText("");
                        ed_user.setText("");
                        ed_pass.setText("");
                }
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_name.setText("");
                ed_user.setText("");
                ed_pass.setText("");
            }
        });
        return view;
    }

}