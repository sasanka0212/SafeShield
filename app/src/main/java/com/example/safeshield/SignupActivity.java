package com.example.safeshield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText edtNameSignup, edtPhoneSignup, edtUserSignup, edtPassSignup;
    Button btnSignup;
    TextView txtSignupError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edtNameSignup = findViewById(R.id.edtNameSignup);
        edtPhoneSignup = findViewById(R.id.edtPhoneSignup);
        edtUserSignup = findViewById(R.id.edtUserSignup);
        edtPassSignup = findViewById(R.id.edtPassSignup);
        btnSignup = findViewById(R.id.btnSignup);
        txtSignupError = findViewById(R.id.txtSignupError);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameSignup = edtNameSignup.getText().toString();
                String phoneSignup = edtPhoneSignup.getText().toString();
                String userSignup = edtUserSignup.getText().toString();
                String passSignup = edtPassSignup.getText().toString();
                DBHelper DB = new DBHelper(SignupActivity.this);
                if(DB.insertData(nameSignup, phoneSignup, userSignup, passSignup)){
                    Toast.makeText(SignupActivity.this, "Successfully created account", Toast.LENGTH_SHORT).show();
                    Intent iNext = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(iNext);
                    finish();
                }
                else{
                    txtSignupError.setText("User already exits");
                }
            }
        });

        getSupportActionBar().hide();
    }
}