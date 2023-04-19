package com.example.mar23thursnew_est;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseControl {

    // update this to work with the new information changed in Helper

    SQLiteDatabase database;
    DatabaseHelper helper;

    public DatabaseControl(Context context){
        helper = new DatabaseHelper(context);
    }

    public void open(){
        database = helper.getWritableDatabase();
    }

    public void close(){
        helper.close();
    }

    //name = title, state = genre, hello = year
    public boolean insert(String name, String state, String hello){
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("state", state);
        values.put("hello", hello);
        return database.insert("contact", null, values) > 0;
    }

    public void delete(String n){
        database.delete("contact", "name =\"" + n + "\"", null);
    }

    public String getState(String name){
        String query = "select state from contact where name =\"" + name + "\"";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String state = cursor.getString(0);
        cursor.close();
        return state;
    }

    public String getYear(String name){
        String query = "select hello from contact where name =\"" + name + "\"";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String year = cursor.getString(0);
        cursor.close();
        return year;
    }

    public String[] getAllNames(){
        String query = "select name from contact";
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<String> list = new ArrayList<String>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String name = cursor.getString(0);
            list.add(name);
            cursor.moveToNext();
        }
        cursor.close();
        String[] array = new String[list.size()];
        return list.toArray(array);
    }

    public String[] getAllYears(){
        String query = "select hello from contact";
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<String> list = new ArrayList<String>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String hello = cursor.getString(0);
            list.add(hello);
            cursor.moveToNext();
        }
        cursor.close();
        String[] array = new String[list.size()];
        return list.toArray(array);
    }


}
