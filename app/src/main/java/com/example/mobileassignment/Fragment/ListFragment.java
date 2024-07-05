package com.example.mobileassignment.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobileassignment.Interfaces.GetLocationByScore;
import com.example.mobileassignment.Interfaces.ScoreClickedCallback;
import com.example.mobileassignment.Utilities.MSPV3;
import com.example.mobileassignment.R;
import com.example.mobileassignment.Adapter.ScoreAdaper;
import com.example.mobileassignment.Logic.ScoreUser;

import java.util.ArrayList;


public class ListFragment extends Fragment {
    private RecyclerView recyclerView_LST_scores;
    private ArrayList<ScoreUser> scores;
    private GetLocationByScore getLocationByScore ;


    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_list, container, false);
        findViews(view);
        MSPV3 manager = MSPV3.getInstance();
        scores = manager.readScoreList();
        initViews(view);


        return view;
    }
    public void setCallbackGetLocationByScore(GetLocationByScore getLocationByScore) {
        this.getLocationByScore = getLocationByScore;
    }

    private void findViews(View view) {
        this.recyclerView_LST_scores = view.findViewById(R.id.recyclerView_LST_scores);
    }
    private void initViews(View view) {
        ScoreAdaper scoreAdapter = new ScoreAdaper(scores);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView_LST_scores.setLayoutManager(linearLayoutManager);
        recyclerView_LST_scores.setAdapter(scoreAdapter);

        scoreAdapter.setScoreCallBack(new ScoreClickedCallback() {
            @Override
            public void scoreButtonClicked(ScoreUser scoreUser, int position) {
                getLocationByScore.getLocationByScore(scoreUser.getLat(), scoreUser.getLon());
            }
        });
    }

}
