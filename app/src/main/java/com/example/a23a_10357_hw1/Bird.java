package com.example.a23a_10357_hw1;

public class Bird implements Object /*extends Object*/{

    private int x;
    private int y;
    private final int birdImage = R.drawable.bird;

    public Bird(int x, int y) {
        setX(x);
        setY(y);
    }

    @Override
    public int getObjectImage() {
        return birdImage;
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
//        return birdImage;
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
