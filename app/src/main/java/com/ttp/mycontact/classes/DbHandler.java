package com.ttp.mycontact.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DbHandler {
    SQLiteDatabase database;
    DbMaker dbMaker;
    Context context;

    public DbHandler(Context context) {
        this.context = context;
        dbMaker = new DbMaker(context);


    }

    public void open() {
        database = dbMaker.getWritableDatabase();
    }

    public void close() {
        database.close();
    }


    public void insert(Contact contact) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("title", contact.getName());
        contentValues.put("phoneNumber",contact.getPhoneNumber());

        database.insert("todo",  null, contentValues);
    }


    public void delete(Contact contact) {


        database.delete("todo","id=? and title=? and phoneNumber=?",
                new String[]{String.valueOf(contact.getId()),contact.getName(),contact.getPhoneNumber()});


    }

    public void update(String oldValue,String newValue){

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", newValue);

        database.update("todo",contentValues,"title"+"=?",new String[]{oldValue});

    }


    public void update(Contact oldValue,Contact newValue){

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", newValue.getName());
        contentValues.put("phoneNumber", newValue.getPhoneNumber());
        database.update("todo",contentValues,"id=? and title=? and phoneNumber=?",
                new String[]{String.valueOf(oldValue.getId()),oldValue.getName(),oldValue.getPhoneNumber()});

    }




    public List<Contact> getAllTodo() {

        Cursor cursor = database.query("todo",new String[] {"id","title" , "phoneNumber"}, null,null,null,null,"id"+" ASC");
        cursor.moveToFirst();

        List<Contact>contactList=new ArrayList<>();

        for (int i = 0; i < cursor.getCount(); i++) {

            contactList.add(new Contact(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();

        return contactList;
    }


    public int getLastId(){

        Cursor cursor = database.query("todo",new String[] {"id","title" , "phoneNumber"}, null,null,null,null,"id"+" ASC");
        return cursor.getCount();
    }

}

