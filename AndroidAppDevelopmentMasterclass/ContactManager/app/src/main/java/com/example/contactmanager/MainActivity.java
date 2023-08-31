package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.contactmanager.data.DatabaseHandler;
import com.example.contactmanager.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler dbHandler = new DatabaseHandler(MainActivity.this);

        Contact jeremy = new Contact();
        jeremy.setName("JSON");
        jeremy.setPhoneNumber("9876543210");

        // Get 1 contact
        //Contact currentContact = dbHandler.getContact(1);
        //currentContact.setName("NewJeremy");
        //dbHandler.deleteContact(currentContact);
        //int updatedRow = dbHandler.updateContact(currentContact);
        //Log.d("Contact updated ", "onCreate: " + updatedRow);
        List<Contact> contactList = dbHandler.getAllContacts();
        for (Contact contact: contactList) {
            Log.d("Found Contact", "Name: " + contact.getName());
        }

        Log.d("Contact", "count: " + dbHandler.getCount());
    }
}