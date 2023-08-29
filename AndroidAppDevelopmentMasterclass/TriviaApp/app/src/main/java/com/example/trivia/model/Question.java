package com.example.trivia.model;

public class Question {
    private String askedQuestion;
    private Boolean answerTrue;

    public Question() {
    }

    public Question(String askedQuestion, Boolean answerTrue) {
        this.askedQuestion = askedQuestion;
        this.answerTrue = answerTrue;
    }

    public String getAskedQuestion() {
        return askedQuestion;
    }

    public void setAskedQuestion(String question) {
        this.askedQuestion = askedQuestion;
    }

    public Boolean getAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(Boolean answerTrue) {
        this.answerTrue = answerTrue;
    }

    @Override
    public String toString() {
        return "Question{" +
                "askedQuestion='" + askedQuestion + '\'' +
                ", answerTrue=" + answerTrue +
                '}';
    }
}
