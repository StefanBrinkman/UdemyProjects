package com.example.contactmanager.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.contactmanager.model.Contact2;
import java.util.List;

@Dao
public interface ContactDao {
    // CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact2 contact);

    @Query("DELETE FROM contact_table")
    void deleteAll();

    @Query("SELECT * FROM contact_table ORDER BY name ASC")
    LiveData<List<Contact2>> getAllContacts();

    @Query("SELECT * FROM contact_table WHERE contact_table.id == :id")
    LiveData<Contact2> get(int id);

    @Update
    void update(Contact2 contact);

    @Delete
    void delete(Contact2 contact);
}
