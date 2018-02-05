package com.example.android.cthulhumythosquiz;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class RadioButtonActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        /**
         * Get variables for the different views.
         */
        TextView questionTextView = (TextView) findViewById(R.id.pose_question);
        ImageView questionPicture = (ImageView) findViewById(R.id.flavor_image);
        Resources res = getResources();
        RadioButton answerOneRadioButton = (RadioButton) findViewById(R.id.answer_1_button);
        RadioButton answerTwoRadioButton = (RadioButton) findViewById(R.id.answer_2_button);
        RadioButton answerThreeRadioButton = (RadioButton) findViewById(R.id.answer_3_button);
        RadioButton answerFourRadioButton = (RadioButton) findViewById(R.id.answer_4_button);

        /**
         * Add and update score bar.
         */
        ProgressBar playerProgress = (ProgressBar) findViewById(R.id.score_bar);
        playerProgress.setProgress(MainActivity.score);


        /**
         * Update questions and picture.
         */
        if (MainActivity.questionsAsked == 2){
            questionTextView.setText(getString(R.string.question_2));

            //Update the picture
            questionPicture.setImageDrawable(res.getDrawable(R.drawable.shub_niggurath_by_dominique_signoret));

            //Update the answers.
            answerOneRadioButton.setText(getString(R.string.question_2_answer_1));
            answerTwoRadioButton.setText(getString(R.string.question_2_answer_2));
            answerThreeRadioButton.setText(getString(R.string.question_2_answer_3));
            answerFourRadioButton.setText(getString(R.string.question_2_answer_4));
        }

        if (MainActivity.questionsAsked == 3){
            questionTextView.setText(getString(R.string.question_3));

            //Update the picture
            questionPicture.setImageDrawable(res.getDrawable(R.drawable.migo_by_khaenna_suntzu));

            //Update the answers.
            answerOneRadioButton.setText(getString(R.string.question_3_answer_1));
            answerTwoRadioButton.setText(getString(R.string.question_3_answer_2));
            answerThreeRadioButton.setText(getString(R.string.question_3_answer_3));
            answerFourRadioButton.setText(getString(R.string.question_3_answer_4));
        }

    }

    /**
     * Prevent people from going back in the app.
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * This method is called when the "Answer"-Button is clicked in a RadioButton question.
     */

    public void giveAnswer(View view) {
        //Find out if Button One is clicked.
        RadioButton answerOneRadioButton = (RadioButton) findViewById(R.id.answer_1_button);
        boolean hasClickedButtonOne = answerOneRadioButton.isChecked();

        //Find out if Button Two is clicked.
        RadioButton answerTwoRadioButton = (RadioButton) findViewById(R.id.answer_2_button);
        boolean hasClickedButtonTwo = answerTwoRadioButton.isChecked();

        //Find out if Button Three is clicked.
        RadioButton answerThreeRadioButton = (RadioButton) findViewById(R.id.answer_3_button);
        boolean hasClickedButtonThree = answerThreeRadioButton.isChecked();

        //Find out if Button Four is clicked.
        RadioButton answerFourRadioButton = (RadioButton) findViewById(R.id.answer_4_button);
        boolean hasClickedButtonFour = answerFourRadioButton.isChecked();

        if (hasClickedButtonOne == false && hasClickedButtonTwo == false && hasClickedButtonThree == false && hasClickedButtonFour == false) {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_no_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            return;
        }

        evaluateAnswer(hasClickedButtonOne, hasClickedButtonTwo, hasClickedButtonThree, hasClickedButtonFour);

        Intent answerPage = new Intent(RadioButtonActivity.this, AnswerPageActivity.class);
        if (answerPage.resolveActivity(getPackageManager()) != null) {
            startActivity(answerPage);
        }

    }

    /**
     * Check if the answer is correct and raise the score accordingly.
     */

    private int evaluateAnswer(boolean hasClickedButtonOne, boolean hasClickedButtonTwo, boolean hasClickedButtonThree, boolean hasClickedButtonFour) {
        if (MainActivity.questionsAsked == 1) {
            if (hasClickedButtonOne == true || hasClickedButtonTwo == true){
                Toast.makeText(getApplicationContext(), getString(R.string.toast_wrong_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            }
            if (hasClickedButtonThree == true){
                Toast.makeText(getApplicationContext(), getString(R.string.toast_right_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
                MainActivity.score +=10;
            }
            if (hasClickedButtonFour == true) {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_fun_answer), Toast.LENGTH_SHORT).show();
                MainActivity.score += 10;
            }

        }

        if (MainActivity.questionsAsked == 2) {
            if (hasClickedButtonOne == true || hasClickedButtonTwo == true || hasClickedButtonFour ==true){
                Toast.makeText(getApplicationContext(), getString(R.string.toast_wrong_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            }
            if (hasClickedButtonThree == true){
                Toast.makeText(getApplicationContext(), getString(R.string.toast_right_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
                MainActivity.score +=10;
            }
        }

        if (MainActivity.questionsAsked == 3) {
            if (hasClickedButtonTwo == true || hasClickedButtonThree == true || hasClickedButtonFour ==true){
                Toast.makeText(getApplicationContext(), getString(R.string.toast_wrong_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            }
            if (hasClickedButtonOne == true){
                Toast.makeText(getApplicationContext(), getString(R.string.toast_right_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
                MainActivity.score +=10;
            }
        }


        return MainActivity.score;
    }
}