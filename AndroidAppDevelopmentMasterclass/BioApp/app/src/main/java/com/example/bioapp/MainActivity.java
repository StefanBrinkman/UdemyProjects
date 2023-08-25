package com.example.bioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bioapp.data.Bio;
import com.example.bioapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final Bio bio = new Bio();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        bio.setName("Stefan Brinkman");
        binding.setBio(bio);

        binding.doneButton.setOnClickListener(view -> addHobbies(view));
    }

    public void addHobbies(View view) {
        bio.setHobbies(String.format("Hobbies: " +
                binding.enterHobbies.getText().toString().trim()
        ));
        binding.invalidateAll();
        binding.hobbiesText.setVisibility(View.VISIBLE);

        // Hiding the keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}