package com.example.mobileassignment.View_controller;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.mobileassignment.R;
import com.google.android.material.button.MaterialButton;

public class OpenActivity extends AppCompatActivity {

    private MaterialButton game_BTN_start_game;
    private MaterialButton game_BTN_show_record;
    private SwitchCompat game_SW_sensor;
    private SwitchCompat game_SW_fast_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_open);
        getPermission();
        findViews();

        game_BTN_start_game.setOnClickListener(view -> clickStart());
        game_BTN_show_record.setOnClickListener(view -> clickTableOfRecord());

    }

    private void clickTableOfRecord() {
        initViewsToRecord();
    }

    private void initViewsToRecord() {
        Intent mainActivityIntent = new Intent(getApplicationContext(), recordAndMapFinal_Activity.class);

        startActivity(mainActivityIntent);
        finish();
    }

    private void clickStart() {
        int sensor = 0;
        int fast_mode = 0;
        if (game_SW_sensor.isChecked())
            sensor = 1;

        if (game_SW_fast_mode.isChecked())
            fast_mode = 1;

        initViewsToStartGame(sensor, fast_mode);
    }

    private void initViewsToStartGame(int sensor, int fast_mode) {
        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("SENSOR", sensor);
        bundle.putInt("FAST_MODE", fast_mode);
        mainActivityIntent.putExtras(bundle);
        startActivity(mainActivityIntent);
        finish();
    }

    private void findViews() {
        game_BTN_start_game = findViewById(R.id.game_BTN_Start_Game);
        game_BTN_show_record = findViewById(R.id.game_BTN_Record);
        game_SW_sensor = findViewById(R.id.switch_sensors);
        game_SW_fast_mode = findViewById(R.id.switch_fast_mode);
    }


    void getPermission() {  // A message for the user to approve sharing a location
        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION, false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                            } else {
                                finish();
                                // No location access granted.
                            }
                        }
                );
        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION});
    }


}
