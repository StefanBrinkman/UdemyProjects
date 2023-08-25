package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import com.example.quizapp.databinding.ActivityMainBinding;
import androidx.databinding.DataBindingUtil;
import com.example.quizapp.models.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TextView questionText;
    private int questionIndex = 0;

    private Question[] questionBank = new Question[] {
        new Question(R.string.capital_netherlands, true),
        new Question(R.string.capital_belgium, true),
        new Question(R.string.capital_germany, false),
        new Question(R.string.capital_spain, false),
        new Question(R.string.capital_usa, false),
        new Question(R.string.capital_estonia, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.questionTextView.setText(questionBank[questionIndex].getAnswerResId());

        binding.falseButton.setOnClickListener(view -> updateQuestionIndex());
        binding.falseButton.setOnClickListener(view -> checkQuestionAnswered(false));
        binding.trueButton.setOnClickListener(view -> updateQuestionIndex());
        binding.trueButton.setOnClickListener(view -> checkQuestionAnswered(true));

        binding.prevButton.setOnClickListener(view -> prevQuestion());
        binding.nextButton.setOnClickListener(view -> nextQuestion());
    }

    private void updateQuestionIndex() {
        questionIndex = (questionIndex + 1) % questionBank.length;
        binding.questionTextView.setText(questionBank[questionIndex].getAnswerResId());
    }

    private void prevQuestion() {
        questionIndex = (questionIndex - 1) % questionBank.length;
        binding.questionTextView.setText(questionBank[questionIndex].getAnswerResId());
    }

    private void nextQuestion() {
        questionIndex = (questionIndex + 1) % questionBank.length;
        binding.questionTextView.setText(questionBank[questionIndex].getAnswerResId());
    }

    private void checkQuestionAnswered(boolean chosenAnswer) {
        boolean answerIsCorrect = questionBank[questionIndex].getAnswerTrue();
        int messageId;
        if(answerIsCorrect == chosenAnswer) {
            messageId = R.string.correct_answer;
        } else {
            messageId = R.string.wrong_answer;
        }
        Snackbar.make(binding.imageView, messageId, Snackbar.LENGTH_SHORT).show();
    }
}