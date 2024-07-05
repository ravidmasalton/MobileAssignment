package com.example.mobileassignment.Utilities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.mobileassignment.Interfaces.MoveCallbackWithSensor;
import com.example.mobileassignment.Interfaces.SpeedCallbackWithSensor;


public class DetectorWithSensor {

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    private long timestamp = 0l;

    private MoveCallbackWithSensor moveCallback;
    private SpeedCallbackWithSensor speedCallback;

    public DetectorWithSensor(Context context, MoveCallbackWithSensor moveCallback,SpeedCallbackWithSensor speedCallback) {
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.moveCallback = moveCallback;
        this.speedCallback=speedCallback;
        initEventListener();
    }




    private void initEventListener() {
        this.sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];

                calculateMove(x,y);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // pass
            }
        };
    }

    private void calculateMove(float x,float y) {
        if (System.currentTimeMillis() - timestamp > 300) {
            timestamp = System.currentTimeMillis();
            if (x > 2.0) {
                if (moveCallback != null) {
                    moveCallback.updateCurrentLocationUIOfPlayerSensor(0);
                }
            } else if (x < -2.0) {
                if (moveCallback != null) {
                    moveCallback.updateCurrentLocationUIOfPlayerSensor(1);
                }
            }


            if (y <-0.5) {
                if (speedCallback != null)
                    speedCallback.speedBeFast();
            } else
                if (speedCallback != null)
                    speedCallback.speedBeSlow();
        }



    }





    public void start(){
        sensorManager.registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    public void stop(){
        sensorManager.unregisterListener(
                sensorEventListener,
                sensor
        );
    }
}
