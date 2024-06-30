package com.example.mobileassignment;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

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
        findViews();
        game_BTN_start_game.setOnClickListener(view -> clickStart());
    }

    private void clickStart() {
        int sensor = 0;
        int fast_mode = 0;
        if (game_SW_sensor.isChecked())
            sensor = 1;

        if (game_SW_fast_mode.isChecked())
            fast_mode = 1;

        initViews(sensor, fast_mode);
    }

    private void initViews(int sensor, int fast_mode) {
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
}
