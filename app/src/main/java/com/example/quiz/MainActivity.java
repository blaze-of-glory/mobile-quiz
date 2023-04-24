package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private String selectedQuiz = "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final LinearLayout flagsQuiz = findViewById(R.id.flagsQuiz);
    final LinearLayout capitalsQuiz = findViewById(R.id.capitalsQuiz);
    final LinearLayout historyQuiz = findViewById(R.id.historyQuiz);
    final LinearLayout personsQuiz = findViewById(R.id.personsQuiz);

    final Button startQuizBtn = findViewById(R.id.startQuizBtn);

    flagsQuiz.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectedQuiz = "flags";
        flagsQuiz.setBackgroundResource(R.drawable.selected_background);
        capitalsQuiz.setBackgroundResource(R.drawable.white_rounded_background);
        historyQuiz.setBackgroundResource(R.drawable.white_rounded_background);
        personsQuiz.setBackgroundResource(R.drawable.white_rounded_background);
      }
    });

    capitalsQuiz.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectedQuiz = "capitals";
        capitalsQuiz.setBackgroundResource(R.drawable.selected_background);
        flagsQuiz.setBackgroundResource(R.drawable.white_rounded_background);
        historyQuiz.setBackgroundResource(R.drawable.white_rounded_background);
        personsQuiz.setBackgroundResource(R.drawable.white_rounded_background);
      }
    });

    historyQuiz.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectedQuiz = "history";
        historyQuiz.setBackgroundResource(R.drawable.selected_background);
        capitalsQuiz.setBackgroundResource(R.drawable.white_rounded_background);
        flagsQuiz.setBackgroundResource(R.drawable.white_rounded_background);
        personsQuiz.setBackgroundResource(R.drawable.white_rounded_background);
      }
    });

    personsQuiz.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectedQuiz = "persons";
        personsQuiz.setBackgroundResource(R.drawable.selected_background);
        flagsQuiz.setBackgroundResource(R.drawable.white_rounded_background);
        capitalsQuiz.setBackgroundResource(R.drawable.white_rounded_background);
        historyQuiz.setBackgroundResource(R.drawable.white_rounded_background);
      }
    });

    startQuizBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (selectedQuiz.isEmpty()) {
          Toast.makeText(MainActivity.this, "Выбирите викторину", Toast.LENGTH_SHORT).show();
        } else {
          Intent intent = new Intent(MainActivity.this, QuizActivity.class);
          startActivity(intent);
          finish();
        }
      }
    });
  }
}