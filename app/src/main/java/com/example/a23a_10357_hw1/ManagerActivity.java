package com.example.a23a_10357_hw1;

import java.util.ArrayList;
import java.util.Random;

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
                plane.setX(planeOnX - 1);
            }
        } else if(planeMove == 1) {
            if (plane.getX() != 2) {
                plane.setX(planeOnX + 1);
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
        if(birds.size() > 0) {
            checkTheAbilityToCreateNewBird();
        }
        else {
            createNewBird(-1);
        }
//        createNewBird(-1);
    }

    /**
     * Check if we can create new bird this step
     * (so that the player can continue to play without being disqualified)
     */
    public void checkTheAbilityToCreateNewBird() {
        int xFirstBird = -1;
        int xSecondBird = -1;
        for (int i = 0; i < birds.size(); i++) {
            if (birds.get(i).getY() == 3) {
                xFirstBird = birds.get(i).getX();
            } else if (birds.get(i).getY() == 2) {
                xSecondBird = birds.get(i).getX();
            }
        }

        if (xFirstBird != -1 && xSecondBird != -1) {
            if (xFirstBird == 0) {
                if (xSecondBird == 1) {
                    createNewBird(2);
                } else if (xSecondBird == 2) {
                    createNewBird(1);
                } else {
                    createNewBird(-1);
                }
            } else if (xFirstBird == 1) {
                if (xSecondBird == 0) {
                    createNewBird(2);
                } else if (xSecondBird == 2) {
                    createNewBird(0);
                } else {
                    createNewBird(-1);
                }
            } else if (xFirstBird == 2) {
                if (xSecondBird == 0) {
                    createNewBird(1);
                } else if (xSecondBird == 1) {
                    createNewBird(0);
                } else {
                    createNewBird(-1);
                }
            }
        }
    }

    /**
     * Create new bird
     *
     * @param index == indicates if can be anywhere (-1) or can not be in specific place(!-1)
     */
    public void createNewBird(int index) {
        int randomX = new Random().nextInt(3);
        if (index != -1) {
            while (randomX == index) {
                randomX = new Random().nextInt(3);
            }
        }
        birds.add(new Bird(randomX, 1));
    }

    /**
     * Check if there is a clash between a bird and the plane
     * If it does. it raise the number of crashes in 1
     * (if it lost all his life, it stop to raise the number of crashes)
     *
     * @return true if does, false if does not
     */
    public boolean checkIfCrash() {
        for (int i = 0; i < birds.size(); i++) {
            if (birds.get(i).getX() == plane.getX() && birds.get(i).getY() == plane.getY()) {
                raiseCrashNumber();
                return true;
            }
        }
        return false;
    }

    private void raiseCrashNumber() {
        int crash = getPlane().getNumOfCrash();
        if (crash < getPlane().getLife()) {
            crash++;
            getPlane().setNumOfCrash(crash);
        }
    }

}
