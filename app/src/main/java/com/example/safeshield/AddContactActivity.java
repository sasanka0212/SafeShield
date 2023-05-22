package com.example.safeshield;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    EditText addName, addPhone, addEmail;
    Button addContactBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        addName = findViewById(R.id.addName);
        addPhone = findViewById(R.id.addPhone);
        addEmail = findViewById(R.id.addEmail);
        addContactBtn = findViewById(R.id.addContactBtn);

        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addName.getText().toString();
                String phone = addPhone.getText().toString();
                String email = addEmail.getText().toString();
                if(!name.equals("") && !phone.equals("")){
                    DBHelper dbHelper = new DBHelper(AddContactActivity.this);
                    if(dbHelper.insertContactData(name, phone, email)==true) {
                        Toast.makeText(AddContactActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(AddContactActivity.this, ContactlistActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }
}