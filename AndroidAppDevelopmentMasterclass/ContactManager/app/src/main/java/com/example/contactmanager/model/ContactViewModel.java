package com.example.contactmanager.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.contactmanager.data.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    public static ContactRepository repository;
    public final LiveData<List<Contact2>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository(application);
        allContacts = repository.getAllData();
    }

    public LiveData<List<Contact2>> getAllContacts() {
        return allContacts;
    }

    public static void insert(Contact2 contact) {
        repository.insert(contact);
    }

    public LiveData<Contact2> getContact(int id) {
        return repository.get(id);
    }

    public static void updateContact(Contact2 contact) {
        repository.update(contact);
    }

    public static void deleteContact(Contact2 contact) {
        repository.delete(contact);
    }
}
