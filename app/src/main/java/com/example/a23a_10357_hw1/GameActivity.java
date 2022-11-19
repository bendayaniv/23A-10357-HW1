package com.example.a23a_10357_hw1;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private final int yLength = 12;
    private final int xLength = 3;
    private final int boardLimit = 10;
    private final int stepRightOfPlane = 1;
    private final int stepLeftOfPlane = -1;
    private final int planeImage = R.drawable.plane;

    private ExtendedFloatingActionButton gameActivity_FAB_left;
    private ExtendedFloatingActionButton gameActivity_FAB_right;
    private ShapeableImageView[][] gameBoard;
    private ShapeableImageView[] game_IMG_hearts;
//    private int[][] birds = new int[][]{
//            {0, 0, 0},
//            {0, 0, 0},
//            {0, 0, 0},
//            {0, 0, 0},
//            {0, 0, 0},
//            {0, 0, 0},
//            {0, 0, 0},
//            {0, 0, 0},
//            {0, 0, 0},
//            {0, 0, 0},
//            {0, 0, 0},
//            {0, 0, 0}
//    };

//    private Plane gamePlayer;
    private ManagerActivity gameManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        findViews();
        createButtons();
//        gamePlayer = new Plane(game_IMG_hearts.length);
        gameManager = new ManagerActivity(game_IMG_hearts.length);
//        initViews();
        createNewBird(-1);
    }

    private void findViews() {
        gameActivity_FAB_left = findViewById(R.id.gameActivity_FAB_left);
        gameActivity_FAB_right = findViewById(R.id.gameActivity_FAB_right);
        gameBoard = new ShapeableImageView[][]{{findViewById(R.id.game_IMG_0_0), findViewById(R.id.game_IMG_0_1), findViewById(R.id.game_IMG_0_2)},
                {findViewById(R.id.game_IMG_1_0), findViewById(R.id.game_IMG_1_1), findViewById(R.id.game_IMG_1_2)},
                {findViewById(R.id.game_IMG_2_0), findViewById(R.id.game_IMG_2_1), findViewById(R.id.game_IMG_2_2)},
                {findViewById(R.id.game_IMG_3_0), findViewById(R.id.game_IMG_3_1), findViewById(R.id.game_IMG_3_2)},
                {findViewById(R.id.game_IMG_4_0), findViewById(R.id.game_IMG_4_1), findViewById(R.id.game_IMG_4_2)},
                {findViewById(R.id.game_IMG_5_0), findViewById(R.id.game_IMG_5_1), findViewById(R.id.game_IMG_5_2)},
                {findViewById(R.id.game_IMG_6_0), findViewById(R.id.game_IMG_6_1), findViewById(R.id.game_IMG_6_2)},
                {findViewById(R.id.game_IMG_7_0), findViewById(R.id.game_IMG_7_1), findViewById(R.id.game_IMG_7_2)},
                {findViewById(R.id.game_IMG_8_0), findViewById(R.id.game_IMG_8_1), findViewById(R.id.game_IMG_8_2)},
                {findViewById(R.id.game_IMG_9_0), findViewById(R.id.game_IMG_9_1), findViewById(R.id.game_IMG_9_2)},
                {findViewById(R.id.game_IMG_10_0), findViewById(R.id.game_IMG_10_1), findViewById(R.id.game_IMG_10_2)},
                {findViewById(R.id.game_IMG_11_0), findViewById(R.id.game_IMG_11_1), findViewById(R.id.game_IMG_11_2)}};

        game_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.game_IMG_heart1),
                findViewById(R.id.game_IMG_heart2),
                findViewById(R.id.game_IMG_heart3)
        };
    }

    private void createButtons() {
        gameActivity_FAB_right.setOnClickListener(v -> playerGoRight());
        gameActivity_FAB_left.setOnClickListener(v -> playerGoLeft());
    }

//    private void initViews() {
//        gameActivity_FAB_right.setOnClickListener(v -> playerGoRight());
//        gameActivity_FAB_left.setOnClickListener(v -> playerGoLeft());
//        restorePlane(gamePlayer.getY());
//    }

    private void movePlaneRight() {
        int x = gameManager.getPlane().getX();
        vanishPlane(x);
        gameManager.movePlane(stepRightOfPlane);
        x = gameManager.getPlane().getX();
        restorePlane(x);
//        if (x != 2) {
//            vanishPlane(x);
//            x++;
//            gameManager.getPlane().setX(x);
//            restorePlane(x);
//            moveBird();
//        }
    }

    private void vanishPlane(int xScale) {
        Glide.with(this).clear(gameBoard[boardLimit][xScale]);
    }

    private void restorePlane(int xScale) {
        Glide.with(this).load(planeImage).fitCenter().into(gameBoard[boardLimit][xScale]);
//        if (birds[9][yScale] == 1) {
//            vanishBird(10, yScale);
//            initScreen();
//        } else if(birds[10][yScale] == 1) {
//            vanishBird(10, yScale);
//            Glide.with(this)
//                    .load(R.drawable.plane)
//                    .fitCenter()
//                    .into(gameBoard[10][yScale]);
//        }
//        else {
//            Glide.with(this)
//                    .load(R.drawable.plane)
//                    .fitCenter()
//                    .into(gameBoard[10][yScale]);
//        }
    }

    private void initScreen() {
        for (int i = 1; i < yLength - 1; i++) {
            for (int j = 0; j < xLength; j++) {
                birds[i][j] = 0;
                Glide.with(this).clear(gameBoard[i][j])
//                        .load("")
//                        .fitCenter()
//                        .into(gameBoard[i][j])
                ;
            }
        }
        Glide.with(this)
                .load(R.drawable.plane)
                .fitCenter()
                .into(gameBoard[10][1]);
        gamePlayer.setX(1);
    }

