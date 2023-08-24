package com.example.makeitrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private Button makeItRain;
    private Button showInfo;
    private TextView amountValue;
    private int amountCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeItRain = findViewById(R.id.buttonMakeItRain);
        amountValue = findViewById(R.id.amount_value);
    }

    public void showAmount(View view) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        amountCounter += 100;
        amountValue.setText(
                String.valueOf(
                        numberFormat.format(amountCounter)
                )
        );
        if(amountCounter >= 1000) {
            amountValue.setTextColor(
                    ContextCompat.getColor(
                            MainActivity.this, R.color.purple_700
                    )
            );
        }
    }

    public void showInfo(View view) {
        Toast.makeText(
                MainActivity.this,
                "Toast",
                Toast.LENGTH_SHORT
        ).show();
        Snackbar.make(
                amountValue, "Snackbar", Snackbar.LENGTH_LONG
        ).show();
    }
}