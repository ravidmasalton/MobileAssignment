package com.example.mobileassignment;

import android.app.Application;

import com.example.mobileassignment.Utilities.MSPV3;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MSPV3.init(this);
    }
}
