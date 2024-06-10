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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private AppCompatImageView[] game_IMG_hearts;
    private AppCompatImageView[] game_IMG_surface;
    private AppCompatImageView[] game_IMG_player;
    private MaterialTextView game_LBL_score;
    private MaterialButton game_BTN_left;
    private MaterialButton game_BTN_right;
    private GameManager gameManager;
    private Handler handler = new Handler();
    private final int DELAY = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViews();
        gameManager = new GameManager(3);
        updateLivesUI();


        game_BTN_left.setOnClickListener(v -> update_currunt_location_view(0));
        game_BTN_right.setOnClickListener(v -> update_currunt_location_view(1));
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


    private void lose() {
        stop();
        openAdvertisementDialog();

    }

    private void gameDone() {
        Log.d("pttt", "Game Done");
        finish();
    }


    private void checkCollision() {
        if (gameManager.checkCollision()) {
            toast("bomm");
            vibrator();

            int index_location=(gameManager.getROW()-1)*gameManager.getCOLUMN()+gameManager.getLocation();
            game_IMG_surface[index_location].setImageResource(0);
            if (gameManager.getLives() == 0) {
                lose();
            }
            updateLivesUI();
        }

    }


    private void gameLoop() {
        updateMines();
        gameManager.incrementScore();
        game_LBL_score.setText(String.valueOf(gameManager.getScore()));
        checkCollision();
    }

    private void updateMines() {
        ArrayList<Integer> location_of_mines = gameManager.Update_mines();
        for (int i = 0; i < location_of_mines.size(); i++) {
            if (location_of_mines.get(i) == 1) {
                game_IMG_surface[i].setImageResource(R.drawable.policeman);
            } else if (location_of_mines.get(i) == 0) {
                game_IMG_surface[i].setImageResource(0);
            }
        }
    }

    private void updateLivesUI() {
        int SZ = game_IMG_hearts.length;

        for (int i = 0; i < SZ; i++) {
            game_IMG_hearts[i].setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < SZ - gameManager.getLives(); i++) {
            game_IMG_hearts[SZ - i - 1].setVisibility(View.INVISIBLE);
        }


    }

    private void update_currunt_location_view(int direction) {
        game_IMG_player[gameManager.getLocation()].setVisibility(View.INVISIBLE);
        gameManager.setNewLocation(direction);
        game_IMG_player[gameManager.getLocation()].setVisibility(View.VISIBLE);
        checkCollision();
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

    private void tick() {
        gameLoop();


    }


    private void findViews() {
        game_LBL_score = findViewById(R.id.trivia_LBL_score);
        game_BTN_left = findViewById(R.id.trivia_BTN_Left);
        game_BTN_right = findViewById(R.id.trivia_BTN_Right);
        game_IMG_surface = new AppCompatImageView[]{

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

        game_IMG_hearts = new AppCompatImageView[]{
                findViewById(R.id.trivia_IMG_heart1),
                findViewById(R.id.trivia_IMG_heart2),
                findViewById(R.id.trivia_IMG_heart3),
                findViewById(R.id.trivia_IMG_heart4),
        };


        game_IMG_player = new AppCompatImageView[]{
                findViewById(R.id.player1),
                findViewById(R.id.player2),
                findViewById(R.id.player3)
        };
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void vibrator() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    private void noVideoAd() {
        toast("You lose");
        gameDone();
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



}




