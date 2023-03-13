package com.example.safeshield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String pass = edtPassword.getText().toString();
                DB = new DBHelper(loginActivity.this);
                if(DB.checkusernamepassword(name,pass)) {
                    Toast.makeText(loginActivity.this, "Welcome back!", Toast.LENGTH_SHORT).show();
                    Intent intentMain = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(intentMain);
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
        getSupportActionBar().hide();
    }
}