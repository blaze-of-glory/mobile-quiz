package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScoreBoard extends AppCompatActivity {

  private final FirebaseDatabase db = FirebaseDatabase.getInstance();
  private final DatabaseReference dbRef = db.getReference();

  ListView scoreBoardList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_score_board);

    scoreBoardList = findViewById(R.id.scoreList);

    dbRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        loadResults(dataSnapshot);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) { }
    });
  }

  private void loadResults(DataSnapshot dataSnapshot) {
    DataSnapshot scores = dataSnapshot.child("score-board");
    ArrayList<String> scoreList = new ArrayList<>();

    for (DataSnapshot ds : scores.child("score-board").getChildren()) {
      scoreList.add(String.valueOf(ds.child("name").getValue()) + " : " + String.valueOf(ds.child("score").getValue()));
    }

    ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.list_template, scoreList);
    scoreBoardList.setAdapter(arrayAdapter);
  }
}