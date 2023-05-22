package com.example.safeshield;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UpadateContactActivity extends AppCompatActivity {

    TextView updateContactName, updateContactPhone, updateContactEmail;
    Button updateContactBtn;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upadate_contact);

        updateContactName = findViewById(R.id.updateContactName);
        updateContactPhone = findViewById(R.id.updateContactPhone);
        updateContactEmail = findViewById(R.id.updateContactEmail);
        updateContactBtn = findViewById(R.id.updateContactBtn);

        updateContactName.setText(getIntent().getStringExtra("name"));
        updateContactPhone.setText(getIntent().getStringExtra("phone"));
        updateContactEmail.setText(getIntent().getStringExtra("email"));
        key = getIntent().getStringExtra("phone");

        updateContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(UpadateContactActivity.this);
                dbHelper.updateContact(key,updateContactName.getText().toString(),updateContactPhone.getText().toString(),updateContactEmail.getText().toString());
                Toast.makeText(UpadateContactActivity.this, "update succesfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpadateContactActivity.this, ContactlistActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}