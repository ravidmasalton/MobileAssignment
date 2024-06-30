package com.example.mobileassignment;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class LoseGameActivity extends AppCompatActivity {

    private MaterialTextView game_LBL_score;
    private MaterialButton playAgain;
    private MaterialButton table_of_records;


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
            game_LBL_score.setText(" "+extras.getInt("score", 0));
        }


        // Set the Play Again button click listener
        playAgain.setOnClickListener(view -> {
            // Start the game activity again
            Intent playAgainIntent = new Intent(getApplicationContext(), OpenActivity.class);
            startActivity(playAgainIntent);
            finish();
        });

        // Set the Table of Records button click listener
        table_of_records.setOnClickListener(view -> {
            // Start the TableOfRecordsActivity
           // Intent recordsIntent = new Intent(lose_gameActivity.this, TableOfRecordsActivity.class);
          //  startActivity(recordsIntent);
        });
    }




    private void findViews() {
        // Initialize views
        game_LBL_score = findViewById(R.id.scoreValue);
        playAgain = findViewById(R.id.btnPlayAgain);
        table_of_records = findViewById(R.id.btnTable_of_records);
    }

}


