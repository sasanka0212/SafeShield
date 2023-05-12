package com.example.safeshield;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="Login.db";
    public static final String TABLE_NAME="Users";
    public String username, password;

    public DBHelper(Context context) {
        super(context,DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table "+TABLE_NAME+"(newuser Text, phoneNo Text, Username Text, Password password Not null, Email Text Unique, Address Text not null, primary key(Username, phoneNo))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists users");

    }

    public boolean insertUser(String newuser, String phoneNo, String username, String password)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("newuser", newuser);
        values.put("phoneNo", phoneNo);
        values.put("Username",username);
        values.put("Password",password);
        values.put("Email", "None");
        values.put("Address", "none");
        long result=MyDB.insert("users",null,values);
        if(result==1){
            this.username = username;
            this.password = password;
            return true;
        }
        else
            return false;
    }
    public User getUser(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor rs = MyDB.rawQuery("select * from users", null);
        rs.moveToNext();
        User user = new User(rs.getString(0),rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        return user;
    }

    public void updateUser(String key, String name, String phone, String user, String pass, String email, String address){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", name);
        values.put("phoneNo", phone);
        values.put("Username", user);
        values.put("Password", pass);
        values.put("Email", email);
        values.put("Address", address);
        MyDB.update(TABLE_NAME, values, "Username=?", new String[]{key});
        MyDB.close();
    }

    public boolean checkusername(String username)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();

        Cursor cursor=MyDB.rawQuery("Select * from users where username=?",new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkusernamepassword(String username,String password)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from users where username = ? and password = ?",new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean runUserQuery(){
        String query = "select * from users where username = ? and password = ?";
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery(query, new String[]{this.username, this.password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
