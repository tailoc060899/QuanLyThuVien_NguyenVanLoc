package com.example.duanmau_nguyenvanloc_ph13492.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.ThuThuDao;
import com.example.duanmau_nguyenvanloc_ph13492.MainActivity;
import com.example.duanmau_nguyenvanloc_ph13492.Model.ThuThu;
import com.example.duanmau_nguyenvanloc_ph13492.R;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    Button btn_login, btn_huy;
    EditText ed_user, ed_pass;
    CheckBox ck_check;
    ThuThuDao db;
    SharedPreferences preferences;
    ThuThu thuThu;
    public static String KEY_USERNAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new ThuThuDao(this);
        btn_login = findViewById(R.id.btn_login);
        ed_user = findViewById(R.id.ed_user);
        ed_pass = findViewById(R.id.ed_pass);
        ck_check = findViewById(R.id.ck_check);
        thuThu = new ThuThu();
        Intent i = getIntent();
        ed_user.setText(i.getStringExtra(KEY_USERNAME));
        preferences = getSharedPreferences("USER_FILE.txt",MODE_PRIVATE);
        ed_user.setText(preferences.getString("USERNAME",""));
        ed_pass.setText(preferences.getString("PASSWORD",""));
        ck_check.setChecked(preferences.getBoolean("REMEMBER",false));
    }
    public void luuThongTin(String user, String pass, boolean check){
        SharedPreferences.Editor editor = preferences.edit();
        if(!check){
            editor.clear();
        }else {
            editor.putString("USERNAME",user);
            editor.putString("PASSWORD",pass);
            editor.putBoolean("REMEMBER",check);
        }
        editor.commit();
    }

    public void login(View view) {
        String u = ed_user.getText().toString();
        String p = ed_pass.getText().toString();

        if(db.getAllThuThu().isEmpty()){
            db.insetAdmin();
        }

        if(u.isEmpty()||p.isEmpty()){
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đc để trống", Toast.LENGTH_SHORT).show();
            return;
        } else {

            boolean login = db.checkThanhCong(u,p);
            if(login==true){
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra(KEY_USERNAME, u);
                startActivity(intent);
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                luuThongTin(u,p,ck_check.isChecked());
            }else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
}