package com.example.safeshield;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;

public class DevInfoActivity extends AppCompatActivity implements View.OnClickListener{

    CardView sasankaFb, sasankaGit, sasankaIn;
    CardView snigdhaFb, snigdhaGit, snigdhaIn;
    CardView raktimFb, raktimGit, raktimIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_info);

        sasankaFb = findViewById(R.id.sasankaFb);
        sasankaGit = findViewById(R.id.sasankaGit);
        sasankaIn = findViewById(R.id.sasankaIn);
        snigdhaFb = findViewById(R.id.snigdhaFb);
        snigdhaGit = findViewById(R.id.snigdhaGit);
        snigdhaIn = findViewById(R.id.snigdhaIn);
        raktimFb = findViewById(R.id.raktimFb);
        raktimGit = findViewById(R.id.raktimGit);
        raktimIn = findViewById(R.id.raktimIn);

        sasankaFb.setOnClickListener(this);
        sasankaGit.setOnClickListener(this);
        sasankaIn.setOnClickListener(this);
        snigdhaFb.setOnClickListener(this);
        snigdhaGit.setOnClickListener(this);
        snigdhaIn.setOnClickListener(this);
        raktimFb.setOnClickListener(this);
        raktimGit.setOnClickListener(this);
        raktimIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){
            case R.id.sasankaFb:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/sasanka.kundu.02/"));
                startActivity(intent);
                break;
            case R.id.sasankaGit:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/sasanka0212"));
                startActivity(intent);
                break;
            case R.id.sasankaIn:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/in/sasanka-kundu-03b159210/"));
                startActivity(intent);
                break;
            case R.id.snigdhaFb:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/snigdha.chatterjee.9250?mibextid=ZbWKwL"));
                startActivity(intent);
                break;
            case R.id.snigdhaGit:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/SnigdhaChatterjee"));
                startActivity(intent);
                break;
            case R.id.snigdhaIn:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/in/snigdha-chatterjee-b25329244"));
                startActivity(intent);
                break;
            case R.id.raktimFb:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/raktim.sur.14?mibextid=ZbWKwL"));
                startActivity(intent);
                break;
            case R.id.raktimGit:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/Raktim-GH"));
                startActivity(intent);
                break;
            case R.id.raktimIn:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/in/raktim-sur-4546a7259"));
                startActivity(intent);
                break;
        }
    }
}