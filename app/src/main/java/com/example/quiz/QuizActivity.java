package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuizActivity extends AppCompatActivity {

  private TextView questionsCount;
  private TextView questionText;

  private AppCompatButton option1, option2, option3, option4;
  private AppCompatButton nextBtn;

  private List<QuestionsList> questionsList;

  private int currentQuestionPosition = 0;
  private String userAnswer = "";

  private final FirebaseDatabase db = FirebaseDatabase.getInstance();
  private final DatabaseReference dbRef = db.getReference();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.quiz_activity);

    final ImageView backBtn = findViewById(R.id.backBtn);
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

    backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        goBack();
      }
    });

    option1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (userAnswer.isEmpty()) {
          userAnswer = option1.getText().toString();
          option1.setBackgroundResource(R.drawable.incorrect_answer_background);

          revealAnswer();
          questionsList.get(currentQuestionPosition).setUserAnswer(userAnswer);
        }
      }
    });

    option2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (userAnswer.isEmpty()) {
          userAnswer = option2.getText().toString();
          option2.setBackgroundResource(R.drawable.incorrect_answer_background);

          revealAnswer();
          questionsList.get(currentQuestionPosition).setUserAnswer(userAnswer);
        }
      }
    });

    option3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (userAnswer.isEmpty()) {
          userAnswer = option3.getText().toString();
          option3.setBackgroundResource(R.drawable.incorrect_answer_background);

          revealAnswer();
          questionsList.get(currentQuestionPosition).setUserAnswer(userAnswer);
        }
      }
    });

    option4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (userAnswer.isEmpty()) {
          userAnswer = option4.getText().toString();
          option4.setBackgroundResource(R.drawable.incorrect_answer_background);

          revealAnswer();
          questionsList.get(currentQuestionPosition).setUserAnswer(userAnswer);
        }
      }
    });

    nextBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (userAnswer.isEmpty()) {
          Toast.makeText(QuizActivity.this, "Выберите ответ", Toast.LENGTH_SHORT).show();
        } else {
          goNextQuestion();
        }
      }
    });

    dbRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        questionsList = loadQuestions(dataSnapshot, getSelectedQuiz);

        questionsCount.setText((currentQuestionPosition + 1) + "/" + questionsList.size());
        questionText.setText(questionsList.get(0).getQuestion());
        option1.setText(questionsList.get(0).getOption1());
        option2.setText(questionsList.get(0).getOption2());
        option3.setText(questionsList.get(0).getOption3());
        option4.setText(questionsList.get(0).getOption4());
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) { }
    });

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
    startActivity(new Intent(QuizActivity.this, MainActivity.class));
    finish();
  }

  @Override
  public void onBackPressed() {
    goBack();
  }

  private void revealAnswer() {
    final String answer = questionsList.get(currentQuestionPosition).getAnswer();

    if (option1.getText().toString().equals(answer)) {
      option1.setBackgroundResource(R.drawable.correct_answer_background);
    } else if (option2.getText().toString().equals(answer)) {
      option2.setBackgroundResource(R.drawable.correct_answer_background);
    } else if (option3.getText().toString().equals(answer)) {
      option3.setBackgroundResource(R.drawable.correct_answer_background);
    } else if (option4.getText().toString().equals(answer)) {
      option4.setBackgroundResource(R.drawable.correct_answer_background);
    }
  }

  private void goNextQuestion() {
    currentQuestionPosition++;

    if ((currentQuestionPosition + 1) == questionsList.size()) {
      nextBtn.setText("Готово");
    }

    if (currentQuestionPosition < questionsList.size()) {
      userAnswer = "";
      option1.setBackgroundResource(R.drawable.white_rounded_background);
      option2.setBackgroundResource(R.drawable.white_rounded_background);
      option3.setBackgroundResource(R.drawable.white_rounded_background);
      option4.setBackgroundResource(R.drawable.white_rounded_background);

      questionsCount.setText((currentQuestionPosition + 1) + "/" + questionsList.size());
      questionText.setText(questionsList.get(currentQuestionPosition).getQuestion());
      option1.setText(questionsList.get(currentQuestionPosition).getOption1());
      option2.setText(questionsList.get(currentQuestionPosition).getOption2());
      option3.setText(questionsList.get(currentQuestionPosition).getOption3());
      option4.setText(questionsList.get(currentQuestionPosition).getOption4());
    } else {
      Intent intent = new Intent(QuizActivity.this, QuizResults.class);
      intent.putExtra("correctAnswers", getCorrectAnswers());
      intent.putExtra("incorrectAnswers", getIncorrectAnswers());

      startActivity(intent);
      finish();
    }
  }

  public List<QuestionsList> loadQuestions(DataSnapshot dataSnapshot, String quiz) {
    final List<QuestionsList> quizQuestionsList = new ArrayList<>();
    DataSnapshot quizData = dataSnapshot.child("quizzes").child(quiz);

    for (DataSnapshot ds : quizData.getChildren()) {
      final QuestionsList questionsList = new QuestionsList(
        Objects.requireNonNull(ds.child("text").getValue()).toString(),
        Objects.requireNonNull(ds.child("option1").getValue()).toString(),
        Objects.requireNonNull(ds.child("option2").getValue()).toString(),
        Objects.requireNonNull(ds.child("option3").getValue()).toString(),
        Objects.requireNonNull(ds.child("option4").getValue()).toString(),
        Objects.requireNonNull(ds.child("answer").getValue()).toString(),
        ""
      );

      quizQuestionsList.add(questionsList);
    }

    return quizQuestionsList;
  }
}