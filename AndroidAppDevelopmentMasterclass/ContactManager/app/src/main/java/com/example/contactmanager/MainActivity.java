package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.contactmanager.adapters.RecyclerViewAdapter;
import com.example.contactmanager.data.DatabaseHandler;
import com.example.contactmanager.model.Contact2;
import com.example.contactmanager.model.ContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;
    private ContactViewModel contactViewModel;
    private FloatingActionButton fabNewContact;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private LiveData<List<Contact2>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        fabNewContact = findViewById(R.id.floatingAddContactButton);
        contactArrayList = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).create(ContactViewModel.class);

        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contactArrayList
        );

        contactViewModel.getAllContacts().observe(this, contacts -> {
            // Set up adapter
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewAdapter = new RecyclerViewAdapter(contacts, MainActivity.this);
            recyclerView.setAdapter(recyclerViewAdapter);
        });

        fabNewContact.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewContact.class);
            startActivity(intent);
        });
    }
}