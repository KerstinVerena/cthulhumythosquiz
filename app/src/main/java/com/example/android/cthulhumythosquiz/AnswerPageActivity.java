package com.example.android.cthulhumythosquiz;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AnswerPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_page);

        //Get variables for the different views.
        TextView questionTextView = (TextView) findViewById(R.id.pose_question);
        ImageView questionPicture = (ImageView) findViewById(R.id.flavor_image);
        Resources res = getResources();
        TextView explanationTextView = (TextView) findViewById(R.id.explanation_text);

        /**
         * Add and update score bar.
         */
        ProgressBar playerProgress = (ProgressBar) findViewById(R.id.score_bar);
        playerProgress.setProgress(MainActivity.score);

        /**
         * Update answer and picture.
         */
        if (MainActivity.questionsAsked == 2) {
            //Update the question
            questionTextView.setText(getString(R.string.question_2));

            //Update the picture
            questionPicture.setImageDrawable(res.getDrawable(R.drawable.shub_niggurath_by_dominique_signoret));

            //Update the explanation
            explanationTextView.setText(getString(R.string.explanation_2));

        }

        if (MainActivity.questionsAsked == 3) {
            //Update the question
            questionTextView.setText(getString(R.string.question_3));

            //Update the picture
            questionPicture.setImageDrawable(res.getDrawable(R.drawable.migo_by_khaenna_suntzu));

            //Update the explanation
            explanationTextView.setText(getString(R.string.explanation_3));

        }
        if (MainActivity.questionsAsked == 4) {
            //Update the question
            questionTextView.setText(getString(R.string.question_4));

            //Update the picture
            questionPicture.setImageDrawable(res.getDrawable(R.drawable.shoggoth_by_nottsuo));

            //Update the explanation
            explanationTextView.setText(getString(R.string.explanation_4));

        }

        if (MainActivity.questionsAsked == 5) {
            //Update the question
            questionTextView.setText(getString(R.string.question_5));

            //Update the picture
            questionPicture.setImageDrawable(res.getDrawable(R.drawable.old_books_by_jose_antonio_alba));

            //Update the explanation
            explanationTextView.setText(getString(R.string.explanation_5));

        }

        if (MainActivity.questionsAsked == 6) {
            //Update the question
            questionTextView.setText(getString(R.string.question_6));

            //Update the picture
            questionPicture.setImageDrawable(res.getDrawable(R.drawable.the_madness_from_the_sea_by_sofyan_syarief));

            //Update the explanation
            explanationTextView.setText(getString(R.string.explanation_6));

        }
    }

    /**
     * Prevent people from going back in the app.
     */
    @Override
    public void onBackPressed() {
    }

    /***
     *  Raise questionsAsked by one and call the next Activity according to the nature of the next question.
     */
    public void continueQuiz(View view) {

        MainActivity.questionsAsked += 1;

        if (MainActivity.questionsAsked == 2 || MainActivity.questionsAsked == 3) {

            Intent radioButtonQuestion = new Intent(AnswerPageActivity.this, RadioButtonActivity.class);

            if (radioButtonQuestion.resolveActivity(getPackageManager()) != null) {
                startActivity(radioButtonQuestion);
            }

        }
        if (MainActivity.questionsAsked == 4 || MainActivity.questionsAsked == 5) {

            Intent checkBoxQuestion = new Intent(AnswerPageActivity.this, CheckBoxActivity.class);

            if (checkBoxQuestion.resolveActivity(getPackageManager()) != null) {
                startActivity(checkBoxQuestion);
            }

        }

        if (MainActivity.questionsAsked == 6) {

            Intent editTextQuestion = new Intent(AnswerPageActivity.this, EditTextActivity.class);

            if (editTextQuestion.resolveActivity(getPackageManager()) != null) {
                startActivity(editTextQuestion);
            }

        }

        if (MainActivity.questionsAsked == 7) {

            Intent evaluationIntent = new Intent(AnswerPageActivity.this, EvaluationActivity.class);

            if (evaluationIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(evaluationIntent);
            }

        }
    }
}
