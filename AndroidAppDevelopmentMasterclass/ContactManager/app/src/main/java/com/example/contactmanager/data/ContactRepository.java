package com.example.contactmanager.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import com.example.contactmanager.model.Contact2;
import com.example.contactmanager.util.ContactRoomDatabase;

import java.util.List;

public class ContactRepository {
    private ContactDao contactDao;
    private LiveData<List<Contact2>> allContacts;

    public ContactRepository(Application application){
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        contactDao = db.contactDao();

        allContacts = contactDao.getAllContacts();
    }

    public LiveData<List<Contact2>> getAllData() {
        return allContacts;
    }

    public void insert(Contact2 contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() ->{
            contactDao.insert(contact);
        });
    }
}
