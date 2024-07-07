package com.example.mobileassignment.View_controller;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mobileassignment.Utilities.MSPV3;
import com.example.mobileassignment.R;
import com.example.mobileassignment.Logic.ScoreUser;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Collections;

public class LoseGameActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private MaterialTextView game_LBL_score;
    private MaterialButton playAgain;
    private MaterialButton table_of_records;
    private String usernameInput;
    private int score;
    private FusedLocationProviderClient locationOfUser;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lose_game);
        findViews();

        // Get the score from the intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            score = extras.getInt("score", 0);
            game_LBL_score.setText(" " + score);
        }

        locationOfUser = LocationServices.getFusedLocationProviderClient(this);

        // Request location permission if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            getUserLocation();
        }

        showEnterNameDialog();

        // Set the Play Again button click listener
        playAgain.setOnClickListener(view -> handlePlayAgain());

        // Set the Table of Records button click listener
        table_of_records.setOnClickListener(view -> handleTableOfRecords());
    }

    private void showEnterNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Your Name");

        // Set up the input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = input.getText().toString();
                if (!name.isEmpty()) {
                    usernameInput = name;
                } else {
                    Toast.makeText(LoseGameActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    showEnterNameDialog(); // Show the dialog again if the input was empty
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showEnterNameDialog();
            }
        });

        builder.show();
    }

    private void handlePlayAgain() {
        if (usernameInput == null || usernameInput.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            showEnterNameDialog();
        } else {
            saveUserNameAndScore();
            Intent playAgainIntent = new Intent(getApplicationContext(), OpenActivity.class);
            playAgainIntent.putExtra("username", usernameInput);
            startActivity(playAgainIntent);
            finish();
        }
    }

    private void handleTableOfRecords() {
        if (usernameInput == null || usernameInput.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            showEnterNameDialog();
        } else {
            saveUserNameAndScore();
            Intent recordsIntent = new Intent(getApplicationContext(), recordAndMapFinal_Activity.class);
            startActivity(recordsIntent);
            finish();
        }
    }

    private void saveUserNameAndScore() {
        MSPV3 mspv3 = MSPV3.getInstance();
        ArrayList<ScoreUser> scores = mspv3.readScoreList();

        scores.add(new ScoreUser(usernameInput, score, longitude, latitude));
        Collections.sort(scores);
        mspv3.saveListOfScore(scores);
    }

    private void findViews() {
        // Initialize views
        game_LBL_score = findViewById(R.id.scoreValue);
        playAgain = findViewById(R.id.btnPlayAgain);
        table_of_records = findViewById(R.id.btnTable_of_records);
    }

    private void getUserLocation() {
        try {
            Task<Location> locationTask = locationOfUser.getLastLocation();
            locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    } else {
                        Toast.makeText(LoseGameActivity.this, "Unable to get location", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            locationTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoseGameActivity.this, "Failed to get location", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (SecurityException e) {
            e.printStackTrace();
            Toast.makeText(this, "Location permission error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getUserLocation();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
