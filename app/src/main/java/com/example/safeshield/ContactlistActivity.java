package com.example.safeshield;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
    EditText contactAutoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactlist);
        recyclerContact = findViewById(R.id.recyclerContact);
        btnDialog = findViewById(R.id.btnDialog);
        contactAutoTextView = findViewById(R.id.contactAutoTextView);

        recyclerContact.setLayoutManager(new LinearLayoutManager(this));
        recyclerContact.setHasFixedSize(true);

        contactAutoTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactlistActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });

        getContact();
    }

    private void filter(String text) {
        ArrayList<ContactFace> filterList = new ArrayList<>();
        for(ContactFace items : arrContacts){
            if(items.name.toLowerCase().contains(text.toLowerCase())){
                filterList.add(items);
            }
            adapter.filterList(filterList);
        }
    }

    private void getContact() {
        DBHelper dbHelper = new DBHelper(this);
        arrContacts = dbHelper.getContactData();
        adapter = new ContactAdapter(arrContacts, this);
        recyclerContact.setAdapter(adapter);
    }
}