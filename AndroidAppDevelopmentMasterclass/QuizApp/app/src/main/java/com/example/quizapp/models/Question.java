package com.example.quizapp.models;

public class Question {
    private int answerResId;
    private boolean answerTrue;

    public Question(int answerResId, boolean answerTrue) {
        this.answerResId = answerResId;
        this.answerTrue = answerTrue;
    }

    public void setAnswerResId(int newAnswerResId) {
        this.answerResId = newAnswerResId;
    }

    public int getAnswerResId() {
        return answerResId;
    }

    public void setAnswerTrue(boolean newAnswerTrue) {
        this.answerTrue = newAnswerTrue;
    }

    public boolean getAnswerTrue() {
        return answerTrue;
    }
}
