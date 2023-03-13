package com.example.safeshield;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;
public class DBHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="Login.db";
    public static final String TABLE_NAME="Users";

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

    public boolean insertData(String newuser, String phoneNo, String username, String password)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("newuser", newuser);
        values.put("phoneNo", phoneNo);
        values.put("Username",username);
        values.put("Password",password);
        long result=MyDB.insert("users",null,values);
        if(result==1)
            return true;
        else
            return false;
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
}
