package com.example.mobileassignment;

import java.util.ArrayList;

public class GameManager {
    private int lives = 3;
    private int score = 0;
    private int currunt_location_User;
    private final int ROW=7;
    private final int COLUMN=3;


    public GameManager(int initialLives,int length) {
        if (initialLives > 0  &&  initialLives <= 4) {
            lives = initialLives;
        }
        else
            lives=3;

        currunt_location_User=COLUMN/2;
    }

    public void setNewLocation(int direction){
       if(direction==1)
           moveLocationRight();
       else
           moveLocationLeft();


    }

    public void moveLocationRight(){
        if(currunt_location_User+1<COLUMN)
            currunt_location_User+=1;
    }

    public void moveLocationLeft(){
        if(currunt_location_User-1>=0)
            currunt_location_User-=1;
    }


    public int getLocation(){
        return  currunt_location_User;
    }
    public int getRow(){
        return  ROW;
    }
    public int getColumn(){
        return  COLUMN;
    }




    public void incrementScore() {
        score += 10;
    }

    public void decreaseLive() {
        lives--;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }



    public void addExtraLive() {
        lives++;
    }
}
