package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactmanager.model.Contact2;
import com.example.contactmanager.model.ContactViewModel;
import com.google.android.material.snackbar.Snackbar;

public class NewContact extends AppCompatActivity {
    private EditText enterName;
    private EditText enterOccupation;
    private Button saveInfoButton;
    private Button updateButton;
    private Button deleteButton;
    private int contactId = 0;
    private ContactViewModel contactViewModel;
    private Boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        enterName = findViewById(R.id.enterName);
        enterOccupation = findViewById(R.id.enterOccupation);

        saveInfoButton = findViewById(R.id.save_button);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(NewContact.this.getApplication()).create(ContactViewModel.class);

        Bundle bundle = getIntent().getExtras();
        if(getIntent().hasExtra(MainActivity.contact_id)) {
            contactId = bundle.getInt(MainActivity.contact_id);
            contactViewModel.getContact(contactId).observe(this, contact -> {
                if(contact != null) {
                    enterName.setText(contact.getName());
                    enterOccupation.setText(contact.getOccupation());
                }
            });
            isEdit = true;
        }


        saveInfoButton.setOnClickListener(view -> {
            if (!TextUtils.isEmpty(enterName.getText()) &&
                    !TextUtils.isEmpty(enterOccupation.getText())
            ){
                Contact2 contact = new Contact2(
                        enterName.getText().toString(),
                        enterOccupation.getText().toString()
                );
                ContactViewModel.insert(contact);
                Intent intent = new Intent(NewContact.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(
                        this,
                        R.string.enter_information,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        updateButton.setOnClickListener(v -> {
            int updateContactId = contactId;
            String updatedName = enterName.getText().toString().trim();
            String updatedOccupation = enterOccupation.getText().toString().trim();

            if(TextUtils.isEmpty(updatedName) || TextUtils.isEmpty(updatedOccupation)) {
                Snackbar.make(enterName, R.string.enter_information, Snackbar.LENGTH_SHORT).show();
            } else {
                Contact2 contact = new Contact2(updatedName, updatedOccupation);
                contact.setId(updateContactId);
                ContactViewModel.updateContact(contact);
                finish();
            }
        });

        deleteButton.setOnClickListener(v -> {
            int updateContactId = contactId;
            String updatedName = enterName.getText().toString().trim();
            String updatedOccupation = enterOccupation.getText().toString().trim();

            if(TextUtils.isEmpty(updatedName) || TextUtils.isEmpty(updatedOccupation)) {
                Snackbar.make(enterName, R.string.enter_information, Snackbar.LENGTH_SHORT).show();
            } else {
                Contact2 contact = new Contact2(updatedName, updatedOccupation);
                contact.setId(updateContactId);
                ContactViewModel.deleteContact(contact);
                finish();
            }
        });

        if(isEdit) {
            saveInfoButton.setVisibility(View.GONE);
        } else {
            updateButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }
    }

}