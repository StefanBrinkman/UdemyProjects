package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button playButton;
    private SeekBar seekbar;

    private String soundEffect1 = "http://codeskulptor-demos.commondatastorage.googleapis.com/descent/Zombie.mp3";
    private String soundEffect2 = "http://codeskulptor-demos.commondatastorage.googleapis.com/descent/background%20music.mp3";
    private String soundEffect3 = "http://codeskulptor-demos.commondatastorage.googleapis.com/descent/spring.mp3";
    private String soundEffect4 = "http://codeskulptor-demos.commondatastorage.googleapis.com/descent/gotitem.mp3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        mediaPlayer = new MediaPlayer();
        seekbar = findViewById(R.id.seekBarId);

        try {
            mediaPlayer.setDataSource("https://buildappswithpaulo.com/music/watch_me.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int duration = mp.getDuration();
                // TOAST
            }
        });
        MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                seekbar.setMax(mediaPlayer.getDuration());
                playButton.setOnClickListener(view -> {
                    if(mp.isPlaying()) {
                        mp.pause();
                        playButton.setText("Play");
                    } else {
                        mp.start();
                        playButton.setText("Pause");
                    }
                });
            }
        };

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("Seekbar", "Progress change");
                if(fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("Seekbar", "Dragging started");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("Seekbar", "Dragging ended on: " + seekBar.getProgress());
            }
        });
        mediaPlayer.setOnPreparedListener(preparedListener);
        mediaPlayer.prepareAsync();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.release();
        }
    }
}