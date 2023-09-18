package com.example.contactmanager.data;

import android.app.Application;
import android.util.Log;

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

    public LiveData<Contact2> get(int id) {
        return contactDao.get(id);
    }

    public void update(Contact2 contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> contactDao.update(contact));
    }

    public void delete(Contact2 contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> contactDao.delete(contact));
    }
}
