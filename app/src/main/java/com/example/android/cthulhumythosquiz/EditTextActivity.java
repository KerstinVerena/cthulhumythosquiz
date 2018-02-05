package com.example.android.cthulhumythosquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class EditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        /**
         * Add and update score bar.
         */
        ProgressBar playerProgress = (ProgressBar) findViewById(R.id.score_bar);
        playerProgress.setProgress(MainActivity.score);
    }

    /**
     * Prevent people from going back in the app.
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * This method is called when the "Answer"-Button is clicked in a EditText question.
     */

    public void giveAnswer(View view) {
        //Find out what is typed in the EditText field.
        EditText enterPoemEnd = (EditText) findViewById(R.id.poem_end);
        String poemEnd = enterPoemEnd.getText().toString();


        if (poemEnd.equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_no_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            return;
        }

        evaluateAnswer(poemEnd);

        Intent answerPage = new Intent(EditTextActivity.this, AnswerPageActivity.class);
        if (answerPage.resolveActivity(getPackageManager()) != null) {
            startActivity(answerPage);
        }

    }

    /**
     * Check if the answer is correct and raise the score accordingly.
     */

    private int evaluateAnswer(String poemEnd) {

        if (poemEnd.equals("even death may die!") || poemEnd.equals("even death may die.") || poemEnd.equals("even death may die")) {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_right_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            MainActivity.score += 30;
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_wrong_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
        }
        MainActivity.score -=1;
        return MainActivity.score;
    }
}
