package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuizResults extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quiz_results);

    final AppCompatButton backToMenuBtn = findViewById(R.id.backToMenuBtn);
    final TextView correctAnswers = findViewById(R.id.correctAnswersAmount);
    final TextView incorrectAnswers = findViewById(R.id.incorrectAnswersAmount);

    final int correctAnswersAmount = getIntent().getIntExtra("correctAnswers", 0);
    final int incorrectAnswersAmount = getIntent().getIntExtra("incorrectAnswers", 0);

    correctAnswers.setText("Правильнных ответов: " + String.valueOf(correctAnswersAmount));
    incorrectAnswers.setText("Неправильнных ответов: " + String.valueOf(incorrectAnswersAmount));

    backToMenuBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(QuizResults.this, MainActivity.class));
        finish();
      }
    });
  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(QuizResults.this, MainActivity.class));
    finish();
  }
}