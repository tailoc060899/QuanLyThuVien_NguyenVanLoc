package com.example.duanmau_nguyenvanloc_ph13492.Fragment.ThuThu_Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_nguyenvanloc_ph13492.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class Mycolection_Fragment_thuthu extends Fragment {
    BottomNavigationView navigationView;
    FragmentManager fm;
    Fragment_danhsachTT fragment_danhsachTT;
    Fragment_ThuThu fragment_thuThu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mycolection_thuthu, container, false);
        navigationView = view.findViewById(R.id.botom_nav);
        fm = getActivity().getSupportFragmentManager();
        fragment_danhsachTT = new Fragment_danhsachTT();
        fragment_thuThu = new Fragment_ThuThu();
        fm.beginTransaction().add(R.id.fragment,fragment_danhsachTT).commit();
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.botnav_ds:
                        fm.beginTransaction().replace(R.id.fragment,fragment_danhsachTT).commit();
                        break;
                    default:
                        fm.beginTransaction().replace(R.id.fragment,fragment_thuThu).commit();
                        break;
                }
                return true;
            }
        });
        return view;
    }

}