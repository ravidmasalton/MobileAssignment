package com.example.mobileassignment.Logic;

public class ScoreUser implements Comparable<ScoreUser> {
    private String name = "";
    private int score = 0;
    private double lon = 0;
    private double lat = 0;


    public ScoreUser(String name, int score, double lon, double lat) {

        this.name = name;
        this.score = score;
        this.lon = lon;
        this.lat = lat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public int compareTo(ScoreUser o) {
        if (this.score > o.getScore())
            return this.score;
        else
            return o.getScore();
    }
}
