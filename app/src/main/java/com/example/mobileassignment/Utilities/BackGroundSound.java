package com.example.mobileassignment.Utilities;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BackGroundSound {
    private  Context context;
    private Executor executor;
    private MediaPlayer mediaPlayer;
    private  int resID;

    public BackGroundSound(Context context, int resID) {
        this.context = context;
        this.resID = resID;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void playSound() {
        executor.execute(() -> {
            try {
                if (mediaPlayer == null) {
                    mediaPlayer = mediaPlayer.create(context, resID);
                    mediaPlayer.setLooping(false);
                    mediaPlayer.setVolume(1.0f, 1.0f);
                }
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
                releaseMediaPlayer();
            }
        });
    }

    private void setLooping(boolean b) {

    }

    public void stopSound() {
        executor.execute(() -> {
            try {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                releaseMediaPlayer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void release() {
        executor.execute(this::releaseMediaPlayer);
    }
}
