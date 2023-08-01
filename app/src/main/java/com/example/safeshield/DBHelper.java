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
    public static final String TABLE2_NAME="SOS";
    public static final String TABLE3_NAME="Contacts";
    public static final String TABLE4_NAME="HospitalContact";
    public String username, password;
    public static ArrayList<Contact> eSos;
    public static ArrayList<Hcontact> Hf;

    public ArrayList<ContactFace> cf;

    public DBHelper(Context context) {
        super(context,DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table "+TABLE_NAME+"(newuser Text, phoneNo Text, Username Text, Password password Not null, Email Text Unique, Address Text not null, primary key(Username, phoneNo))");
        MyDB.execSQL("create table "+TABLE2_NAME+"( policeStation Text ,District Text, PhoneNo Text Not null,primary key(District,policeStation))");
        MyDB.execSQL("Create table "+TABLE4_NAME+"(hospitalName Text,Place Text Not null,District Text Not null,contactNo Text,primary key(hospitalName,contactNo))");
        MyDB.execSQL("create table "+TABLE3_NAME+"(name Text not null, phoneNo Text primary key, email text)");
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

    public boolean insertContactData(String name, String phoneNo, String email){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name", name);
        values.put("phoneNo", phoneNo);
        values.put("email", email);
        long result=MyDB.insert("Contacts",null,values);
        if(result!=0)
            return true;
        else
            return false;
    }

    public ArrayList<ContactFace> getContactData(){
        cf = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from Contacts", null);
        while(rs.moveToNext()){
            cf.add(new ContactFace(rs.getString(0), rs.getString(1), rs.getString(2)));
        }
        return cf;
    }

    public void updateContact(String key, String name, String phone, String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phoneNo", phone);
        values.put("email", email);
        MyDB.update(TABLE3_NAME, values, "phoneNo=?", new String[]{key});
        MyDB.close();
    }

    public void deleteContact(String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE3_NAME, "phoneNo=?", new String[]{phone});
        db.close();
    }

    public void insertSosData()
    {
        eSos=new ArrayList<Contact>();
        eSos.add(new Contact("Alipurduar","Alipurduar","03564-255100"));
        eSos.add(new Contact("Falakata","Alipurduar","03563-260242"));
        eSos.add(new Contact("Bankura","Bankura","03242-250675"));
        eSos.add(new Contact("Barjora","Bankura","8001306306"));
        eSos.add(new Contact("Bishnupur","Bankura","03244-252024"));
        eSos.add(new Contact("Bolpur","Birbhum","03463-252228"));
        eSos.add(new Contact("Nalhati","Birbhum","03465-255247"));
        eSos.add(new Contact("Rampurhut","Birbhum","03461-255100"));
        eSos.add(new Contact("Sainthia","Birbhum","03462-262232"));
        eSos.add(new Contact("Tarapith","Birbhum","03461-253277"));
        eSos.add(new Contact("Asansol(S)","Bardhaman","0341-2302225"));
        eSos.add(new Contact("Asansol(N)","Bardhaman","0341-2270149"));
        eSos.add(new Contact("Chittaranjan","Bardhaman","0341-2525368"));
        eSos.add(new Contact("Durgapur","Bardhaman","0343-2564081"));
        eSos.add(new Contact("Raniganj","Bardhaman","0341-2444230"));
        eSos.add(new Contact("Katwa","Bardhaman","03453-255023"));
        eSos.add(new Contact("Kulti","Bardhaman","0341-2515555"));
        eSos.add(new Contact("Buxirhat","Coochbehar","03582-263630"));
        eSos.add(new Contact("Kuchlibari","Coochbehar","03584-251223"));
        eSos.add(new Contact("Balurghat","Dakshin Dinajpur","03522-255651"));
        eSos.add(new Contact("Kushmandi","Dakshin Dinajpur","03524-263452"));
        eSos.add(new Contact("Bagdora","Darjeeling and Kalimpong","0353-2551242"));
        eSos.add(new Contact("Kalimpong","Darjeeling and Kalimpong","03552-255268"));
        eSos.add(new Contact("Naxalbari","Darjeeling and Kalimpong","0353-2488615"));
        eSos.add(new Contact("Siliguri","Darjeeling and Kalimpong","0353-2662101"));
        eSos.add(new Contact("Bhadreswar","Hoogly","2633-4561"));
        eSos.add(new Contact("Chandannagar","Hoogly","2683-1838"));
        eSos.add(new Contact("Dankuni","Hoogly","2659-4720"));
        eSos.add(new Contact("Dhaniakhali","Hoogly","953213-255225"));
        eSos.add(new Contact("Rishra","Hoogly","2672-6666"));
        eSos.add(new Contact("Singur","Hoogly","2630-1001"));
        eSos.add(new Contact("Sreerampur","Hoogly","2652-1200"));
        eSos.add(new Contact("Tarakeswar","Hoogly","953212-276252"));
        eSos.add(new Contact("Belur","Howrah","033 2654 0380"));
        eSos.add(new Contact("Golabari","Howrah","033 2666 3515"));
        eSos.add(new Contact("Howrah","Howrah","033 2641 1750"));
        eSos.add(new Contact("Shibpur","Howrah","033 2638 1028"));
        eSos.add(new Contact("Uluberia","Howrah","033 2661 0261"));
        eSos.add(new Contact("Malda","Malda","090238 45954"));
        eSos.add(new Contact("Bhagawangola","Murshidabad","03483 259 224"));
        eSos.add(new Contact("Isalampore","Murshidabad","083486 43944"));
        eSos.add(new Contact("Murshidabad","Murshidabad","03482 270 221"));
        eSos.add(new Contact("Lalgola","Murshidabad","03483 274 244"));
        eSos.add(new Contact("Chakdaha","Nadia","03473 242 022"));
        eSos.add(new Contact("Dhubulia","Nadia","03472 261 211"));
        eSos.add(new Contact("Kotwali(Krishnanagar)","Nadia","03472 253 154"));
        eSos.add(new Contact("Santipur","Nadia","03472 278 030"));
        eSos.add(new Contact("Kalyani","Nadia","033 2582 8100"));
        eSos.add(new Contact("Karimpur","Nadia","03471 255 137"));
        eSos.add(new Contact("CoopersCamp","Nadia","03473 210 142"));
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        for(int i = 0; i<eSos.size(); i++){
            values.put("policeStation", eSos.get(i).name);
            values.put("District", eSos.get(i).district);
            values.put("PhoneNo",eSos.get(i).phone);
            long result=MyDB.insert("SOS",null,values);
        }
        /*SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("District",district);
        values.put("policeStation",policeStation);
        values.put("PhoneNo", phoneNo);
        long result=MyDB.insert("SOS",null,values);*/
    }

    public ArrayList<Contact> getSos(){
        return eSos;
    }

    public void insertHospitalData()
    {
        Hf=new ArrayList<Hcontact>();
        Hf.add(new Hcontact("Maa Seva Ashram Nursing & Diagnostic Centre","Alipurduar","ALIPURDUAR","8906105778"));
        Hf.add(new Hcontact("Alipurduar District Hospital","Alipurduar Municipality","ALIPURDUAR","03564-255085"));
        Hf.add(new Hcontact("Varsha Falakata Health Care P Ltd","GOPE NAGAR, POPS FALAKATA, ALIPURDUAR","ALIPURDUAR","9733088135"));
        Hf.add(new Hcontact("Bishnupur District Hospital","Bishnupur","BANKURA","3244252065"));
        Hf.add(new Hcontact("Bankura Nursing Home","Patpur Bankura 722101 Dist Bankura West Bengal","BANKURA","9434003087"));
        Hf.add(new Hcontact("Dr. R.B.S. Memorial Hospital","Rabindra Sarni Bankura Bankura Bankura 722101 Dist Bankura West Bengal","BANKURA","9434022711"));
        Hf.add(new Hcontact("Glocal Healthcare Systems Pvt. Ltd. Sonamukhi","Gangadi Nanga, Bankura To Burdhwan Road, Sonamukhi","BANKURA","8697712744"));
        Hf.add(new Hcontact("Arogyo Multi Speciality Hospital","Bolpur Ilambazar Bypass Road, Raipur Bolpur - 731204","BIRBHUM","8170044491"));
        Hf.add(new Hcontact("H. C. Nursing Home","Marowari Patti, Sainthia","BIRBHUM","9433234448"));
        Hf.add(new Hcontact("Birbhum District Hospital","Suri Municipality","BIRBHUM","03462 255 766"));
        Hf.add(new Hcontact("Niramoy Nursing Home","N H Road, Bharsala Para, PO - Rampurhat, Birbhum, WB - 731224","BIRBHUM","9332647604"));
        Hf.add(new Hcontact("Boxirhat BPHC","Tufanganj-II","COOCHBEHAR","03582-263223"));
        Hf.add(new Hcontact("Dr. P. K Saha Hospital Pvt. Ltd.","Bairagi Dighi Bye Lane,Post & Dist- Cooch Behar","COOCHBEHAR","03582-223224"));
        Hf.add(new Hcontact("Mili Nursing Home","Madan Homa Para, Post & P.S. - Dinhata, Dist.- Cooch Behar","COOCHBEHAR","03583-256865"));
        Hf.add(new Hcontact("District Hospital Hooghly","Hooghly-Chinsurah Municipality","HOOGHLY","033-26802293"));
        Hf.add(new Hcontact("Care Concern Hospital Pvt Ltd","87/A,G.T.Road(West) Serampore,Hooghly-712203","HOOGHLY","9007308961"));
        Hf.add(new Hcontact("Kamala Ray Hospital Pvt Ltd","20 K.G.Road,Hindmotor,Hooghly-712233","HOOGHLY","7003079471"));
        Hf.add(new Hcontact("Kanak Nursing Home","1 No.Netaji Park,Bandel,Hooghly-712123","HOOGHLY","9332046795"));
        Hf.add(new Hcontact("Paramount Health Care","148/151,G.T.Road,Serampore,Hooghly-712201","HOOGHLY","9830375381"));
        Hf.add(new Hcontact("Nadia District Hospital","Krishnanagar Municipality","Nadia","03472-258533"));
        Hf.add(new Hcontact("College of Medicine & J.N.M.Hospital","Kalyani Municipality","Nadia","033-25828562"));
        Hf.add(new Hcontact("Santipur SG Hospital","Santipur Municipality","Nadia","03472-278057"));
        Hf.add(new Hcontact("Dhubulia RH","Krishnanagar-II","Nadia","03472-261219"));
        Hf.add(new Hcontact("Health Care Nursing Home","B-7/46S Central,Kalyani","Nadia","033-25825728"));
        Hf.add(new Hcontact("Life Care Nursing Home","Prembazar, 25, Vivekananda Road High Coop. Kharagpur 721306","PASCHIM MEDINIPUR","8016543681"));
        Hf.add(new Hcontact("New Life Nursing Home","Kushpata, Ghatal, West Midnapore, W.B.-721212","PASCHIM MEDINIPUR","9733763436"));
        Hf.add(new Hcontact("Nandigram RH","Nandigram-I","Purba MEDINIPUR","03224-232611"));
        Hf.add(new Hcontact("District Hospital, Tamluk","Tamluk","Purba MEDINIPUR","03228-263209"));
        Hf.add(new Hcontact("Digha Ak BPHC","Ramnagar-I","Purba MEDINIPUR","03220-266260"));
        Hf.add(new Hcontact("Diamond Harbour District Hospital","Diamond Harbour Municipality","South 24 Pgs","3174255633"));
        Hf.add(new Hcontact("Jirangacha Rural Hospital","Bhangore -II","South 24 Pgs","03215-275755"));
        Hf.add(new Hcontact("Gosaba Rural Hospital","Gosaba","South 24 Pgs","03218 236112"));
        Hf.add(new Hcontact("Vidyasagar S G Hospital","Kolkata Municipal Corporation","South 24 Pgs","3323970546"));
        Hf.add(new Hcontact("Raniganj BPHC","Raniganj","Bardhaman","0341-2445298"));
        Hf.add(new Hcontact("Asansol DH","Asansol Municipal Corporation","Bardhaman","3412302388"));
        Hf.add(new Hcontact("Barddhaman Medical College & Hospital","Barddhaman Municipality","Bardhaman","0342-2558641/42/43"));
        Hf.add(new Hcontact("Durgapur SDH","Durgapur Municipal Corporation","Bardhaman","0343-2537163"));
        Hf.add(new Hcontact("Kalna SDH","Kalna Municipality","Bardhaman","3454255052"));
        Hf.add(new Hcontact("Katwa SDH","Katwa Municipality","Bardhaman","03453-255060"));
        Hf.add(new Hcontact("E.S.I.Hospital, Manicktala","Kolkata Municipal Corporation","Kolkata","23557218"));
        Hf.add(new Hcontact("Alipur Central Hospital","Kolkata Municipal Corporation","Kolkata","24791053"));
        Hf.add(new Hcontact("Calcutta National Medical College & Hospital","Kolkata Municipal Corporation","Kolkata","22897424"));
        Hf.add(new Hcontact("S. S. K. M. Hospital","Kolkata Municipal Corporation","Kolkata","22236242"));
        Hf.add(new Hcontact("Barasat District Hospital","Barasat Municipality","North 24 Pgs","2592-1212"));
        Hf.add(new Hcontact("Barrackpur Cantonment Hospital","Barrackpur Municipality","North 24 Pgs","033-25020570"));
        Hf.add(new Hcontact("Bhatpara State General Hospital","Bhatpara Municipality","North 24 Pgs","033-2580-0513"));
        Hf.add(new Hcontact("Naihati State General Hospital","Naihati Municipality","North 24 Pgs","033 2581 2933"));
        Hf.add(new Hcontact("Murshidabad Medical College & Hospital","Berhampure"," Murshidabad","03482252131"));
        Hf.add(new Hcontact("Islampur RH","Raninagar I","Murshidabad","3481236377"));
        Hf.add(new Hcontact("Lalbag S.D. Hospital","Murshidabad","Murshidabad","03482270247"));
        Hf.add(new Hcontact("Malda Medical College & Hospital","English Bazar Municipality","Malda","03512-252480"));
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        for(int i = 0; i<Hf.size(); i++)
        {
            values.put("hospitalName", Hf.get(i).hospitalName);
            values.put("Place",Hf.get(i).place);
            values.put("District", Hf.get(i).district);
            values.put("contactNo",Hf.get(i).contactNo);
            long result=MyDB.insert("HospitalContact",null,values);
        }
    }

    public ArrayList<Hcontact> getHospital(){
        return Hf;
    }
}