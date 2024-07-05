package com.example.mobileassignment.Utilities;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mobileassignment.Logic.ScoreUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MSPV3 {

    private static MSPV3 mspv3;
    public static final String SCORELIST = "SCORELIST";
    private SharedPreferences prefs;

    private MSPV3(Context context) {
        prefs = context.getSharedPreferences(SCORELIST, MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (mspv3 == null) {
            mspv3 = new MSPV3(context);
        }
    }

    public static MSPV3 getInstance() {
        return mspv3;
    }

    public void saveListOfScore(ArrayList<ScoreUser> scoreUsers) {

        Gson gson = new Gson();
        String scoreListAsJson = gson.toJson(scoreUsers);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SCORELIST, scoreListAsJson);
        editor.apply();
    }

    public  ArrayList<ScoreUser> readScoreList() {
        String json = prefs.getString(SCORELIST, "");
        ArrayList<ScoreUser> newScoreList = new ArrayList<>();
        TypeToken<ArrayList<ScoreUser>> token = new TypeToken<ArrayList<ScoreUser>>() {};
        if (json.equals("")) {
            return newScoreList;
        }
        return new Gson().fromJson(json, token.getType());
    }



}
