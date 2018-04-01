package com.example.android.cthulhumythosquiz;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

        //Get variables for the different views.

        TextView questionTextView = findViewById(R.id.pose_question);
        ImageView questionPicture = findViewById(R.id.flavor_image);
        Resources res = getResources();
        checkBoxOne = findViewById(R.id.checkbox_1);
        checkBoxTwo = findViewById(R.id.checkbox_2);
        checkBoxThree = findViewById(R.id.checkbox_3);
        checkBoxFour = findViewById(R.id.checkbox_4);


        /**
         * Add and update progress bar. More information about implementing a progress bar can be found on: https://developer.android.com/reference/android/widget/ProgressBar.html
         */
        ProgressBar playerProgress = findViewById(R.id.progress_bar);
        int currentProgress = MainActivity.questionsAsked-1;
        currentProgress = currentProgress*10;
        playerProgress.setProgress(currentProgress);

        //Update questions and picture.

        if (MainActivity.questionsAsked == 5) {
            questionTextView.setText(getString(R.string.question_5));

            //Update the picture and description: More information and code for using and updating content descriptions: https://www.deque.com/blog/android-imageviews-accessible-content-descriptions/
            questionPicture.setImageDrawable(res.getDrawable(R.drawable.old_books_by_jose_antonio_alba));
            questionPicture.setContentDescription(getResources().getString(R.string.picture_7));

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
       boolean hasCheckedBoxOne = checkBoxOne.isChecked();
       boolean hasCheckedBoxTwo = checkBoxTwo.isChecked();
       boolean hasCheckedBoxThree = checkBoxThree.isChecked();
       boolean hasCheckedBoxFour = checkBoxFour.isChecked();


        if (!hasCheckedBoxOne && !hasCheckedBoxTwo && !hasCheckedBoxThree && !hasCheckedBoxFour) {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_no_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            return;
        }

        evaluateAnswer(hasCheckedBoxOne, hasCheckedBoxTwo, hasCheckedBoxThree, hasCheckedBoxFour);

        //  More information about how to start a new activity with a button: https://www.youtube.com/watch?v=n21mXO1ASJM
        Intent answerPage = new Intent(CheckBoxActivity.this, AnswerPageActivity.class);
        if (answerPage.resolveActivity(getPackageManager()) != null) {
            startActivity(answerPage);
        }

    }

    /**
     * Prevent people from going back in the app. Code used from: https://stackoverflow.com/questions/8631095/android-preventing-going-back-to-the-previous-activity
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * Check if the answer is correct and raise the score accordingly.
     */

    private int evaluateAnswer(boolean hasCheckedBoxOne, boolean hasCheckedBoxTwo, boolean hasCheckedBoxThree, boolean hasCheckedBoxFour) {
        if (MainActivity.questionsAsked == 4) {
            if (hasCheckedBoxOne && hasCheckedBoxThree && !hasCheckedBoxTwo && !hasCheckedBoxFour) {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_right_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
                MainActivity.score += 20;
            } else if (!hasCheckedBoxOne && !hasCheckedBoxThree) {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_wrong_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_part_answer), Toast.LENGTH_SHORT).show();
                MainActivity.score += 10;
            }
        }

        if (MainActivity.questionsAsked == 5) {
            if (hasCheckedBoxOne && hasCheckedBoxTwo && hasCheckedBoxThree && hasCheckedBoxFour) {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_right_answer, MainActivity.playerName), Toast.LENGTH_SHORT).show();
                MainActivity.score += 40;
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_part_answer), Toast.LENGTH_SHORT).show();

                if (hasCheckedBoxOne) {
                    MainActivity.score += 10;
                }
                if (hasCheckedBoxTwo) {
                    MainActivity.score += 10;
                }
                if (hasCheckedBoxThree) {
                    MainActivity.score += 10;
                }
                if (hasCheckedBoxFour) {
                    MainActivity.score += 10;
                }
            }
        }
        return MainActivity.score;
    }
}
