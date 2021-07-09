package com.android.XOXO;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean isActive = true;
    LinearLayout playagain;
    TextView playagainTitle;

    public void dropIn(View view) {

        ImageView drop = (ImageView) view;
        int tapped = Integer.parseInt(drop.getTag().toString());
        if (gameState[tapped] == 2 && isActive == true) {
            gameState[tapped] = activePlayer;
            drop.setVisibility(View.INVISIBLE);
            if (activePlayer == 0) {
                drop.setImageResource(R.drawable.tick);
                activePlayer = 1;
            } else {
                drop.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }
            drop.setVisibility(View.VISIBLE);
            drop.animate().scaleXBy(0.05f).scaleYBy(0.05f).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    isActive = false;
                    playagainTitle.setText("PLAYER 1 HAS WON");
                    if (gameState[winningPosition[0]] == 1) {
                        playagainTitle.setText("PLAYER 2 HAS WON");
                    }
                    playagain.setVisibility(View.VISIBLE);
                }
                    else {
                        boolean gameOver = true;
                        for (int countState : gameState) {
                            if (countState == 2) {
                                gameOver = false;
                            }
                        }
                        if(gameOver){
                            playagainTitle.setText("IT'S A DRAW");
                            playagain.setVisibility(View.VISIBLE);
                        }
                    }

                }
            }
        }

    public void playagain(View view){
        isActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length;i++){
            gameState[i]=2;
        }
        LinearLayout playagain = (LinearLayout) findViewById(R.id.playagainwindow);
        playagain.setVisibility(View.INVISIBLE);
        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
        for (int j = 0; j<grid.getChildCount();j++){
            ((ImageView) grid.getChildAt(j)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playagain = (LinearLayout) findViewById(R.id.playagainwindow);
        playagainTitle = (TextView) findViewById(R.id.playagaintitle);
    }
}
