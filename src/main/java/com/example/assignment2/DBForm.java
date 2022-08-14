package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBForm extends SQLiteOpenHelper {
    public DBForm(Context context) {
        super(context, "data.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table userDetails(name TEXT primary key,rollNo TEXT,regNo TEXT,sem TEXT,program TEXT,degree TEXT,fee TEXT,depNo TEXT,subject TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists userDetails");

    }
    public Boolean insertuserdata(String name,String rollNo,String regNo,String sem,String program,
                                  String degree,String fee,String depNo,String subject){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("rollNo",rollNo);
        contentValues.put("regNo",regNo);
        contentValues.put("sem",sem);
        contentValues.put("program",program);
        contentValues.put("degree",degree);
        contentValues.put("fee",fee);
        contentValues.put("depNo",depNo);
        contentValues.put("subject",subject);
        

        long result = db.insert("userDetails",null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }

    }
    public Boolean updateuserdata(String name,String rollNo,String regNo,String sem,String program,
                                  String degree,String fee,String depNo,String subject){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rollNo",rollNo);
        contentValues.put("regNo",regNo);
        contentValues.put("sem",sem);
        contentValues.put("program",program);
        contentValues.put("degree",degree);
        contentValues.put("fee",fee);
        contentValues.put("depNo",depNo);
        contentValues.put("subject",subject);

        Cursor cursor = db.rawQuery("select * from userDetails where name =?",new String[] {name} );
        if(cursor.getCount()>0){
            long result = db.update("userDetails",contentValues,"name=?",new String[] {name});
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }

    }
    public Boolean deleteuserdata(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from userDetails where name =?",new String[] {name} );
        if(cursor.getCount()>0){
            long result = db.delete("userDetails","name=?",new String[] {name});
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from userDetails",null );
        return  cursor;
    }

}
