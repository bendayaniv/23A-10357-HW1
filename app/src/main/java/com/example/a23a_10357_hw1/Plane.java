package com.example.a23a_10357_hw1;

public class Plane {

    private int explosion = 0;
    private int numOfCrash = 0;
    private int x;
    private final int y = 10;
    private final int life;
    private final int planeImage = R.drawable.plane;
    private final int explodeImage = R.drawable.explosion;

    public Plane(int lifeLength) {
        life = lifeLength;
        x = 1;
    }

    public int getPlaneImage() {
        return planeImage;
    }

    public int getExplodeImage() {
        return explodeImage;
    }

    public int getNumOfCrash() {
        return numOfCrash;
    }

    public Plane setNumOfCrash(int numOfCrash) {
        this.numOfCrash = numOfCrash;
        return this;
    }

    public int getExplosion() {
        return explosion;
    }

    public Plane setExplosion(int explosion) {
        this.explosion = explosion;
        return this;
    }

    public int getX() {
        return x;
    }

    public Plane setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }


    public int getLife() {
        return life;
    }
}
