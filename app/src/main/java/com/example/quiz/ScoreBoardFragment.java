package com.example.quiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScoreBoardFragment extends Fragment {

  private RecyclerView recyclerView;
  private RecyclerView.Adapter mAdapter;

  private final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    recyclerView = view.findViewById(R.id.score_recycler_view);

    recyclerView.setHasFixedSize(true);

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(layoutManager);

    ArrayList<String> myDataset = new ArrayList<>();
    mAdapter = new MyAdapter(myDataset);
    recyclerView.setAdapter(mAdapter);

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
      public void onCancelled(@NonNull DatabaseError error) {
      }
    });

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_score_board, container, false);
  }

  private void loadResults(DataSnapshot dataSnapshot) {
    DataSnapshot scores = dataSnapshot.child("score-board");
    ArrayList<String> scoreList = new ArrayList<>();

    for (DataSnapshot ds : scores.getChildren()) {
      scoreList.add(String.valueOf(ds.child("name").getValue()) + " : " + String.valueOf(ds.child("score").getValue()));
    }

    mAdapter = new MyAdapter(scoreList);
    recyclerView.setAdapter(mAdapter);
  }

  static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final ArrayList<String> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
      public TextView textView;
      public MyViewHolder(TextView v) {
        super(v);
        textView = v;
      }
    }

    public MyAdapter(ArrayList<String> myDataset) {
      mDataset = myDataset;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      TextView v = (TextView) LayoutInflater.from(parent.getContext())
        .inflate(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, parent, false);

      return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      holder.textView.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
      return mDataset.size();
    }
  }

}