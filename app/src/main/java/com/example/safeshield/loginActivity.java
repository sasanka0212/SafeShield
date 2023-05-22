package com.example.safeshield;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    EditText edtName, edtPassword;
    Button btnLogin;
    DBHelper DB;
    TextView txtError, txtgotoSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtError = findViewById(R.id.txtError);
        txtgotoSignup = findViewById(R.id.txtgotoSignup);

        //direct entry to MainActivity class

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String pass = edtPassword.getText().toString();
                DB = new DBHelper(loginActivity.this);
                SharedPreferences pref;
                if(DB.checkusernamepassword(name,pass)) {
                    Toast.makeText(loginActivity.this, "Welcome back!", Toast.LENGTH_SHORT).show();
                    pref = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("flag", true);
                    editor.apply();
                    Intent intentDirection = new Intent(loginActivity.this, DirectionActivity.class);
                    startActivity(intentDirection);
                    finish();
                }
                else{
                    txtError.setText("Invalid username or password!");
                }
            }
        });

        txtgotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNext = new Intent(loginActivity.this, SignupActivity.class);
                startActivity(iNext);
            }
        });
        //getSupportActionBar().hide();
    }
}