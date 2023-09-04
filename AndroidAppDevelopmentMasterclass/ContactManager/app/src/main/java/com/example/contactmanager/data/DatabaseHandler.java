package com.example.contactmanager.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.contactmanager.R;
import com.example.contactmanager.model.Contact;
import com.example.contactmanager.model.Contact2;
import com.example.contactmanager.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper{
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME
                + "(" + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";

        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.db_drop_contact);
        db.execSQL(DROP_TABLE, new String[]{
                Util.DATABASE_NAME
        });

        onCreate(db);
    }

    public void createContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        db.insert(Util.TABLE_NAME, null, values);
        Log.d("DBHandler", "createContact: " + values);
        db.close();
    }

    public Contact getContact(int contactId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                Util.TABLE_NAME,
                new String[]{Util.KEY_ID,Util.KEY_NAME, Util.KEY_PHONE_NUMBER},
                Util.KEY_ID + "=?", new String[]{String.valueOf(contactId)},
                null,
                null,
                null
                );

        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));

        return contact;
    }

    public List<Contact2> getAllContacts() {
        List<Contact2> contactsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                Contact2 contact = new Contact2(cursor.getString(1), cursor.getString(2));
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setOccupation(cursor.getString(2));

                contactsList.add(contact);
            } while(cursor.moveToNext());
        }
        return contactsList;
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        return db.update(
                Util.TABLE_NAME,
                values,
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())}
        );
    }

    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                Util.TABLE_NAME,
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())}
        );
        db.close();
    }

    public int getCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
}
