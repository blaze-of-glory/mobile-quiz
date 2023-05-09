package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class QuizResults extends AppCompatActivity {

  EditText nameInput;

  ArrayList<Score> scoreList = new ArrayList<>();

  private int correctAnswersAmount;

  private final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quiz_results);

    final AppCompatButton backToMenuBtn = findViewById(R.id.backToMenuBtn);

    final TextView correctAnswers = findViewById(R.id.correctAnswersAmount);
    final TextView incorrectAnswers = findViewById(R.id.incorrectAnswersAmount);

    nameInput = findViewById(R.id.nameInput);

    correctAnswersAmount = getIntent().getIntExtra("correctAnswers", 0);
    final int incorrectAnswersAmount = getIntent().getIntExtra("incorrectAnswers", 0);

    correctAnswers.setText("Правильнных ответов: " + String.valueOf(correctAnswersAmount));
    incorrectAnswers.setText("Неправильнных ответов: " + String.valueOf(incorrectAnswersAmount));

    backToMenuBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        saveResult();
      }
    });
  }

  @Override
  public void onBackPressed() {
    saveResult();
  }

  public void saveResult() {
    final String name = String.valueOf(nameInput.getText());
    if (name.equals(getString(R.string.name_placeholder)) || name.isEmpty()) {
      Toast.makeText(QuizResults.this, "Введите имя", Toast.LENGTH_SHORT).show();
    } else {
      updateScoreBoard(new Score(String.valueOf(nameInput.getText()), String.valueOf(correctAnswersAmount)));
    }
  }

  private void updateScoreBoard(Score newScore) {
    dbRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
      @Override
      public void onComplete(@NonNull Task<DataSnapshot> task) {
        if (task.isSuccessful()) {
          DataSnapshot allScores = task.getResult().child("score-board");
          for (DataSnapshot score : allScores.getChildren()) {
            scoreList.add(new Score(String.valueOf(score.child("name").getValue()), String.valueOf(score.child("score").getValue())));
          }
          scoreList.add(newScore);

          dbRef.child("score-board").setValue(scoreList).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
              if (task.isSuccessful()) {
                backToMenu();
              }
            }
          });
        }
      }
    });
  }

  public void backToMenu () {
    startActivity(new Intent(QuizResults.this, MainActivity.class));
    finish();
  }
}