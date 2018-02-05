package com.example.android.cthulhumythosquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /**
     * This app is a quiz about the Cthulhu Mythos.
     */

    public static String playerName;
    public static int questionsAsked = 0;
    public static int score;
    public static String statePlayerName;
    public static String stateScore;
    public static String stateQuestionsAsked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            playerName = savedInstanceState.getString(statePlayerName);
            score = savedInstanceState.getInt(stateScore);
            questionsAsked = savedInstanceState.getInt(stateQuestionsAsked);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(statePlayerName, playerName);
        outState.putInt(stateScore, score);
        outState.putInt(stateQuestionsAsked, questionsAsked);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        playerName = savedInstanceState.getString(statePlayerName);
        score = savedInstanceState.getInt(stateScore);
        questionsAsked = savedInstanceState.getInt(stateQuestionsAsked);

    }


    /**
     * This method is called when the "Proof It"-Button at the start is clicked.
     */
    public void startQuiz (View view){

        //Find out the player's name.

        EditText addPlayerName = (EditText) findViewById(R.id.player_name);
        playerName = addPlayerName.getText().toString();

        questionsAsked = 1;
        score = 1;

        if (playerName.equals("")) {
            playerName = getString(R.string.default_name);
        }
        Intent radioButtonQuestion = new Intent(MainActivity.this, RadioButtonActivity.class);
        if (radioButtonQuestion.resolveActivity(getPackageManager()) != null) {
            startActivity(radioButtonQuestion);
        }
    }

}
