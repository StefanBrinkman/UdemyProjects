package com.example.trivia.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivia.controller.AppController;
import com.example.trivia.model.Question;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    String URL = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    ArrayList<Question> questionsArrayList = new ArrayList<>();

    public List<Question> getQuestions(final AnswerListAsyncResponse callback) {
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, URL, null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    Question currentQuestion = new Question(
                            response.getJSONArray(i).get(0).toString(),
                            response.getJSONArray(i).getBoolean(1)
                    );
                    questionsArrayList.add(currentQuestion);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if(callback != null) {
                callback.processFinished(questionsArrayList);
            }
        }, error -> {
            Log.d("JSON", "Something went wrong with: " + error);
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        return questionsArrayList;
    }
}
