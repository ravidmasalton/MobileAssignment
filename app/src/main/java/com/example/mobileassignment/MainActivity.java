package com.example.mobileassignment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {


    private AppCompatImageView[] trivia_IMG_hearts;
    private AppCompatImageView[] trivia_IMG_surface;
    private AppCompatImageView[] trivia_IMG_player;
    private MaterialTextView trivia_LBL_score;
    private MaterialButton trivia_BTN_left;
    private MaterialButton trivia_BTN_right;
    private GameManager gameManager;
    private Handler handler = new Handler();
    private final int DELAY = 1000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViews();
        gameManager = new GameManager(3, trivia_IMG_surface.length);
        updateLivesUI();


        trivia_BTN_left.setOnClickListener(v -> update_currunt_location_view(0));
        trivia_BTN_right.setOnClickListener(v -> update_currunt_location_view(1));

    }

    private void update_currunt_location_view(int direction) {
        trivia_IMG_player[gameManager.getLocation()].setVisibility(View.INVISIBLE);
        gameManager.setNewLocation(direction);
        trivia_IMG_player[gameManager.getLocation()].setVisibility(View.VISIBLE);


    }


    private void tick() {


    }

    private void start() {

        handler.postDelayed(runnable, DELAY);
    }

    private Runnable runnable = new Runnable() {
        public void run() {
            handler.postDelayed(this, DELAY);
            tick();

        }
    };

    private void stop() {
        handler.removeCallbacks(runnable);

    }

    private void updateLivesUI() {
        int SZ = trivia_IMG_hearts.length;

        for (int i = 0; i < SZ; i++) {
            trivia_IMG_hearts[i].setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < SZ - gameManager.getLives(); i++) {
            trivia_IMG_hearts[SZ - i - 1].setVisibility(View.INVISIBLE);
        }


    }

    private void findViews() {
        trivia_LBL_score = findViewById(R.id.trivia_LBL_score);
        trivia_BTN_left = findViewById(R.id.trivia_BTN_Left);
        trivia_BTN_right = findViewById(R.id.trivia_BTN_Right);
        trivia_IMG_surface = new AppCompatImageView[]{
                findViewById(R.id.main_IMG00),
                findViewById(R.id.main_IMG10),
                findViewById(R.id.main_IMG20),
                findViewById(R.id.main_IMG30),
                findViewById(R.id.main_IMG40),
                findViewById(R.id.main_IMG50),


                findViewById(R.id.main_IMG01),
                findViewById(R.id.main_IMG11),
                findViewById(R.id.main_IMG21),
                findViewById(R.id.main_IMG31),
                findViewById(R.id.main_IMG41),
                findViewById(R.id.main_IMG51),


                findViewById(R.id.main_IMG02),
                findViewById(R.id.main_IMG12),
                findViewById(R.id.main_IMG22),
                findViewById(R.id.main_IMG32),
                findViewById(R.id.main_IMG42),
                findViewById(R.id.main_IMG52),

        };

        trivia_IMG_hearts = new AppCompatImageView[]{
                findViewById(R.id.trivia_IMG_heart1),
                findViewById(R.id.trivia_IMG_heart2),
                findViewById(R.id.trivia_IMG_heart3),
                findViewById(R.id.trivia_IMG_heart4),
        };


        trivia_IMG_player = new AppCompatImageView[]{
                findViewById(R.id.main_player1),
                findViewById(R.id.main_player2),
                findViewById(R.id.main_player3)
        };
    }
}



