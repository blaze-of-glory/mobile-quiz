package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

  private TextView questionsCount;
  private TextView questionText;

  private AppCompatButton option1, option2, option3, option4;
  private AppCompatButton nextBtn;

  private Timer quizTimer;

  private int seconds = 0;
  private int totalTimeInMinutes = 1;

  private final List<QuestionsList> questionsList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.quiz_activity);

    final ImageView backBtn = findViewById(R.id.backBtn);
    final TextView timer = findViewById(R.id.timer);
    final TextView quizName = findViewById(R.id.quizName);

    final String getSelectedQuiz = getIntent().getStringExtra("selectedQuiz");

    questionsCount = findViewById(R.id.questionsCount);
    questionText = findViewById(R.id.questionText);

    option1 = findViewById(R.id.option1);
    option2 = findViewById(R.id.option2);
    option3 = findViewById(R.id.option3);
    option4 = findViewById(R.id.option4);

    nextBtn = findViewById(R.id.nextBtn);

    quizName.setText(getSelectedQuiz);

    startTimer(timer);

    backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        goBack();
      }
    });
  }

  private void startTimer (TextView timerTextView) {

    quizTimer = new Timer();
    quizTimer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (seconds == 0 && totalTimeInMinutes == 0) {
          quizTimer.purge();
          quizTimer.cancel();

          Toast.makeText(QuizActivity.this, "Время вышло", Toast.LENGTH_LONG).show();

          Intent intent = new Intent(QuizActivity.this, QuizResults.class);
          intent.putExtra("correctAnswers", getCorrectAnswers());
          intent.putExtra("incorrectAnswers", getIncorrectAnswers());

          startActivity(intent);
          finish();
        } else if (seconds == 0) {
          totalTimeInMinutes--;
          seconds = 59;
        } else {
          seconds--;
        }

        runOnUiThread(new Runnable() {
          @Override
          public void run() {

            String finalMinutes = String.valueOf(totalTimeInMinutes);
            String finalSeconds = String.valueOf(seconds);

            if (finalMinutes.length() == 1) {
              finalMinutes = "0" + finalMinutes;
            }

            if (finalSeconds.length() == 1) {
              finalSeconds = "0" + finalSeconds;
            }

            timerTextView.setText(finalMinutes + ":" + finalSeconds);

          }
        });

      }
    }, 1000, 1000);

  }

  private int getCorrectAnswers () {

    int correctAnswers = 0;

    for (int i = 0; i < questionsList.size(); i++) {

      final String getUserAnswer = questionsList.get(i).getUserAnswer();
      final String getAnswer = questionsList.get(i).getAnswer();

      if (getUserAnswer.equals(getAnswer)) {
        correctAnswers++;
      }
    }

    return correctAnswers;
  }

  private int getIncorrectAnswers () {

    int incorrectAnswers = 0;

    for (int i = 0; i < questionsList.size(); i++) {

      final String getUserAnswer = questionsList.get(i).getUserAnswer();
      final String getAnswer = questionsList.get(i).getAnswer();

      if (!getUserAnswer.equals(getAnswer)) {
        incorrectAnswers++;
      }
    }

    return incorrectAnswers;
  }

  public void goBack() {
    quizTimer.purge();
    quizTimer.cancel();

    startActivity(new Intent(QuizActivity.this, MainActivity.class));
    finish();
  }

  @Override
  public void onBackPressed() {
    goBack();
  }
}