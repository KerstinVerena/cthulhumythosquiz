package com.example.android.cthulhumythosquiz;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
    }

    /**
     * Prevent people from going back in the app.
     */
    @Override
    public void onBackPressed() {
    }

}
