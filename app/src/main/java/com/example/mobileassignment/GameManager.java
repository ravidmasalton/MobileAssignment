package com.example.mobileassignment;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private int lives = 3;
    private int score = 0;
    private int currunt_location_User;
    private final int ROW=7; //row-1 because the line of the player
    private final int COLUMN=3;
    private Random random = new Random();
    private ArrayList<Integer>all_mine=new ArrayList<>();
    private boolean isNewLIne=true;


        public GameManager(int initialLives) {
            if (initialLives > 0  &&  initialLives <= 4) {
                lives = initialLives;
            }
            else
                lives=3;

            for (int i = 0; i < ROW * COLUMN; i++) {
                all_mine.add(0);
            }

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
        public  ArrayList<Integer> Update_mines(){
                for(int i=0;i<COLUMN;i++){
                    all_mine.set((ROW-1)*COLUMN+i,0);
                }
                for (int i = ROW-2; i >= 0; i--) {
                    for (int j = 0; j < COLUMN; j++) {
                        // If the current cell has a 1 and the cell below is 0
                        if (all_mine.get(i*COLUMN+j) == 1 ) {
                            // Move the 1 down
                            all_mine.set(i*COLUMN+j, 0);
                            all_mine.set((i+1)*COLUMN+j, 1);
                        }
                    }
                }
                if(isNewLIne) {
                    Adding_new_mines();
                    isNewLIne=false;
                }
                else
                    isNewLIne=true;

            return all_mine;
            }

        public boolean checkCollision(){
            if(all_mine.get((ROW-1)*COLUMN+this.currunt_location_User)==1) {
                actionCollision();
                return true;

            }
            return  false;

        }

        public void actionCollision(){
                all_mine.set((ROW-1)*COLUMN+this.currunt_location_User,0);
                decreaseLive();
                decreasetScore();


        }

    public int getROW(){
            return  ROW;
    }

    public int getCOLUMN(){
        return  COLUMN;
    }
    public void Adding_new_mines(){
        int index=random.nextInt(COLUMN);
        all_mine.set(index,1);
    }
    public int getLocation(){
        return  currunt_location_User;
    }


    public void incrementScore() {
        score += 10;
    }

    public void decreasetScore() {
        score -= 50;
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
