package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 player X
    // 1 player O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] win = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    public void dropin(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter =  Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] ==2 && gameActive) {
            gameState[tappedCounter] = activePlayer;

            //posisinya di atasin biar bisa animasi
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;

            }
            // animasi jatuh dari atas
            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] win : win) {
                if (gameState[win[0]] == gameState[win[1]] && gameState[win[1]] == gameState[win[2]] && gameState[win[0]] != 2) {
                    //Player Won
                    String winner = "";
                    gameActive = false;
                    if (activePlayer == 1) {
                        winner = "Player 2";

                    } else {
                        winner = "Player 1";
                    }

                    Button playButton = (Button) findViewById(R.id.playButton);
                    TextView winText = (TextView) findViewById(R.id.winText);

                    winText.setText(winner+ " has Won!");

                    playButton.setVisibility(View.VISIBLE);
                    winText.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        Button playButton = (Button) findViewById(R.id.playButton);
        TextView winText = (TextView) findViewById(R.id.winText);

        playButton.setVisibility(View.INVISIBLE);
        winText.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid_layout);

        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i=0;i<gameState.length;i++){
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}