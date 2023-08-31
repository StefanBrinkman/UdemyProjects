package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.trivia.data.Repository;
import com.example.trivia.databinding.ActivityMainBinding;
import com.example.trivia.model.Question;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;
    private int currentScore = 0;
    List<Question> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        questionsList = new Repository().getQuestions(questionArrayList ->{
                    binding.questionTextView.setText(
                            questionArrayList.get(currentQuestionIndex).getAskedQuestion()
                    );
            SharedPreferences sharedPreferences = getSharedPreferences("highestScore", MODE_PRIVATE);

            String highestScore = String.valueOf(sharedPreferences.getInt("score", 0));
            Log.d("Loaded highScore: ", highestScore);
            binding.highestScore.setText("High score: " + highestScore);
                    updateQuestionCounter();
                }
        );



        binding.buttonNext.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionsList.size();
            updateQuestion();
        });

        binding.buttonTrue.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionsList.size();
            checkAnswer(true);
            updateQuestion();
        });

        binding.buttonFalse.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionsList.size();
            checkAnswer(false);
            updateQuestion();
        });
    }

    private void updateQuestionCounter() {
        if((currentQuestionIndex + 1) == questionsList.size()) {
            SharedPreferences sharedPreferences = getSharedPreferences("highestScore", MODE_PRIVATE);

            int highestScore = sharedPreferences.getInt("highestScore", 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if(currentScore > highestScore) {
                editor.putInt("score", currentScore);
                editor.apply();
                binding.highestScore.setText("High score: " + currentScore);
            }
        }

        binding.outOfTextView.setText(String.format(getString(R.string.questionCountText), currentQuestionIndex, questionsList.size()));
    }

    private void updateQuestion() {
        String question = questionsList.get(currentQuestionIndex).getAskedQuestion();
        binding.questionTextView.setText(question);
        updateQuestionCounter();
    }

    private void checkAnswer(boolean chosenUserAnswer) {
        boolean correctAnswer = questionsList.get(currentQuestionIndex).getAnswerTrue();
        int snackMessageId = 0;
        if(chosenUserAnswer == correctAnswer) {
            currentScore = currentScore + 1;
            snackMessageId = R.string.correct_answer;
            blinkAnimation();
        } else {
            if(currentScore > 0) {
                currentScore = currentScore - 1;
            }
            snackMessageId = R.string.incorrect_answer;
            shakeAnimation();
        }

        String newScore = "Score: " + currentScore;
        binding.currentScore.setText(newScore);
        Snackbar.make(binding.cardView, snackMessageId, Snackbar.LENGTH_SHORT).show();
    }

    private void shakeAnimation() {
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake_animation);
        binding.cardView.setAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextView.setTextColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    private void blinkAnimation() {
        Animation blink = AnimationUtils.loadAnimation(MainActivity.this, R.anim.blink_animation);
        binding.cardView.setAnimation(blink);
        blink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextView.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}