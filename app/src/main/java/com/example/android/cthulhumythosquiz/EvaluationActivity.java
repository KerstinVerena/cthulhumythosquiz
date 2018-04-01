package com.example.android.cthulhumythosquiz;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.ProgressBar;

public class EvaluationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

/**
 * Give the player their final score and a message.
 */
        TextView evaluationTextView = (TextView) findViewById(R.id.cthulhu_mythos);
        String playerScore = getString(R.string.final_score,Integer.toString(MainActivity.score));
        evaluationTextView.setText(playerScore);

        TextView evaluationMessageTextView = (TextView) findViewById(R.id.evaluation_message);
           String evaluationMessage;

        if (MainActivity.score < 35){
            evaluationMessage = getString(R.string.low_rating);
        }
        else if (MainActivity.score > 75) {
            evaluationMessage = getString(R.string.good_rating, MainActivity.playerName);
        }
        else {
            evaluationMessage = getString(R.string.average_rating, MainActivity.playerName);
        }
        evaluationMessageTextView.setText(evaluationMessage);

        /**
         * Add and update progress bar. More information about implementing a progress bar can be found on: https://developer.android.com/reference/android/widget/ProgressBar.html
         */
        ProgressBar playerProgress = findViewById(R.id.progress_bar);
        int currentProgress = MainActivity.questionsAsked-1;
        currentProgress = currentProgress*10;
        playerProgress.setProgress(currentProgress);
    }

    /**
     * Rests the game for a new round.
     */
    public void playAgain(View view) {
        // Sets saved instances to null
        MainActivity.playerName = "";
        MainActivity.questionsAsked = 0;
        MainActivity.score = 0;

        //  More information about how to start a new activity with a button: https://www.youtube.com/watch?v=n21mXO1ASJM
        Intent playAgain = new Intent(EvaluationActivity.this, MainActivity.class);
        if (playAgain.resolveActivity(getPackageManager()) != null) {
            startActivity(playAgain);
        }
    }

    /**
     * Shows credit page.
     * More information about how to start a new activity with a button: https://www.youtube.com/watch?v=n21mXO1ASJM
     */
    public void showCredits(View view) {
        Intent credits = new Intent(EvaluationActivity.this, CreditsActivity.class);
        if (credits.resolveActivity(getPackageManager()) != null) {
            startActivity(credits);
        }
    }

    /**
     * Prevent people from going back in the app. Code used from: https://stackoverflow.com/questions/8631095/android-preventing-going-back-to-the-previous-activity
     */
    @Override
    public void onBackPressed() {
    }

}
