package edu.uta.avb9570.maverickride;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context,"maverickride.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table users(utaid text primary key, firstname text, lastname text, email text, password text, phone text, dob text, country text, address text, city text, state text, pin text, dlnumber text, dlexp text, aacm text, usertype text, userrole text, enabled text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }

    public boolean register(String utaid, String firstname, String lastname, String email, String password, String phone, String dob, String country, String address, String city, String state, String pin, String dlnumber, String dlexp, String aacm, String usertype, String userrole, String enabled){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("utaid", utaid);
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("phone", phone);
        contentValues.put("dob", dob);
        contentValues.put("country", country);
        contentValues.put("address", address);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("pin", pin);
        contentValues.put("dlnumber", dlnumber);
        contentValues.put("dlexp", dlexp);
        contentValues.put("aacm", aacm);
        contentValues.put("usertype", usertype);
        contentValues.put("userrole", userrole);
        contentValues.put("enabled", enabled);
        long ins = db.insert("users", null, contentValues);
        if(ins==-1) return false;
        else return true;
    }

    public Boolean checkutaid(String utaid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where utaid = ?", new String []{utaid});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean login(String utaid, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where utaid = ?", new String []{utaid});
        if (cursor.moveToFirst()){
            do{
                String pass = cursor.getString(cursor.getColumnIndex("password"));
                if(pass.equals(password)){
                    return true;
                }
                else {
                    return false;
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    public String getuserrole(String utaid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where utaid = ?", new String []{utaid});
        if (cursor.moveToFirst()){
            do{
                String userrole = cursor.getString(cursor.getColumnIndex("userrole"));
                return userrole;
            }while(cursor.moveToNext());
        }
        cursor.close();
        return "null";
    }

    public String getuserenabled(String utaid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where utaid = ?", new String []{utaid});
        if (cursor.moveToFirst()){
            do{
                String userenabled = cursor.getString(cursor.getColumnIndex("enabled"));
                return userenabled;
            }while(cursor.moveToNext());
        }
        cursor.close();
        return "null";
    }

    public String getfirstname(String utaid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where utaid = ?", new String []{utaid});
        if (cursor.moveToFirst()){
            do{
                String firstname = cursor.getString(cursor.getColumnIndex("firstname"));
                return firstname;
            }while(cursor.moveToNext());
        }
        cursor.close();
        return "null";
    }

    public String getaacm(String utaid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where utaid = ?", new String []{utaid});
        if (cursor.moveToFirst()){
            do{
                String aacm = cursor.getString(cursor.getColumnIndex("aacm"));
                return aacm;
            }while(cursor.moveToNext());
        }
        cursor.close();
        return "null";
    }


    public ArrayList<String> searchcars(String capacity){
        ArrayList<String> cars = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT carname,capacity,weekdayrate,weekendrate,weekrate from cars where capacity >= ?", new String []{capacity});
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    String carname = c.getString(c.getColumnIndex("carname"));
                    String capacity1 = c.getString(c.getColumnIndex("capacity"));
                    String weekdayrate = c.getString(c.getColumnIndex("weekdayrate"));
                    String weekendrate = c.getString(c.getColumnIndex("weekendrate"));
                    String weekrate = c.getString(c.getColumnIndex("weekrate"));
                    cars.add(carname + "\t\t\t\t\t" + capacity1 + "\t\t\t\t\t" + weekdayrate + "\t\t\t\t\t" + weekendrate + "\t\t\t\t\t" + weekrate);
                }while(c.moveToNext());
            }
        }
        c.close();
        return cars;
    }

    public ArrayList<String> getextrasrates(String carname){
        ArrayList<String> extrasrates = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT gpsrate,onstarrate,siriusxmrate from cars where carname >= ?", new String []{carname});
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    String gpsrate = c.getString(c.getColumnIndex("gpsrate"));
                    String onstarrate = c.getString(c.getColumnIndex("onstarrate"));
                    String siriusxmrate = c.getString(c.getColumnIndex("siriusxmrate"));
                    extrasrates.add(gpsrate);
                    extrasrates.add(onstarrate);
                    extrasrates.add(siriusxmrate);
                }while(c.moveToNext());
            }
        }
        c.close();
        return extrasrates;
    }

    public boolean confirmrental(String utaid, String firstname, String carname, String baseprice, String taxapplied, String discount, String startdate, String starttime, String enddate, String endtime, String totalamt){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("utaid", utaid);
        contentValues.put("name", firstname);
        contentValues.put("carname", carname);
        contentValues.put("baseprice", baseprice);
        contentValues.put("taxapplied", taxapplied);
        contentValues.put("discount", discount);
        contentValues.put("startdate", startdate);
        contentValues.put("starttime", starttime);
        contentValues.put("enddate", enddate);
        contentValues.put("endtime", endtime);
        contentValues.put("totalamt", totalamt);
        long ins = db.insert("rentals", null, contentValues);
        if(ins==-1) return false;
        else return true;
    }


    public Integer getlastrentalid(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT rentalid from rentals order by rentalid desc limit 1",null);
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    Integer rentalid = c.getInt(c.getColumnIndex("rentalid"));
                    return  rentalid;
                }while(c.moveToNext());
            }
        }
        c.close();
        return null;
    }

    public ArrayList<String> getmyreservations(String utaid){
        ArrayList<String> rentals = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * from rentals where utaid = ?", new String []{utaid});
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    String rentalid = c.getString(c.getColumnIndex("rentalid"));
                    String startdate = c.getString(c.getColumnIndex("startdate"));
                    String starttime = c.getString(c.getColumnIndex("starttime"));
                    String enddate = c.getString(c.getColumnIndex("enddate"));
                    String endtime = c.getString(c.getColumnIndex("endtime"));
                    rentals.add(rentalid + "\t\t\t" + startdate + "\t\t\t" + starttime + "\t\t\t" + enddate + "\t\t\t" + endtime);
                }while(c.moveToNext());
            }
        }
        c.close();
        return rentals;
    }

    public ArrayList<String> getrentaldetails(String rentalid){
        ArrayList<String> rentaldetails = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * from rentals where rentalid >= ?", new String []{rentalid});
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    String rentalid1 = c.getString(c.getColumnIndex("rentalid"));
                    String utaid = c.getString(c.getColumnIndex("utaid"));
                    String name = c.getString(c.getColumnIndex("name"));
                    String carname = c.getString(c.getColumnIndex("carname"));
                    String baseprice = c.getString(c.getColumnIndex("baseprice"));
                    String taxapplied = c.getString(c.getColumnIndex("taxapplied"));
                    String discount = c.getString(c.getColumnIndex("discount"));
                    String startdate = c.getString(c.getColumnIndex("startdate"));
                    String starttime = c.getString(c.getColumnIndex("starttime"));
                    String enddate = c.getString(c.getColumnIndex("enddate"));
                    String endtime = c.getString(c.getColumnIndex("endtime"));
                    String totalamt = c.getString(c.getColumnIndex("totalamt"));
                    rentaldetails.add(rentalid1 + "\t\t\t" + utaid + "\t\t\t" + name + "\t\t\t" + carname + "\t\t\t" + baseprice + "\t\t\t" + taxapplied + "\t\t\t" + discount + "\t\t\t" + startdate + "\t\t\t" + starttime + "\t\t\t" + enddate + "\t\t\t" + endtime + "\t\t\t" + totalamt);
                }while(c.moveToNext());
            }
        }
        c.close();
        return rentaldetails;
    }

    public Boolean deleterental(String rentalid){
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            System.out.println("rentalid = "+rentalid);
            db.execSQL("delete from rentals where rentalid = "+rentalid);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean updateprofile(String utaid, String firstname, String lastname, String email, String password, String phone, String dob, String country, String address, String city, String state, String pin, String dlexp, String aacm, String usertype){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("phone", phone);
        contentValues.put("dob", dob);
        contentValues.put("country", country);
        contentValues.put("address", address);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("pin", pin);
        contentValues.put("dlexp", dlexp);
        contentValues.put("aacm", aacm);
        contentValues.put("usertype", usertype);
        String where = "utaid=?";
        String[] whereArgs = new String[] {String.valueOf(utaid)};
        long up = db.update("users", contentValues, where, whereArgs);
        if(up==-1) return false;
        else return true;
    }

    public ArrayList<String> getprofile(String utaid){
        ArrayList<String> profile = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * from users where utaid = ?", new String []{utaid});
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    String firstname = c.getString(c.getColumnIndex("firstname"));
                    String lastname = c.getString(c.getColumnIndex("lastname"));
                    String password = c.getString(c.getColumnIndex("password"));
                    String email = c.getString(c.getColumnIndex("email"));
                    String phone = c.getString(c.getColumnIndex("phone"));
                    String dob = c.getString(c.getColumnIndex("dob"));
                    String country = c.getString(c.getColumnIndex("country"));
                    String address = c.getString(c.getColumnIndex("address"));
                    String city = c.getString(c.getColumnIndex("city"));
                    String state = c.getString(c.getColumnIndex("state"));
                    String pincode = c.getString(c.getColumnIndex("pin"));
                    String dlexp = c.getString(c.getColumnIndex("dlexp"));
                    profile.add(firstname + "\t\t\t" + lastname + "\t\t\t" + email + "\t\t\t" + password + "\t\t\t" + phone + "\t\t\t" + dob + "\t\t\t" + country + "\t\t\t" + address + "\t\t\t" + city + "\t\t\t" + state + "\t\t\t" + pincode + "\t\t\t" + dlexp);
                }while(c.moveToNext());
            }
        }
        c.close();
        return profile;
    }

    public ArrayList<String> getallreservations(){
        ArrayList<String> rentals = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * from rentals",null);
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    String rentalid = c.getString(c.getColumnIndex("rentalid"));
                    String startdate = c.getString(c.getColumnIndex("startdate"));
                    String starttime = c.getString(c.getColumnIndex("starttime"));
                    String enddate = c.getString(c.getColumnIndex("enddate"));
                    String endtime = c.getString(c.getColumnIndex("endtime"));
                    rentals.add(rentalid + "\t\t\t" + startdate + "\t\t\t" + starttime + "\t\t\t" + enddate + "\t\t\t" + endtime);
                }while(c.moveToNext());
            }
        }
        c.close();
        return rentals;
    }

    public ArrayList<String> viewbookingsondate(String date){
        System.out.println("view bookings date = "+date);
        ArrayList<String> rentals1 = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * from rentals where startdate = ?", new String []{date});
        //c.moveToFirst();
        System.out.println("cursor count = "+c.getCount());
        while (c.moveToNext()) {


                    String rentalid = c.getString(c.getColumnIndex("rentalid"));
                    String startdate = c.getString(c.getColumnIndex("startdate"));
                    String starttime = c.getString(c.getColumnIndex("starttime"));
                    String enddate = c.getString(c.getColumnIndex("enddate"));
                    String endtime = c.getString(c.getColumnIndex("endtime"));
                    rentals1.add(rentalid + "\t\t\t" + startdate + "\t\t\t" + starttime + "\t\t\t" + enddate + "\t\t\t" + endtime);


        }
        c.close();
        return rentals1;
    }

    public boolean enableuser(String utaid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("enabled", "yes");
        String where = "utaid=?";
        String[] whereArgs = new String[] {String.valueOf(utaid)};
        long up = db.update("users", contentValues, where, whereArgs);
        if(up==-1) return false;
        else return true;
    }

    public boolean revokeuser(String utaid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("enabled", "no");
        String where = "utaid=?";
        String[] whereArgs = new String[] {String.valueOf(utaid)};
        long up = db.update("users", contentValues, where, whereArgs);
        if(up==-1) return false;
        else return true;
    }
}

