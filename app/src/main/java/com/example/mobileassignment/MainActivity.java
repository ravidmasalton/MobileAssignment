package com.example.mobileassignment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {


    private AppCompatImageView[] game_IMG_hearts;
    private AppCompatImageView[][] game_IMG_surface;
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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);

        findViews();
        game_BTN_left.setOnClickListener(v -> updateCurrentLocationUIOfPlayer(0));
        game_BTN_right.setOnClickListener(v -> updateCurrentLocationUIOfPlayer(1));
        gameManager = new GameManager(3);
        updateLivesUI();


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



    private void checkCollision() { //check if player hit a mine
        if (gameManager.checkCollision()) {
            toast("boom");
            vibrator();
            playSound();
            int row=gameManager.getROW()-1;
            int col=gameManager.getLocation();

            game_IMG_surface[row][col].setImageResource(0);
            if (gameManager.getLives() == 0) {
                lose();
            }
            updateLivesUI();
        }
        else
            gameManager.incrementScore();

        game_LBL_score.setText(String.valueOf(gameManager.getScore()));


    }



    private void gameLoop() {
        updateMines();
        checkCollision();
    }

    private void updateMines() { //Update mines on the matrix
        int[][] location_of_mines = gameManager.Update_mines();
        for (int i = 0; i < location_of_mines.length; i++) {
            for (int j = 0; j < location_of_mines[i].length; j++)
                if (location_of_mines[i][j] == 1) {
                    game_IMG_surface[i][j].setImageResource(R.drawable.police_car);
                } else if (location_of_mines[i][j] == 0) {
                    game_IMG_surface[i][j].setImageResource(0);
            }
        }
    }

    private void updateLivesUI() { //update lives
        int SZ = game_IMG_hearts.length;

        for (int i = 0; i < SZ; i++) {
            game_IMG_hearts[i].setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < SZ - gameManager.getLives(); i++) {
            game_IMG_hearts[SZ - i - 1].setVisibility(View.INVISIBLE);
        }


    }

    private void updateCurrentLocationUIOfPlayer(int direction) {  //update player location
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
        game_LBL_score = findViewById(R.id.game_LBL_score);
        game_BTN_left = findViewById(R.id.game_BTN_Left);
        game_BTN_right = findViewById(R.id.game_BTN_Right);
        game_IMG_surface = new AppCompatImageView[][]{

                {findViewById(R.id.main_IMG00),
                findViewById(R.id.main_IMG01),
                findViewById(R.id.main_IMG02)},


                {findViewById(R.id.main_IMG10),
                findViewById(R.id.main_IMG11),
                findViewById(R.id.main_IMG12)},


                {findViewById(R.id.main_IMG20),
                findViewById(R.id.main_IMG21),
                findViewById(R.id.main_IMG22)},

                {findViewById(R.id.main_IMG30),
                findViewById(R.id.main_IMG31),
                findViewById(R.id.main_IMG32)},

                {findViewById(R.id.main_IMG40),
                findViewById(R.id.main_IMG41),
                findViewById(R.id.main_IMG42)},

                {findViewById(R.id.main_IMG50),
                findViewById(R.id.main_IMG51),
                findViewById(R.id.main_IMG52)},

                {findViewById(R.id.main_IMG60),
                findViewById(R.id.main_IMG61),
                findViewById(R.id.main_IMG62)},

        };

        game_IMG_hearts = new AppCompatImageView[]{
                findViewById(R.id.game_IMG_heart1),
                findViewById(R.id.game_IMG_heart2),
                findViewById(R.id.game_IMG_heart3),
                findViewById(R.id.game_IMG_heart4),
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
    private void playSound() {
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.police_siren2);
        mp.start();

    }
    private void lose() { //lose game
        stop();
        toast("You lose");
        gameStartup();;
        start();
    }

    public void gameStartup(){ //restart game
        game_IMG_player[gameManager.getLocation()].setVisibility(View.INVISIBLE);
        gameManager = new GameManager(3);
        updateLivesUI();
        updateMines();
        game_IMG_player[gameManager.getLocation()].setVisibility(View.VISIBLE);
    }





}




