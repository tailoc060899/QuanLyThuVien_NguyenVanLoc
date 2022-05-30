package com.example.duanmau_nguyenvanloc_ph13492.Fragment.Pass_Fragment;

import static com.example.duanmau_nguyenvanloc_ph13492.Login.Login.KEY_USERNAME;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.ThuThuDao;
import com.example.duanmau_nguyenvanloc_ph13492.Model.ThuThu;
import com.example.duanmau_nguyenvanloc_ph13492.R;



public class Fragment_DoiPass extends Fragment {
    EditText ed_passcu,ed_pass,ed_repass;
    Button button;
    ThuThuDao thuThuDao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_doi_pass, container, false);
        ed_passcu = view.findViewById(R.id.ed_passcu);
        ed_pass = view.findViewById(R.id.ed_passmoi);
        ed_repass = view.findViewById(R.id.ed_repass);
        button = view.findViewById(R.id.btn_thaydoi);
        thuThuDao = new ThuThuDao(view.getContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pc = ed_passcu.getText().toString();
                String p = ed_pass.getText().toString();
                String rp = ed_repass.getText().toString();
                Intent intent = getActivity().getIntent();

                if(pc.isEmpty()||p.isEmpty()||rp.isEmpty()){
                    Toast.makeText(view.getContext(), "Các trường không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!p.equals(rp)){
                    Toast.makeText(view.getContext(), "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pc.equals(thuThuDao.passCu(intent.getStringExtra(KEY_USERNAME)))){
                    Toast.makeText(view.getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                    return;
                }
                String mtt = intent.getStringExtra(KEY_USERNAME);
                ThuThu thuThu = new ThuThu();
                thuThu.setMatKhau(p);
                thuThu.setMaTT(mtt);
                if(thuThuDao.updatePass(thuThu)){
                    Toast.makeText(getActivity(), "Đổi mật khẩu thành công", Toast.LENGTH_LONG).show();
                    ed_passcu.setText("");
                    ed_pass.setText("");
                    ed_repass.setText("");
                }

            }
        });
        return view;
    }
}