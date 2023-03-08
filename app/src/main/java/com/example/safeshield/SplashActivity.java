package com.example.safeshield;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        logo = findViewById(R.id.logo);
        Animation scale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        logo.startAnimation(scale);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iNext = new Intent(SplashActivity.this, loginActivity.class);
                startActivity(iNext);
                finish();
            }
        },2500);
    }
}