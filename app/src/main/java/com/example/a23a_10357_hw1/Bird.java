package com.example.a23a_10357_hw1;

public class Bird {

    private int x;
    private int y;

    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public Bird setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Bird setY(int y) {
        this.y = y;
        return this;
    }
}
