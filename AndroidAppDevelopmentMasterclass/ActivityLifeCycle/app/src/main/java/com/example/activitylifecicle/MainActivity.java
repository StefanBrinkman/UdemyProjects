package com.example.activitylifecicle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button showGuess;
    private TextView guessedText;
    private final int REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showGuess = findViewById(R.id.guessButton);
        guessedText = findViewById(R.id.guessField);

        showGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userGuess = guessedText.getText().toString().trim();
                if(userGuess.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter guess",Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, ShowGuess.class);
                    intent.putExtra("guess", userGuess);
                    startActivityForResult(intent, REQUEST_CODE);
                    // startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String message = data.getStringExtra("guess");
        System.out.println("MESSAGE BACK: " + message);
        Toast.makeText(MainActivity.this, message,Toast.LENGTH_LONG).show();
    }
}