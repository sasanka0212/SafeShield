package com.example.safeshield;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DirectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                boolean flag = pref.getBoolean("flag", false);
                Intent intent;
                if(flag){
                    intent = new Intent(DirectionActivity.this, MainActivity.class);
                }else{
                    intent = new Intent(DirectionActivity.this, loginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        },500);
    }
}