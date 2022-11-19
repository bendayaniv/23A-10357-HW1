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

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    final int DELAY = 1000;
    private final int yLength = 12;
    private final int xLength = 3;
    private final int boardLimit = 10;
    private final int stepRightOfPlane = 1;
    private final int stepLeftOfPlane = -1;

    private ExtendedFloatingActionButton gameActivity_FAB_left;
    private ExtendedFloatingActionButton gameActivity_FAB_right;
    private ShapeableImageView[][] gameBoard;
    private ShapeableImageView[] game_IMG_hearts;

    private ManagerActivity gameManager;
    private Timer timer;
    long startTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        findViews();
        createButtons();
        gameManager = new ManagerActivity(game_IMG_hearts.length);
        gameManager.createNewBird(-1);

        startGame();
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
        gameActivity_FAB_right.setOnClickListener(v -> movePlaneRight());
        gameActivity_FAB_left.setOnClickListener(v -> movePlaneLeft());
    }

    private void loadImage(int imageNum, ShapeableImageView imageView) {
        Glide.with(this).load(imageNum).fitCenter().into(imageView);
    }

    private void deleteImage(ShapeableImageView imageView) {
        Glide.with(this).clear(imageView);
    }

    private void movePlaneRight() {
        int x = gameManager.getPlane().getX();
        vanishPlane(x);
        gameManager.movePlane(stepRightOfPlane);
        x = gameManager.getPlane().getX();
        restorePlane(x);
    }

    private void movePlaneLeft() {
        int x = gameManager.getPlane().getX();
        vanishPlane(x);
        gameManager.movePlane(stepLeftOfPlane);
        x = gameManager.getPlane().getX();
        restorePlane(x);
    }

    private void vanishPlane(int xScale) {
        deleteImage(gameBoard[boardLimit][xScale]);
    }

    private void restorePlane(int xScale) {
        loadImage(gameManager.getPlane().getPlaneImage(), gameBoard[boardLimit][xScale]);
    }

    /**
     * Clean the game board and the list of birds
     */
    private void initializationBoard() {
        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                Glide.with(this).clear(gameBoard[i][j]);
            }
        }
        gameManager.getBirds().clear();
    }

    private void moveAllBirds() {
        gameManager.moveBirdsDown(boardLimit);
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

    private void stopGame() {
        timer.cancel();
    }

    private void startGame() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    if (!gameManager.checkIfCrash()) {
                        refreshUI();
                    } else {
                        if (gameManager.getPlane().getNumOfCrash() != 0) {
                            game_IMG_hearts[game_IMG_hearts.length - gameManager.getPlane().getNumOfCrash()].setVisibility(View.INVISIBLE);
                        }
                        crashToast();
                        vibrateAll();
                        gameManager.getBirds().clear();
                        loadImage(gameManager.getPlane().getExplodeImage(), gameBoard[gameManager.getPlane().getY()][gameManager.getPlane().getX()]);
                    }
                    stopGame();
                    startGame();
                });
            }
        }, DELAY, DELAY);
        startTime = System.currentTimeMillis();
    }

    private void refreshUI() {
        long millis = System.currentTimeMillis() - startTime;
        int seconds = (int) (millis / 1000);
        seconds = seconds % 60;

        initializationBoard();
        moveAllBirds();
        vanishPlane(gameManager.getPlane().getX());
        gameManager.movePlane(0);
        restorePlane(gameManager.getPlane().getX());

        if (seconds % 3 == 0) {
            gameManager.createNewBird(-1);
        }
    }

}