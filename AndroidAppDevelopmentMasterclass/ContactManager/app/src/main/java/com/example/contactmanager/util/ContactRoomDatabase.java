package com.example.contactmanager.util;

import android.content.Context;

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

                Contact2 contact = new Contact2("Stefan", "Student");
                contactDao.insert(contact);


            });
        }
    };
}
