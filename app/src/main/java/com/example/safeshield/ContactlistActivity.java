package com.example.safeshield;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactlistActivity extends AppCompatActivity {
    RecyclerView recyclerContact;
    ArrayList<ContactFace> arrContacts;
    ContactAdapter adapter;
    FloatingActionButton btnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactlist);
        recyclerContact = findViewById(R.id.recyclerContact);
        btnDialog = findViewById(R.id.btnDialog);

        recyclerContact.setLayoutManager(new LinearLayoutManager(this));
        recyclerContact.setHasFixedSize(true);

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactlistActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });

        getContact();
    }

    private void getContact() {
        DBHelper dbHelper = new DBHelper(this);
        arrContacts = dbHelper.getContactData();
        adapter = new ContactAdapter(arrContacts, this);
        recyclerContact.setAdapter(adapter);
    }
}