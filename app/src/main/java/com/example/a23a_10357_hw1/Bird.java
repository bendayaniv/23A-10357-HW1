package com.example.a23a_10357_hw1;

public class Bird extends Object{

    private int x;
    private int y;
    private final int birdImage = R.drawable.bird;

    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    int getObjectImage() {
        return birdImage;
    }

    @Override
    int getX() {
        return x;
    }

    @Override
    void setX(int x) {
        this.x = x;
    }

    @Override
    int getY() {
        return y;
    }

    @Override
    void setY(int y) {
        this.y = y;
    }
}
