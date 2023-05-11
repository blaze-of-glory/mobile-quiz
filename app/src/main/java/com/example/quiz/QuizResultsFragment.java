package com.example.quiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class QuizResultsFragment extends Fragment {

  private static final String CORRECT_ANSWERS = "correctAnswers";
  private static final String INCORRECT_ANSWERS = "incorrectAnswers";

  EditText nameInput;

  ArrayList<Score> scoreList = new ArrayList<>();

  private String correctAnswersAmount;
  private String incorrectAnswersAmount;

  private final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      correctAnswersAmount = getArguments().getString(CORRECT_ANSWERS);
      incorrectAnswersAmount = getArguments().getString(INCORRECT_ANSWERS);
    }
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    final AppCompatButton backToMenuBtn = view.findViewById(R.id.backToMenuBtn);

    final TextView correctAnswers = view.findViewById(R.id.correctAnswersAmount);
    final TextView incorrectAnswers = view.findViewById(R.id.incorrectAnswersAmount);

    nameInput = view.findViewById(R.id.nameInput);

    correctAnswers.setText("Правильнных ответов: " + correctAnswersAmount);
    incorrectAnswers.setText("Неправильнных ответов: " + incorrectAnswersAmount);

    backToMenuBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        saveResult();
      }
    });
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_quiz_results, container, false);
  }

  public void saveResult() {
    final String name = String.valueOf(nameInput.getText());
    if (name.equals(getString(R.string.name_placeholder)) || name.isEmpty()) {
      Toast.makeText(getContext(), "Введите имя", Toast.LENGTH_SHORT).show();
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
    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
    transaction.replace(R.id.fragment_container_view, MenuFragment.class, null);
    transaction.commit();
  }
}