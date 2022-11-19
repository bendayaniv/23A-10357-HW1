package com.example.a23a_10357_hw1;

import java.util.ArrayList;

public class ManagerActivity {

    private ArrayList<Bird> birds;
    private Plane plane;

    public ManagerActivity(int life) {
        plane = new Plane(life);
        birds = new ArrayList<>();
    }

    public ArrayList<Bird> getBirds() {
        return birds;
    }

    public Plane getPlane() {
        return plane;
    }


    /**
     * Can not move directly from [10][0] to [10][2] and the opposite
     */
    public void movePlane(int planeMove) {
        int planeOnX = plane.getX();
        if (planeMove == -1) {
            if (plane.getX() != 0) {
                plane.setX(planeOnX + 1);
            }
        } else {
            if (plane.getX() != 2) {
                plane.setX(planeOnX - 1);
            }
        }
    }

    /**
     * Move all exists birds one step down
     *
     * @param boardLimit the limit to the objects on the board
     */
    public void moveBirdsDown(int boardLimit) {
        for (int i = 0; i < birds.size(); i++) {
            if (birds.get(i).getY() == boardLimit) {
                birds.remove(birds.get(i));
            } else {
                int tmpY = birds.get(i).getY() + 1;
                birds.get(i).setY(tmpY);
            }
        }
    }

    /**
     * Check if there is a clash between a bird and the plane
     *
     * @return true if does, false if does not
     */
    private boolean checkIfCrash() {
        for (int i = 0; i < birds.size(); i++) {
            if (birds.get(i).getX() == plane.getX() && birds.get(i).getY() == plane.getY()) {
                return true;
            }
        }
        return false;
    }
}
