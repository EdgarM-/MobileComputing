package com.example.administrator.concentrationea;

/**
 * Created by Administrator on 3/10/2016.
 */
public class Player {
    private String name;
    private int score;

    Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void plusScore(int plus){
        this.score += plus;
    }

    public int getScore(){
        return this.score;
    }
}
