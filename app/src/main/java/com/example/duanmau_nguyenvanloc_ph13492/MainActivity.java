package com.example.duanmau_nguyenvanloc_ph13492;

import static com.example.duanmau_nguyenvanloc_ph13492.Login.Login.KEY_USERNAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau_nguyenvanloc_ph13492.DAO.ThuThuDao;
import com.example.duanmau_nguyenvanloc_ph13492.Fragment.DoanhThu_Fragment.Fragment_DoanhThu;
import com.example.duanmau_nguyenvanloc_ph13492.Fragment.LoaiSach_Fragment.Fragment_LoaiSach;
import com.example.duanmau_nguyenvanloc_ph13492.Fragment.Pass_Fragment.Fragment_DoiPass;
import com.example.duanmau_nguyenvanloc_ph13492.Fragment.PhieuMuon_Fragment.Fragment_PhieuMuon;
import com.example.duanmau_nguyenvanloc_ph13492.Fragment.Sach_Fragment.Fragment_Sach;
import com.example.duanmau_nguyenvanloc_ph13492.Fragment.ThanhVien_Fragment.Fragment_ThanhVien;
import com.example.duanmau_nguyenvanloc_ph13492.Fragment.ThuThu_Fragment.Fragment_ThuThu;
import com.example.duanmau_nguyenvanloc_ph13492.Fragment.ThuThu_Fragment.Mycolection_Fragment_thuthu;
import com.example.duanmau_nguyenvanloc_ph13492.Fragment.Top10_Fragment.Fragment_top10;
import com.example.duanmau_nguyenvanloc_ph13492.Login.Login;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView img_menu;
    FrameLayout frameLayout;
    FragmentManager fm;
    Fragment_Sach fragment_sach;
    Fragment_LoaiSach fragment_loaiSach;
    Fragment_ThanhVien fragment_thanhVien;
    Fragment_PhieuMuon fragment_phieuMuon;
    Fragment_top10 fragment_top10;
    Fragment_DoanhThu fragment_doanhThu;
    Mycolection_Fragment_thuthu fragment_thuThu;
    Fragment_DoiPass fragment_doiPass;
    TextView tv_td,tv_nd;
    ThuThuDao thuThuDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        setSupportActionBar(toolbar);
        ActionBar actionBar =  getSupportActionBar();
        actionBar.setTitle("");
        View header = navigationView.getHeaderView(0);
        Intent intent = getIntent();
        tv_nd = header.findViewById(R.id.tv_tenNd);
        tv_nd.setText(thuThuDao.TenTT(intent.getStringExtra(KEY_USERNAME)));
        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        fm.beginTransaction().add(R.id.frame_layout,fragment_phieuMuon).commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_phieumuon:
                        fm.beginTransaction().replace(R.id.frame_layout,fragment_phieuMuon).commit();
                        tv_td.setText("Quản lý phiếu mượn");
                        break;
                    case R.id.nav_loaisach:
                        fm.beginTransaction().replace(R.id.frame_layout,fragment_loaiSach).commit();
                        tv_td.setText("Quản lý loại sách");
                        break;
                    case R.id.nav_sach:
                        fm.beginTransaction().replace(R.id.frame_layout,fragment_sach).commit();
                        tv_td.setText("Quản lý sách");
                        break;
                    case R.id.nav_thanhvien:
                        fm.beginTransaction().replace(R.id.frame_layout,fragment_thanhVien).commit();
                        tv_td.setText("Quản lý thành viên");
                        break;
                    case R.id.nav_top10:
                        fm.beginTransaction().replace(R.id.frame_layout,fragment_top10).commit();
                        tv_td.setText("Top 10 sách bán chạy");
                        break;
                    case R.id.nav_doanhthu:
                        fm.beginTransaction().replace(R.id.frame_layout,fragment_doanhThu).commit();
                        tv_td.setText("Thống kê doanh thu");
                        break;
                    case R.id.nav_addTK:
                        if(!intent.getStringExtra(KEY_USERNAME).equals("admin")){
                            drawerLayout.closeDrawer(navigationView);
                            Toast.makeText(MainActivity.this, "Bạn không phải admin", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        fm.beginTransaction().replace(R.id.frame_layout,fragment_thuThu).commit();
                        tv_td.setText("Quản lý thủ thư");
                        break;
                    case R.id.nav_doiPass:
                        fm.beginTransaction().replace(R.id.frame_layout,fragment_doiPass).commit();
                        tv_td.setText("Đổi mật khẩu");
                        break;
                    case R.id.nav_thoat:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Hỏi");
                        builder.setMessage("Bạn có muốn đăng xuất không");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                              startActivity(new Intent(MainActivity.this,Login.class));
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
    }

    public void anhxa(){
        toolbar = findViewById(R.id.toolbal);
        drawerLayout = findViewById(R.id.dra_layout);
        navigationView = findViewById(R.id.nav_navigation);
        img_menu = findViewById(R.id.img_menu);
        frameLayout = findViewById(R.id.frame_layout);
        fragment_sach = new Fragment_Sach();
        fragment_loaiSach = new Fragment_LoaiSach();
        fragment_thanhVien = new Fragment_ThanhVien();
        fragment_phieuMuon = new Fragment_PhieuMuon();
        fragment_top10 = new Fragment_top10();
        fragment_doanhThu = new Fragment_DoanhThu();
        fragment_thuThu = new Mycolection_Fragment_thuthu();
        fragment_doiPass = new Fragment_DoiPass();
        fm = getSupportFragmentManager();
        tv_td = findViewById(R.id.tv_tieude);
        thuThuDao = new ThuThuDao(MainActivity.this);
    }
}