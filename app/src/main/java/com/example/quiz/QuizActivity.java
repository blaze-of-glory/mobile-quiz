package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.quiz_activity);

    final ImageView backBtn = findViewById(R.id.backBtn);
    final TextView timer = findViewById(R.id.timer);
    final TextView quizName = findViewById(R.id.quizName);

    final String getSelectedQuiz = getIntent().getStringExtra("selectedQuiz");

    quizName.setText(getSelectedQuiz);
  }
}