package com.example.activitylifecicle;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowGuess extends AppCompatActivity {
    private TextView showGuessTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        showGuessTextView = findViewById(R.id.showGuessedText);
        String guessedText = getIntent().getStringExtra("guess");

        if(guessedText != null) {
            showGuessTextView.setText(guessedText);
        }

        showGuessTextView.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.putExtra("Message back", "From Second Activity");
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}