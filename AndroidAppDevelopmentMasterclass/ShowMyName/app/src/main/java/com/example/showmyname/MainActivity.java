package com.example.showmyname;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button showButton;
    private TextView nameText;
    private EditText enteredName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showButton = findViewById(R.id.button);
        nameText = findViewById(R.id.textView);
        enteredName = findViewById(R.id.editTextName);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = enteredName.getText().toString();
                if(name.isEmpty()) {
                    nameText.setText("Hello ???");
                } else {
                    nameText.setText("Hello " + name);
                }
            }
        });
    }
}