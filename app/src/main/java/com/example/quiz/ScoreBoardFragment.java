package com.example.quiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScoreBoardFragment extends Fragment {

  ListView scoreBoardList;

  private final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    scoreBoardList = view.findViewById(R.id.scoreList);
    final Button backToMenuBtn = view.findViewById(R.id.backToMenuBtn);

    backToMenuBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_view, MenuFragment.class, null);
        transaction.commit();
      }
    });

    dbRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        loadResults(dataSnapshot);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) { }
    });

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_score_board, container, false);
  }

  private void loadResults(DataSnapshot dataSnapshot) {
    DataSnapshot scores = dataSnapshot.child("score-board");
    ArrayList<String> scoreList = new ArrayList<>();

    for (DataSnapshot ds : scores.getChildren()) {
      scoreList.add(String.valueOf(ds.child("name").getValue()) + " : " + String.valueOf(ds.child("score").getValue()));
    }

    ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.list_template, scoreList);
    scoreBoardList.setAdapter(arrayAdapter);
  }
}