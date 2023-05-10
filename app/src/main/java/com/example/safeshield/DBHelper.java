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
        MyDB.execSQL("create table "+TABLE_NAME+"(newuser Text, phoneNo Text, Username Text, Password password Not null, primary key(Username, phoneNo))");
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
        long result=MyDB.insert("users",null,values);
        if(result==1){
            this.username = username;
            this.password = password;
            return true;
        }
        else
            return false;
    }
    public ArrayList<User> getUser(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor rs = MyDB.rawQuery("select * from users", null);
        rs.moveToFirst();
        ArrayList<User> arrList = new ArrayList<>();
        String name = rs.getString(0);
        String phone = rs.getString(1);
        String id = rs.getString(2);
        String pass = rs.getString(3);
        arrList.add(new User(name, phone, id, pass));
        return arrList;
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

    class User{
        String name, phone, id, pass;

        public User(String name, String phone, String id, String pass) {
            this.name = name;
            this.phone = phone;
            this.id = id;
            this.pass = pass;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getId() {
            return id;
        }

        public String getPass() {
            return pass;
        }
    }
}
