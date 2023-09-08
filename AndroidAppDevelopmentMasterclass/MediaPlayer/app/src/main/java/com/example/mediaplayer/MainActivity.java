package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonOne, buttonTwo, buttonThree, buttonFour;

    private String soundEffect1 = String.valueOf(Uri.parse("http://commondatastorage.googleapis.com/codeskulptor-assets/week7-brrring.m4a"));
    private String soundEffect2 = "http://codeskulptor-demos.commondatastorage.googleapis.com/descent/background%20music.mp3";
    private String soundEffect3 = "http://codeskulptor-demos.commondatastorage.googleapis.com/descent/spring.mp3";
    private String soundEffect4 = "http://codeskulptor-demos.commondatastorage.googleapis.com/descent/gotitem.mp3";
    private int sound1, sound2, sound3, sound4;

    private boolean soundLoaded = false;

    private SoundPool soundPool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonFour = findViewById(R.id.buttonFour);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(4)
                .setAudioAttributes(audioAttributes)
                .build();

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.d("soundPool", "onLoadComplete: ");
                soundLoaded = true;
            }
        });
        try {
            sound1 = soundPool.l
            Log.d("Sound 1", "onCreate: " + sound1);
        } catch (Exception error) {
            Log.d("LOADING ERROR", "onLoad: " + error);
        }
//        sound2 = soundPool.load(soundEffect2, 1);
//        sound3 = soundPool.load(soundEffect3, 1);
//        sound4 = soundPool.load(soundEffect4, 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOne:
                    if(soundLoaded) {
                        soundPool.play(sound1,1,1,0,1,1);
                    }
                break;

            case R.id.buttonTwo:
                if(soundLoaded) {
                    soundPool.play(sound2,1,1,0,1,1);
                }

                break;

            case R.id.buttonThree:
                if(soundLoaded) {
                    soundPool.play(sound3,1,1,0,1,1);
                }
                break;

            case R.id.buttonFour:
                if(soundLoaded) {
                    soundPool.play(sound4,1,1,0,1,1);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}