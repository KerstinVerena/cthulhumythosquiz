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
         * Add and update progress bar. More information about implementing a progress bar can be found on: https://developer.android.com/reference/android/widget/ProgressBar.html
         */
        ProgressBar playerProgress = findViewById(R.id.progress_bar);
        int currentProgress = MainActivity.questionsAsked-1;
        currentProgress = currentProgress*10;
        playerProgress.setProgress(currentProgress);
    }

    /**
     * Prevent people from going back in the app. Code used from: https://stackoverflow.com/questions/8631095/android-preventing-going-back-to-the-previous-activity
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * This method is called when the "Answer"-Button is clicked in a EditText question.
     */

    public void giveAnswer(View view) {
        //Find out what is typed in the EditText field.
        EditText enterPoemEnd = findViewById(R.id.poem_end);
        String poemEnd = enterPoemEnd.getText().toString().trim();

        // More information about checking if a string is empty: https://stackoverflow.com/questions/2601978/how-to-check-if-my-string-is-equal-to-null
        if (poemEnd.equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_no_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            return;
        }

        evaluateAnswer(poemEnd);

        //  More information about how to start a new activity with a button: https://www.youtube.com/watch?v=n21mXO1ASJM

        Intent answerPage = new Intent(EditTextActivity.this, AnswerPageActivity.class);
        if (answerPage.resolveActivity(getPackageManager()) != null) {
            startActivity(answerPage);
        }

    }

    /**
     * Check if the answer is correct and raise the score accordingly.
     */

    private int evaluateAnswer(String poemEnd) {

        if (poemEnd.equals(getString(R.string.poem_answer_1)) || poemEnd.equals(getString(R.string.poem_answer_2)) || poemEnd.equals(getString(R.string.poem_answer_3))) {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_right_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            MainActivity.score += 10;
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_wrong_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
        }
        MainActivity.score -=1;
        return MainActivity.score;
    }
}
