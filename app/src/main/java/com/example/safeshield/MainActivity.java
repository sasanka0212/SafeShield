package com.example.safeshield;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SafeShield");

        bottomNavigation.setSelectedItemId(R.id.bottom_emergencysos);
        setFragment(new EmegencySOSFragment(), 0);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                switch(itemId){
                    case R.id.bottom_emergencysos:
                        setFragment(new EmegencySOSFragment(), 1);
                        break;
                    case R.id.bottom_sms:
                        setFragment(new SmsFragment(), 1);
                        break;
                    case R.id.bottom_hospital:
                        setFragment(new HospitalFragment(), 1);
                        break;
                    case R.id.bottom_contactus:
                        setFragment(new ContactusFragment(), 1);
                        break;
                    case R.id.bottom_user:
                        setFragment(new AccountFragment(), 1);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(MainActivity.this).inflate(R.menu.toolbar_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId==R.id.tool_settings){
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        else if(itemId==R.id.tool_appinfo){
            Intent appInfo = new Intent(this, AppInfoActivity.class);
            startActivity(appInfo);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setFragment(Fragment fragment, int flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(flag==0) {
            ft.add(R.id.container, fragment);
        }
        else{
            ft.replace(R.id.container, fragment);
        }
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if(bottomNavigation.getSelectedItemId()!=R.id.bottom_emergencysos){
            bottomNavigation.setSelectedItemId(R.id.bottom_emergencysos);
            setFragment(new EmegencySOSFragment(), 1);
        }
        else {
            super.onBackPressed();
        }
    }
}