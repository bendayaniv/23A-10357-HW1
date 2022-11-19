package com.example.a23a_10357_hw1;

public class GamePlayer {

    private int explosion = 0;
    private int numOfCrash = 0;
    private int x;
    private int y;
    private final int life;

    public GamePlayer(int lifeLength) {
        life = lifeLength;
        x = 10;
        y = 1;
    }

    public int getNumOfCrash() {
        return numOfCrash;
    }

    public GamePlayer setNumOfCrash(int numOfCrash) {
        this.numOfCrash = numOfCrash;
        return this;
    }

    public int getExplosion() {
        return explosion;
    }

    public GamePlayer setExplosion(int explosion) {
        this.explosion = explosion;
        return this;
    }

    public int getX() {
        return x;
    }

    public GamePlayer setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public GamePlayer setY(int y) {
        this.y = y;
        return this;
    }

    public int getLife() {
        return life;
    }
}
