package com.example.safeshield;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.ArrayList;

public class SosListActivity extends AppCompatActivity {

    private ArrayList<Contact> list;
    private SosAdapter adapter;
    private RecyclerView sosRecycler;
    private EditText sosAutoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_list);

        sosRecycler = findViewById(R.id.sosRecycler);
        sosAutoTextView = findViewById(R.id.sosAutoTextView);
        sosRecycler.setLayoutManager(new LinearLayoutManager(this));
        sosRecycler.setHasFixedSize(true);

        pushData();

        getData();

        sosAutoTextView.addTextChangedListener(new TextWatcher() {
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
    }

    private void filter(String text) {
        ArrayList<Contact> filterList = new ArrayList<>();
        for(Contact items : list){
            if(items.name.toLowerCase().contains(text.toLowerCase())){
                filterList.add(items);
            }
            adapter.filterList(filterList);
        }
    }

    private void pushData() {
        DBHelper db = new DBHelper(this);
        db.insertSosData();
    }

    private void getData() {
        DBHelper db = new DBHelper(this);
        list = db.getSos();
        adapter = new SosAdapter(this, list);
        sosRecycler.setAdapter(adapter);
    }
}