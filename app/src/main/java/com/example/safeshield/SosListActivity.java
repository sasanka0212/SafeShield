package com.example.safeshield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class SosListActivity extends AppCompatActivity {

    private ArrayList<Contact> list;
    private SosAdapter adapter;
    private RecyclerView sosRecycler;
    private EditText sosAutoTextView;
    private CardView sosMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_list);

        sosRecycler = findViewById(R.id.sosRecycler);
        sosAutoTextView = findViewById(R.id.sosAutoTextView);
        sosMap = findViewById(R.id.sosMap);

        sosRecycler.setLayoutManager(new LinearLayoutManager(this));
        sosRecycler.setHasFixedSize(true);

        pushData();

        getData();

        sosAutoTextView.setText(getIntent().getStringExtra("address"));

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

        sosMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SosListActivity.this, MapActivity.class);
                startActivity(intent);
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