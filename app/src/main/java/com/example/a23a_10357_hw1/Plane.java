package com.example.a23a_10357_hw1;

public class Plane implements Object /*extends Object*/ {

    private int numOfCrash = 0;
    private int x;
    private int y;
    private final int life;
    private final int planeImage = R.drawable.plane;
    private final int explodeImage = R.drawable.explosion;

    public Plane(int lifeLength, int defaultX, int defaultY) {
        life = lifeLength;
        setX(defaultX);
        setY(defaultY);
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

    public int getLife() {
        return life;
    }

    @Override
    public int getObjectImage() {
        return planeImage;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

//    @Override
//    int getObjectImage() {
//        return planeImage;
//    }
//
//    @Override
//    int getX() {
//        return x;
//    }
//
//    @Override
//    void setX(int x) {
//        this.x = x;
//    }
//
//    @Override
//    int getY() {
//        return y;
//    }
//
//    @Override
//    void setY(int y) {
//        this.y = y;
//    }
}