//    private void vanishPlane(int yScale) {
//        Glide.with(this)
//                .load("")
//                .fitCenter()
//                .into(gameBoard[10][yScale]);
//    }

//    private void restorePlane(int yScale) {
//        if (birds[9][yScale] == 1) {
//            vanishBird(10, yScale);
//            initScreen();
//        } else if(birds[10][yScale] == 1) {
//            vanishBird(10, yScale);
//            Glide.with(this)
//                    .load(R.drawable.plane)
//                    .fitCenter()
//                    .into(gameBoard[10][yScale]);
//        }
//        else {
//            Glide.with(this)
//                    .load(R.drawable.plane)
//                    .fitCenter()
//                    .into(gameBoard[10][yScale]);
//        }
//    }

//    private void explosion(int yScale) {
//        Glide.with(this)
//                .load(R.drawable.explosion)
//                .fitCenter()
//                .into(gameBoard[10][yScale]);
//    }

    private void playerGoRight() {
        int x = gamePlayer.getX();
        if (x != 2) {
            vanishPlane(x);
            x++;
            gamePlayer.setX(x);
            restorePlane(x);
            moveBird();
        }
    }

    private void playerGoLeft() {
        int x = gamePlayer.getX();
        if (x != 0) {
            vanishPlane(x);
            x--;
            gamePlayer.setX(x);
            restorePlane(x);
            moveBird();
        }
    }


    private void createNewBird(int index) {
        int randomY = new Random().nextInt(3);
        if (index != -1 && randomY >= 0 && randomY <= 2) {
            while (randomY == index) {
                randomY = new Random().nextInt(3);
            }
            Glide.with(this)
                    .load(R.drawable.bird)
                    .fitCenter()
                    .into(gameBoard[1][randomY]);
            birds[1][randomY] = 1;
        } else if (index == -1 && randomY >= 0 && randomY <= 2) {
            Glide.with(this)
                    .load(R.drawable.bird)
                    .fitCenter()
                    .into(gameBoard[1][randomY]);
            birds[1][randomY] = 1;
        }
    }

    private void availableToCreateNewBird() {
        if (birds[3][0] == 1) {
            if (birds[2][1] == 1) {
                createNewBird(2);
            } else if (birds[1][2] == 1) {
                createNewBird(1);
            } else {
                createNewBird(-1);
            }
        } else if (birds[3][1] == 1) {
            if (birds[2][0] == 1) {
                createNewBird(2);
            } else if (birds[2][2] == 1) {
                createNewBird(0);
            } else {
                createNewBird(-1);
            }

        } else if (birds[3][2] == 1) {
            if (birds[2][0] == 1) {
                createNewBird(1);
            } else if (birds[2][1] == 1) {
                createNewBird(0);
            } else {
                createNewBird(-1);
            }
        }
    }

    private void moveBird() {
        for (int i = yLength - 2; i >= 1; i--) {
            for (int j = xLength - 1; j >= 0; j--) {
                if (birds[i][j] == 1) {
                    moveDownBird(i, j);
                }
            }
        }
        availableToCreateNewBird();
    }

    private void vanishBird(int yScale, int xScale) {
        Glide.with(this)
                .load("")
                .fitCenter()
                .into(gameBoard[yScale][xScale]);
        birds[yScale][xScale] = 0;
    }

    private void restoreBird(int yScale, int xScale) {
        Glide.with(this)
                .load(R.drawable.bird)
                .fitCenter()
                .into(gameBoard[yScale][xScale]);
        birds[yScale][xScale] = 1;
    }

    private void crashToast() {
        Toast.makeText(this, "Birds attacked you", Toast.LENGTH_LONG)
                .show();
    }

    private void vibrateAll() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    /**
     * @param i = where the
     * @param j
     * @return false if didn't crash, and true if crash
     */
    private boolean checkIfCrash(int i, int j) {
        if (i == 10 && gamePlayer.getX() == j) {
            int crash = gamePlayer.getNumOfCrash();
            if (crash < gamePlayer.getLife()) {
                crash++;
                gamePlayer.setNumOfCrash(crash);
                if (gamePlayer.getNumOfCrash() != 0) {
                    game_IMG_hearts[game_IMG_hearts.length - gamePlayer.getNumOfCrash()].setVisibility(View.INVISIBLE);
                }
            }
            vanishPlane(j);
//            explosion(j);
            vanishBird(i, j);
            vibrateAll();
            crashToast();
            restorePlane(j);
            return true;
        }
        return false;
    }

    private void moveDownBird(int i, int j) {
        vanishBird(i, j);
        i++;
        if (i <= 10 && !checkIfCrash(i, j)) {
            restoreBird(i, j);
        }
    }

}