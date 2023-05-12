package com.example.safeshield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAccountActivity extends AppCompatActivity {

    EditText editName, editPhone, editUserid, editEmail,editPassword, editAddress;
    Button editUpdateBtn;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        editUserid = findViewById(R.id.editUserid);
        editPassword = findViewById(R.id.editPassword);
        editEmail = findViewById(R.id.editEmail);
        editAddress = findViewById(R.id.editAddress);
        editUpdateBtn = findViewById(R.id.editUpdateBtn);

        editName.setText(getIntent().getStringExtra("name"));
        editPhone.setText(getIntent().getStringExtra("phone"));
        editUserid.setText(getIntent().getStringExtra("id"));
        editEmail.setText(getIntent().getStringExtra("email"));
        editPassword.setText(getIntent().getStringExtra("pass"));
        editAddress.setText(getIntent().getStringExtra("address"));
        user = getIntent().getStringExtra("id");

        editUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(UpdateAccountActivity.this);
                db.updateUser(user, editName.getText().toString(), editPhone.getText().toString(), editUserid.getText().toString()
                        , editPassword.getText().toString(), editEmail.getText().toString(), editAddress.getText().toString());
                Toast.makeText(UpdateAccountActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}