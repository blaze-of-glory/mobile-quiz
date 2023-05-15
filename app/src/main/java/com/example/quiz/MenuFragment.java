package com.example.quiz;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MenuFragment extends Fragment {

  private static final String SELECTED_QUIZ = "selectedQuiz";

  private String selectedQuiz = "";

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    final LinearLayout topic1 = view.findViewById(R.id.flagsQuiz);
    final LinearLayout topic2 = view.findViewById(R.id.capitalsQuiz);
    final LinearLayout topic3 = view.findViewById(R.id.historyQuiz);
    final LinearLayout topic4 = view.findViewById(R.id.personsQuiz);

    final Button startQuizBtn = view.findViewById(R.id.startQuizBtn);
    final Button openScoreBoardBtn = view.findViewById(R.id.scoreQuizBtn);
    final Button exitQuizBtn = view.findViewById(R.id.exitQuizBtn);

    topic1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectedQuiz = "topic1";
        topic1.setBackgroundResource(R.drawable.selected_background);
        topic2.setBackgroundResource(R.drawable.white_rounded_background);
        topic3.setBackgroundResource(R.drawable.white_rounded_background);
        topic4.setBackgroundResource(R.drawable.white_rounded_background);
      }
    });

    topic2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectedQuiz = "topic2";
        topic2.setBackgroundResource(R.drawable.selected_background);
        topic1.setBackgroundResource(R.drawable.white_rounded_background);
        topic3.setBackgroundResource(R.drawable.white_rounded_background);
        topic4.setBackgroundResource(R.drawable.white_rounded_background);
      }
    });

    topic3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectedQuiz = "topic3";
        topic3.setBackgroundResource(R.drawable.selected_background);
        topic2.setBackgroundResource(R.drawable.white_rounded_background);
        topic1.setBackgroundResource(R.drawable.white_rounded_background);
        topic4.setBackgroundResource(R.drawable.white_rounded_background);
      }
    });

    topic4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectedQuiz = "topic4";
        topic4.setBackgroundResource(R.drawable.selected_background);
        topic1.setBackgroundResource(R.drawable.white_rounded_background);
        topic2.setBackgroundResource(R.drawable.white_rounded_background);
        topic3.setBackgroundResource(R.drawable.white_rounded_background);
      }
    });

    startQuizBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (selectedQuiz.isEmpty()) {
          Toast.makeText(getContext(), "Выбирите викторину", Toast.LENGTH_SHORT).show();
        } else {
          Bundle bundle = new Bundle();
          bundle.putString(SELECTED_QUIZ, selectedQuiz);

          switchFragment(R.id.fragment_container_view, QuizFragment.class, bundle);
        }
      }
    });

    openScoreBoardBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        switchFragment(R.id.fragment_container_view, ScoreBoardFragment.class, null);
      }
    });

    exitQuizBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getActivity().finish();
      }
    });
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_menu, container, false);
  }

  public void switchFragment(@IdRes int containerViewId, @NonNull Class<? extends androidx.fragment.app.Fragment> fragmentClass, @Nullable android.os.Bundle args) {
    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
    transaction.replace(containerViewId, fragmentClass, args);
    transaction.addToBackStack(null);
    transaction.commit();
  }
}