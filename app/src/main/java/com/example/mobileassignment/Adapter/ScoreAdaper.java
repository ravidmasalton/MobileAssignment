package com.example.mobileassignment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileassignment.Interfaces.ScoreClickedCallback;
import com.example.mobileassignment.R;
import com.example.mobileassignment.Logic.ScoreUser;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class ScoreAdaper extends RecyclerView.Adapter<ScoreAdaper.ScoreViewHolder>{
    private ArrayList<ScoreUser> scores;
    private ScoreClickedCallback scoreCallback;

    public ScoreAdaper(ArrayList<ScoreUser> scores) {
        this.scores=scores;
    }

    public ScoreAdaper setScoreCallBack(ScoreClickedCallback scoreCallBack) {
        this.scoreCallback = scoreCallBack;
        return this;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_score_name, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        ScoreUser score = getItem(position);
        holder.score_LBL_name.setText(score.getName());
        holder.score_LBL_score.setText(String.valueOf(score.getScore()));

    }

    @Override
    public int getItemCount() {
        if (scores == null)
            return 0;
        else if (scores.size() > 10)
            return 10;
        return scores.size();
    }

    public ScoreUser getItem(int position) {
        return scores.get(position);
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView score_LBL_name;
        private MaterialTextView score_LBL_score;



        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);

            score_LBL_name = itemView.findViewById(R.id.score_LBL_name);
            score_LBL_score = itemView.findViewById(R.id.score_LBL_score);

            score_LBL_name.setOnClickListener(v -> {
                        if (scoreCallback != null) {
                            scoreCallback.scoreButtonClicked(getItem(getAdapterPosition()), getAdapterPosition());
                        }
                    }
            );
        }
    }
}
