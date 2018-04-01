package com.example.android.cthulhumythosquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }

    public void backToEvaluation(View view) {
        //  More information about how to start a new activity with a button: https://www.youtube.com/watch?v=n21mXO1ASJM
        Intent credits = new Intent(CreditsActivity.this, EvaluationActivity.class);
        if (credits.resolveActivity(getPackageManager()) != null) {
            startActivity(credits);
        }
    }
}
