package com.example.contactmanager.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.contactmanager.data.ContactDao;
import com.example.contactmanager.model.Contact2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = { Contact2.class }, version = 1, exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {
    public static final int NUMBER_OF_THREADS = 4;
    public abstract ContactDao contactDao();

    private static volatile ContactRoomDatabase INSTANCE;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ContactRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (ContactRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class, "contact_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                ContactDao contactDao = INSTANCE.contactDao();
                contactDao.deleteAll();

                Contact2 contact1 = new Contact2("Stefan", "ICT");
                Contact2 contact2 = new Contact2("Ruben", "Directeur");
                Contact2 contact3 = new Contact2("Noah", "ICT");
                Contact2 contact4 = new Contact2("Liam", "Servicedesk");
                Contact2 contact5 = new Contact2("Luca", "Servicedesk");
                Contact2 contact6 = new Contact2("Lucas", "Servicedesk");
                Contact2 contact7 = new Contact2("Mees", "Administratie");
                Contact2 contact8 = new Contact2("Finn", "ICT");
                Contact2 contact9 = new Contact2("James", "Servicedesk");
                Contact2 contact10 = new Contact2("Milan", "ICT");
                Contact2 contact11 = new Contact2("Levi", "Administratie");
                Contact2 contact12 = new Contact2("Sem", "Manager");
                Contact2 contact13 = new Contact2("Flutter", "Coding");
                Contact2 contact14 = new Contact2("Android", "Coding");
                Contact2 contact15 = new Contact2("React", "Coding");
                Contact2 contact16 = new Contact2("PHP", "Coding");
                contactDao.insert(contact1);
                contactDao.insert(contact2);
                contactDao.insert(contact3);
                contactDao.insert(contact4);
                contactDao.insert(contact5);
                contactDao.insert(contact6);
                contactDao.insert(contact7);
                contactDao.insert(contact8);
                contactDao.insert(contact9);
                contactDao.insert(contact10);
                contactDao.insert(contact11);
                contactDao.insert(contact12);
                contactDao.insert(contact13);
                contactDao.insert(contact14);
                contactDao.insert(contact15);
                contactDao.insert(contact16);
            });
        }
    };
}
