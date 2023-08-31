package com.example.parsedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String url = "https://jsonplaceholder.typicode.com/todos";
    RequestQueue queue;
    TextView showTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        showTitle = findViewById(R.id.titleText);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                        try {
                            showTitle.setText(response.getString("title"));
                            System.out.println("Object: " + response.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    Log.d("JSON: ", response.toString());
                },
                error -> {
                    Log.d("JSON: ", error.toString());
                }
        );
        queue.add(jsonObjectRequest);
        // callAPi();
    }

    private void callAPi() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, (Response.Listener) response -> {
            System.out.println("RESPONSE: " + response);
        },
        error -> {
            System.out.println("ERROR: " + error);
        });
        queue.add(stringRequest);
    }
}