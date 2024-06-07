package com.example.mobileassignment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

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

    protected void onResume() {
        super.onResume();

        start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        stop();

    }

    private void update_currunt_location_view(int direction) {
        trivia_IMG_player[gameManager.getLocation()].setVisibility(View.INVISIBLE);
        gameManager.setNewLocation(direction);
        trivia_IMG_player[gameManager.getLocation()].setVisibility(View.VISIBLE);
        checkCollision();
    }

    private void lose() {
        stop();
        openAdvertisementDialog();

    }

    private void openAdvertisementDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("No lives")
                .setMessage("watch ad for extra live")
                .setPositiveButton("Yes", (dialog, which) -> showVideoAd())
                .setNegativeButton("No", (dialog, which) -> noVideoAd())
                .show();
    }

    private void showVideoAd() {
        gameManager.addExtraLive();
        updateLivesUI();
        start();
    }

    private void noVideoAd() {
        toast("You lose");
        gameDone();
    }

    private void gameDone() {
        Log.d("pttt", "Game Done");
        finish();
    }

    private void tick() {
        gameLoop();


    }

    private void checkCollision(){
        if (gameManager.checkCollision()) {
            toast("bomm");
            vibrator();
            gameManager.actionCollision();
            updateMines();
            if (gameManager.getLives() == 0) {
                lose();
            }
            updateLivesUI();
        }

    }


    private void gameLoop() {
        updateMines();
        gameManager.incrementScore();
        trivia_LBL_score.setText(String.valueOf(gameManager.getScore()));
        checkCollision();
    }

    private void updateMines(){
        ArrayList<Integer> location_of_mines = gameManager.Update_mines();
        for (int i = 0; i < location_of_mines.size(); i++) {
            if (location_of_mines.get(i) == 1) {
                trivia_IMG_surface[i].setImageResource(R.drawable.policeman);
            } else if (location_of_mines.get(i) == 0) {
                trivia_IMG_surface[i].setImageResource(0);
            }
        }
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
                findViewById(R.id.main_IMG01),
                findViewById(R.id.main_IMG02),


                findViewById(R.id.main_IMG10),
                findViewById(R.id.main_IMG11),
                findViewById(R.id.main_IMG12),


                findViewById(R.id.main_IMG20),
                findViewById(R.id.main_IMG21),
                findViewById(R.id.main_IMG22),

                findViewById(R.id.main_IMG30),
                findViewById(R.id.main_IMG31),
                findViewById(R.id.main_IMG32),

                findViewById(R.id.main_IMG40),
                findViewById(R.id.main_IMG41),
                findViewById(R.id.main_IMG42),

                findViewById(R.id.main_IMG50),
                findViewById(R.id.main_IMG51),
                findViewById(R.id.main_IMG52),

                findViewById(R.id.main_IMG60),
                findViewById(R.id.main_IMG61),
                findViewById(R.id.main_IMG62),


        };

        trivia_IMG_hearts = new AppCompatImageView[]{
                findViewById(R.id.trivia_IMG_heart1),
                findViewById(R.id.trivia_IMG_heart2),
                findViewById(R.id.trivia_IMG_heart3),
                findViewById(R.id.trivia_IMG_heart4),
        };


        trivia_IMG_player = new AppCompatImageView[]{
                findViewById(R.id.player1),
                findViewById(R.id.player2),
                findViewById(R.id.player3)
        };
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
    private void vibrator (){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }
}



