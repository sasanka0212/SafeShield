package com.example.safeshield;

import android.app.Dialog;
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

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ContactlistActivity.this);
                dialog.setContentView(R.layout.add_contact);
                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtPhone = dialog.findViewById(R.id.edtPhone);
                Button btnAction = dialog.findViewById(R.id.btnSave);
                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="", phone="";
                        if(!edtName.getText().toString().equals("") && !edtPhone.getText().toString().equals("")){
                            name = edtName.getText().toString();
                            phone = edtPhone.getText().toString();
                            arrContacts.add(new ContactFace(name, phone));
                        }
                        else{
                            Toast.makeText(ContactlistActivity.this, "Please Enter Full Details!", Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyItemInserted(arrContacts.size()-1);
                        recyclerContact.scrollToPosition(arrContacts.size()-1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        recyclerContact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        arrContacts = new ArrayList<ContactFace>();
        /*arrContacts.add(new ContactFace("Sasanka", "6291184256"));
        arrContacts.add(new ContactFace("Siddthartha", "9587218712"));
        arrContacts.add(new ContactFace("Aritra", "6291544256"));
        arrContacts.add(new ContactFace("Ankur", "8221184256"));
        arrContacts.add(new ContactFace(R.drawable.e, "Snigdha", "6291100056"));
        arrContacts.add(new ContactFace(R.drawable.f, "Raktim", "9929184256"));
        arrContacts.add(new ContactFace(R.drawable.g, "Puronjit", "8091184256"));
        arrContacts.add(new ContactFace(R.drawable.h, "Ankita", "6282184200"));
        arrContacts.add(new ContactFace(R.drawable.i, "Subhendhu", "9900184206"));
        arrContacts.add(new ContactFace(R.drawable.a, "Surajit", "6290004256"));
        arrContacts.add(new ContactFace(R.drawable.b, "Soumya", "6280124901"));
        arrContacts.add(new ContactFace(R.drawable.g, "Subhadeep", "7290084297"));
        arrContacts.add(new ContactFace(R.drawable.a, "Souvik", "6291254256"));
        arrContacts.add(new ContactFace(R.drawable.b, "Sayantan", "7001184256"));
        arrContacts.add(new ContactFace(R.drawable.d, "Dishani", "7291184256"));
        arrContacts.add(new ContactFace(R.drawable.h, "Sarnali", "8291188856"));
        arrContacts.add(new ContactFace(R.drawable.a, "Soudip", "7091034256"));*/
        adapter = new ContactAdapter(arrContacts, ContactlistActivity.this);
        recyclerContact.setAdapter(adapter);
    /*RecyclerView recyclerContact;
    Button btnDialog;
    ArrayList<ContactFace> arrContacts;
    ContactAdapter contactAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactlist);
        recyclerContact = findViewById(R.id.recyclerContact);
        btnDialog = findViewById(R.id.btnDialog);

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ContactlistActivity.this);
                dialog.setContentView(R.layout.add_contact);
                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtPhone = dialog.findViewById(R.id.edtPhone);
                Button btnSave = dialog.findViewById(R.id.btnSave);

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "", phone = "";
                        if(!edtName.getText().toString().equals("") && !edtPhone.getText().toString().equals("")){
                            name = edtName.getText().toString();
                            phone = edtPhone.getText().toString();
                            arrContacts.add(new ContactFace(name, phone));
                        }else{
                            Toast.makeText(ContactlistActivity.this, "Please enter full details...", Toast.LENGTH_SHORT).show();
                        }
                        contactAdapter.notifyItemInserted(arrContacts.size()-1);
                        recyclerContact.scrollToPosition(arrContacts.size()-1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        recyclerContact.setLayoutManager(new LinearLayoutManager(ContactlistActivity.this));
        arrContacts =  new ArrayList<>();
        contactAdapter = new ContactAdapter(arrContacts, ContactlistActivity.this);
        recyclerContact.setAdapter(contactAdapter);*/
    }
}