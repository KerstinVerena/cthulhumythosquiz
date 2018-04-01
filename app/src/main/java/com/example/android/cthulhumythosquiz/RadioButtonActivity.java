package com.example.android.cthulhumythosquiz;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class RadioButtonActivity extends AppCompatActivity {

    public static RadioButton answerOneRadioButton;
    public static RadioButton answerTwoRadioButton;
    public static RadioButton answerThreeRadioButton;
    public static RadioButton answerFourRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        //Get variables for the different views.
        TextView questionTextView = findViewById(R.id.pose_question);
        ImageView questionPicture = findViewById(R.id.flavor_image);
        Resources res = getResources();
        answerOneRadioButton = findViewById(R.id.answer_1_button);
        answerTwoRadioButton = findViewById(R.id.answer_2_button);
        answerThreeRadioButton = findViewById(R.id.answer_3_button);
        answerFourRadioButton = findViewById(R.id.answer_4_button);


        //Add and update score bar. More information about implementing a progress bar can be found on: https://developer.android.com/reference/android/widget/ProgressBar.html
        ProgressBar playerProgress = findViewById(R.id.progress_bar);
        int currentProgress = MainActivity.questionsAsked-1;
        currentProgress = currentProgress*10;
        playerProgress.setProgress(currentProgress);


        /**
         * Update questions and picture.
         */
        if (MainActivity.questionsAsked == 2){
            questionTextView.setText(getString(R.string.question_2));

            //Update the picture and description: More information and code for using and updating content descriptions: https://www.deque.com/blog/android-imageviews-accessible-content-descriptions/
            questionPicture.setImageDrawable(res.getDrawable(R.drawable.shub_niggurath_by_dominique_signoret));
            questionPicture.setContentDescription(getResources().getString(R.string.picture_3));

            //Update the answers.
            answerOneRadioButton.setText(getString(R.string.question_2_answer_1));
            answerTwoRadioButton.setText(getString(R.string.question_2_answer_2));
            answerThreeRadioButton.setText(getString(R.string.question_2_answer_3));
            answerFourRadioButton.setText(getString(R.string.question_2_answer_4));
        }

        if (MainActivity.questionsAsked == 3){
            questionTextView.setText(getString(R.string.question_3));

            //Update the picture and content description
            questionPicture.setImageDrawable(res.getDrawable(R.drawable.migo_by_khaenna_suntzu));
            questionPicture.setContentDescription(getResources().getString(R.string.picture_4));

            //Update the answers.
            answerOneRadioButton.setText(getString(R.string.question_3_answer_1));
            answerTwoRadioButton.setText(getString(R.string.question_3_answer_2));
            answerThreeRadioButton.setText(getString(R.string.question_3_answer_3));
            answerFourRadioButton.setText(getString(R.string.question_3_answer_4));
        }

    }

    /**
     * Prevent people from going back in the app. Code used from: https://stackoverflow.com/questions/8631095/android-preventing-going-back-to-the-previous-activity
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * This method is called when the "Answer"-Button is clicked in a RadioButton question.
     */

    public void giveAnswer(View view) {
        //Find out if Button One is clicked.
        boolean hasClickedButtonOne = answerOneRadioButton.isChecked();

        //Find out if Button Two is clicked.
        boolean hasClickedButtonTwo = answerTwoRadioButton.isChecked();

        //Find out if Button Three is clicked.
        boolean hasClickedButtonThree = answerThreeRadioButton.isChecked();

        //Find out if Button Four is clicked.
        boolean hasClickedButtonFour = answerFourRadioButton.isChecked();

        if (!hasClickedButtonOne && !hasClickedButtonTwo && !hasClickedButtonThree && !hasClickedButtonFour) {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_no_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            return;
        }

        evaluateAnswer(hasClickedButtonOne, hasClickedButtonTwo, hasClickedButtonThree, hasClickedButtonFour);

        //  More information about how to start a new activity with a button: https://www.youtube.com/watch?v=n21mXO1ASJM
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
            if (hasClickedButtonOne || hasClickedButtonTwo){
                Toast.makeText(getApplicationContext(), getString(R.string.toast_wrong_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            }
            if (hasClickedButtonThree){
                Toast.makeText(getApplicationContext(), getString(R.string.toast_right_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
                MainActivity.score +=10;
            }
            if (hasClickedButtonFour) {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_fun_answer), Toast.LENGTH_SHORT).show();
                MainActivity.score += 10;
            }

        }

        if (MainActivity.questionsAsked == 2) {
            if (hasClickedButtonOne || hasClickedButtonTwo || hasClickedButtonFour){
                Toast.makeText(getApplicationContext(), getString(R.string.toast_wrong_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            }
            if (hasClickedButtonThree){
                Toast.makeText(getApplicationContext(), getString(R.string.toast_right_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
                MainActivity.score +=10;
            }
        }

        if (MainActivity.questionsAsked == 3) {
            if (hasClickedButtonTwo || hasClickedButtonThree || hasClickedButtonFour){
                Toast.makeText(getApplicationContext(), getString(R.string.toast_wrong_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            }
            if (hasClickedButtonOne){
                Toast.makeText(getApplicationContext(), getString(R.string.toast_right_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
                MainActivity.score +=10;
            }
        }

        return MainActivity.score;
    }
}