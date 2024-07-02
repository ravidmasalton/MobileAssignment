package com.example.mobileassignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.android.material.textfield.TextInputEditText;

public class LoseGameActivity extends AppCompatActivity {

    private MaterialTextView game_LBL_score;
    private MaterialButton playAgain;
    private MaterialButton table_of_records;
    private EditText usernameInput;
    private String username;

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
            game_LBL_score.setText(" " + extras.getInt("score", 0));
        }

        // Set the Play Again button click listener
        playAgain.setOnClickListener(view -> {
            handlePlayAgain();
            // Get the username from the EditText

        });

        // Set the Table of Records button click listener
        table_of_records.setOnClickListener(view -> {
            handlTableOfRecords();
            // Intent recordsIntent = new Intent(LoseGameActivity.this, TableOfRecordsActivity.class);
            // startActivity(recordsIntent);
        });
    }

    private void handlePlayAgain() {
        // Get the username from the EditText
         username = usernameInput.getText().toString().trim();
        if (!username.isEmpty()) {
            // Use the username for any desired functionality
            // For example, start the game activity again and pass the username
            Intent playAgainIntent = new Intent(getApplicationContext(), OpenActivity.class);
            playAgainIntent.putExtra("username", username);
            startActivity(playAgainIntent);
            finish();
        } else {
            usernameInput.setError("Please enter your name");
        }
    }

    private void handlTableOfRecords() {
        // Get the username from the EditText
        username = usernameInput.getText().toString().trim();
        if (!username.isEmpty()) {
            // Use the username for any desired functionality
            // For example, start the game activity again and pass the username
           // Intent playAgainIntent = new Intent(getApplicationContext(), OpenActivity.class);
          //  playAgainIntent.putExtra("username", username);
          //  startActivity(playAgainIntent);
           // finish();
        } else {
            usernameInput.setError("Please enter your name");
        }
    }

    private void findViews() {
        // Initialize views
        game_LBL_score = findViewById(R.id.scoreValue);
        playAgain = findViewById(R.id.btnPlayAgain);
        table_of_records = findViewById(R.id.btnTable_of_records);
        usernameInput = findViewById(R.id.usernameInput);
    }
}
