package com.example.duanmau_nguyenvanloc_ph13492.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmau_nguyenvanloc_ph13492.Login.Login;
import com.example.duanmau_nguyenvanloc_ph13492.R;

public class Splash_screen extends AppCompatActivity {
    ImageView imageView;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = findViewById(R.id.img_splash);
        t1=findViewById(R.id.tv_splash);
        Animation animation1 = AnimationUtils.loadAnimation(Splash_screen.this,R.anim.anim_tv1);
        t1.startAnimation(animation1);

        Animation animation = AnimationUtils.loadAnimation(Splash_screen.this,R.anim.anim_splash);
        imageView.startAnimation(animation);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash_screen.this, Login.class));
                finish();
            }
        },3500);
    }
}