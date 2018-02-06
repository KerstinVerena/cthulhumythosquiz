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

public class CheckBoxActivity extends AppCompatActivity {

    public static CheckBox checkBoxOne;
    public static CheckBox checkBoxTwo;
    public static CheckBox checkBoxThree;
    public static CheckBox checkBoxFour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        /**
         * Get variables for the different views.
         */

        TextView questionTextView = (TextView) findViewById(R.id.pose_question);
        ImageView questionPicture = (ImageView) findViewById(R.id.flavor_image);
        Resources res = getResources();
        checkBoxOne = (CheckBox) findViewById(R.id.checkbox_1);
        checkBoxTwo = (CheckBox) findViewById(R.id.checkbox_2);
        checkBoxThree = (CheckBox) findViewById(R.id.checkbox_3);
        checkBoxFour = (CheckBox) findViewById(R.id.checkbox_4);

        /**
         * Add and update score bar.
         */
        ProgressBar playerProgress = (ProgressBar) findViewById(R.id.score_bar);
        playerProgress.setProgress(MainActivity.score);

        /**
         * Update questions and picture.
         */

        if (MainActivity.questionsAsked == 5) {
            questionTextView.setText(getString(R.string.question_5));

            //Update the picture
            questionPicture.setImageDrawable(res.getDrawable(R.drawable.old_books_by_jose_antonio_alba));

            //Update the answers.
            checkBoxOne.setText(getString(R.string.question_5_answer_1));
            checkBoxTwo.setText(getString(R.string.question_5_answer_2));
            checkBoxThree.setText(getString(R.string.question_5_answer_3));
            checkBoxFour.setText(getString(R.string.question_5_answer_4));
        }
    }

    /**
     * This method is called when the "Answer"-Button is clicked in a CheckBoxQuestion.
     */

    public void giveAnswer(View view) {
        //Find out if Checkbox One is checked.
        boolean hasCheckedBoxOne = checkBoxOne.isChecked();

        //Find out if Checkbox Two is checked.
       boolean hasCheckedBoxTwo = checkBoxTwo.isChecked();

        //Find out if Checkbox Three is checked.
        boolean hasCheckedBoxThree = checkBoxThree.isChecked();

        //Find out if Checkbox Three is checked.
       boolean hasCheckedBoxFour = checkBoxFour.isChecked();


        if (hasCheckedBoxOne == false && hasCheckedBoxTwo == false && hasCheckedBoxThree == false && hasCheckedBoxFour == false) {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_no_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            return;
        }

        evaluateAnswer(hasCheckedBoxOne, hasCheckedBoxTwo, hasCheckedBoxThree, hasCheckedBoxFour);

        Intent answerPage = new Intent(CheckBoxActivity.this, AnswerPageActivity.class);
        if (answerPage.resolveActivity(getPackageManager()) != null) {
            startActivity(answerPage);
        }

    }

    /**
     * Prevent people from going back in the app.
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * Check if the answer is correct and raise the score accordingly.
     */

    private int evaluateAnswer(boolean hasCheckedBoxOne, boolean hasCheckedBoxTwo, boolean hasCheckedBoxThree, boolean hasCheckedBoxFour) {
        if (MainActivity.questionsAsked == 4) {
            if (hasCheckedBoxOne == true && hasCheckedBoxThree == true && hasCheckedBoxTwo ==false && hasCheckedBoxFour == false) {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_right_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
                MainActivity.score += 20;
            } else if (hasCheckedBoxOne == false && hasCheckedBoxThree == false) {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_wrong_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_part_answer), Toast.LENGTH_SHORT).show();
                MainActivity.score += 10;
            }
        }

        if (MainActivity.questionsAsked == 5) {
            if (hasCheckedBoxOne == true && hasCheckedBoxTwo == true && hasCheckedBoxThree == true && hasCheckedBoxFour == true) {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_right_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
                MainActivity.score += 20;
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_part_answer), Toast.LENGTH_SHORT).show();
                MainActivity.score += 10;
            }
        }
        return MainActivity.score;
    }
}
