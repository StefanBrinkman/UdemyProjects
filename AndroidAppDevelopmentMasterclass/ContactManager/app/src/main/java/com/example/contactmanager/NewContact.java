package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactmanager.model.Contact2;
import com.example.contactmanager.model.ContactViewModel;

public class NewContact extends AppCompatActivity {
    private EditText enterName;
    private EditText enterOccupation;
    private Button saveInfoButton;

    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        enterName = findViewById(R.id.enterName);
        enterOccupation = findViewById(R.id.enterOccupation);
        saveInfoButton = findViewById(R.id.save_button);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(NewContact.this.getApplication()).create(ContactViewModel.class);

        saveInfoButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

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
    }
}