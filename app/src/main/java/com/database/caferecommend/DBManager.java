package com.database.caferecommend;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016-11-25.
 */
public class DBManager extends SQLiteOpenHelper {
    public DBManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE CAFFE(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)");
        db.execSQL("CREATE TABLE CAFFE(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)");
        db.execSQL("CREATE TABLE CAFFE(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)");
        db.execSQL("CREATE TABLE CAFFE(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)");
        db.execSQL("CREATE TABLE CAFFE(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)");
        db.execSQL("CREATE TABLE CAFFE(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        //insert into 테이블명 values(속성, 속성)
        db.execSQL(_query);
        db.close();
    }

    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        //select * from 테이블명;
        //select 속성,속성...from 테이블명;
        Cursor cursor = db.rawQuery("select * from "+"테이블 이름", null);
        while (cursor.moveToNext()) ;
        return str;
    }
}