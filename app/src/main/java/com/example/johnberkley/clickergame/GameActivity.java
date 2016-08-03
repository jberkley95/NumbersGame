package com.example.johnberkley.clickergame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Button plusButton, minusButton, checkButton, resetButton;
    TextView numberView, titleView;

    int currentNumber, correctValue, tries;

    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        r = new Random();
        correctValue = r.nextInt(100) + 1;

        plusButton = (Button) findViewById(R.id.btn_increase);
        minusButton = (Button) findViewById(R.id.btn_decrease);
        checkButton = (Button) findViewById(R.id.btn_check);
        resetButton = (Button) findViewById(R.id.btn_reset);
        resetButton.setEnabled(false);

        numberView = (TextView) findViewById(R.id.tv_guess);
        titleView = (TextView) findViewById(R.id.tv_guessTitle);

        tries = 0;
        currentNumber = 0;
        numberView.setText(String.valueOf(currentNumber));

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentNumber == correctValue) {
                    titleView.setText("You guessed in " + tries + " tries.");
                    plusButton.setEnabled(false);
                    minusButton.setEnabled(false);
                    checkButton.setEnabled(false);
                    resetButton.setEnabled(true);
                } else if (currentNumber > correctValue){
                    titleView.setText("Guess Lower!");
                    tries++;
                } else if (currentNumber < correctValue){
                    titleView.setText("Guess Higher!");
                    tries++;
                }
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentNumber < 100) {
                    currentNumber++;
                }
                numberView.setText(String.valueOf(currentNumber));
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentNumber > 0) {
                    currentNumber--;
                }
                numberView.setText(String.valueOf(currentNumber));
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctValue = r.nextInt(101);
                currentNumber = 0;
                numberView.setText(String.valueOf(currentNumber));
                titleView.setText("Guess A Number");

                plusButton.setEnabled(true);
                minusButton.setEnabled(true);
                checkButton.setEnabled(true);
                resetButton.setEnabled(false);

                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }
}
