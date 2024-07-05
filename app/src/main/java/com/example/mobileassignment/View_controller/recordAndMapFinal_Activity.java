package com.example.mobileassignment.View_controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileassignment.Interfaces.GetLocationByScore;
import com.example.mobileassignment.Fragment.ListFragment;
import com.example.mobileassignment.Fragment.MapFragment;
import com.example.mobileassignment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class recordAndMapFinal_Activity extends AppCompatActivity {
    private MapFragment mapFragment;
    private ListFragment listFragment;
    private FloatingActionButton toOpenActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_and_map);
        findViews();
        listFragment = new ListFragment();
        listFragment.setCallbackGetLocationByScore(new GetLocationByScore() {
            @Override
            public void getLocationByScore(double lat, double lng) {
                mapFragment.addLocation(lat,lng);

            }
        });

        mapFragment = new MapFragment();
        toOpenActivity.setOnClickListener(view -> changeIntent());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_LAY_top, listFragment)
                .add(R.id.main_LAY_bottom, mapFragment)
                .commit();




    }

    private void findViews() {

        toOpenActivity = findViewById(R.id.button_back);
    }

    private void changeIntent() {
        Intent mainIntent = new Intent(this, OpenActivity.class);
        startActivity(mainIntent);
        finish();
    }
}