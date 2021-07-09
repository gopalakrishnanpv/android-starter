package com.android.braintrainer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView timerText;
    TextView questionText;
    Button playagain;
    int answerLoc;
    TextView Scoretext;
    String scoreBoard;
    TextView result;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int score=0;
    int NumofQues=0;
    Button Option1;
    Button Option2;
    Button Option3;
    Button Option4;
    GridLayout grid;
    TextView Gameover;
    TextView Greeting;

    public void goclicked(View view){

        goButton.setVisibility(View.INVISIBLE);
//        countdown(null);
    }
    public void generateQuestion(View view) {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        questionText.setText(String.valueOf(a) + " + " + String.valueOf(b));
        int ca = a + b;
        answerLoc = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for (int i = 0; i < 4; i++) {
            if (i == answerLoc) {
                answers.add(ca);
            } else {

                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }
        }

        Option1.setText(String.valueOf(answers.get(0)));
        Option2.setText(String.valueOf(answers.get(1)));
        Option3.setText(String.valueOf(answers.get(2)));
        Option4.setText(String.valueOf(answers.get(3)));
    }

    public void countdown(View view){

        new CountDownTimer(29900,1000){

            public void onTick(long milliSeconds){
                int seconds = (int) milliSeconds/1000;
                String secondsString = String.valueOf(seconds);
                timerText.setText(secondsString+" SEC");
            }
            public void onFinish(){
                timerText.setText("0 SEC");
                result.setTextColor(Color.parseColor("#2980b9"));
                result.setVisibility(View.VISIBLE);
                playagain.setVisibility(View.VISIBLE);
                grid.setVisibility(View.INVISIBLE);
                Gameover.setVisibility(View.VISIBLE);
                timerText.setVisibility(View.INVISIBLE);
                questionText.setVisibility(View.INVISIBLE);
                Scoretext.setVisibility(View.INVISIBLE);
                Greeting.setVisibility(View.VISIBLE);
                if(NumofQues==0){
                    result.setText("YOU SCORED : 0 / 0");
                }
                else {
                    result.setText("YOU SCORED : " + scoreBoard);
                }
            }

        }.start();
    }

    public void clicked(View view){
        if(view.getTag().toString().equals(Integer.toString(answerLoc))){
            result.setText("CORRECT");
            result.setTextColor(Color.parseColor("#38bd1a"));
            result.setVisibility(View.VISIBLE);
            score++;

        }
        else{
            result.setText("WRONG");
            result.setTextColor(Color.parseColor("#cc0000"));
            result.setVisibility(View.VISIBLE);
        }

        NumofQues++;
        scoreBoard = String.valueOf(score)+" / "+String.valueOf(NumofQues);
        Scoretext.setText(scoreBoard);
        generateQuestion(null);
    }

    public void PlayAgain(View view){

        result.setVisibility(View.INVISIBLE);
        playagain.setVisibility(View.INVISIBLE);
        score = 0;
        NumofQues = 0;
        scoreBoard = "0 / 0";
        Scoretext.setText(scoreBoard);
        grid.setVisibility(View.VISIBLE);
        Gameover.setVisibility(View.INVISIBLE);
        timerText.setVisibility(View.VISIBLE);
        questionText.setVisibility(View.VISIBLE);
        Scoretext.setVisibility(View.VISIBLE);
        Greeting.setVisibility(View.INVISIBLE);
        generateQuestion(null);
        countdown(null);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.gobutton);
        timerText = (TextView) findViewById(R.id.timer);
        questionText = (TextView) findViewById(R.id.question);
        Scoretext = (TextView) findViewById(R.id.score);
        playagain = (Button) findViewById(R.id.playagain);
        Option1 = (Button) findViewById(R.id.option1);
        Option2 = (Button) findViewById(R.id.option2);
        Option3 = (Button) findViewById(R.id.option3);
        Option4 = (Button) findViewById(R.id.option4);
        result = (TextView) findViewById(R.id.result);
        grid = (GridLayout) findViewById(R.id.gridLayout);
        Gameover = (TextView) findViewById(R.id.gameover);
        Gameover.setVisibility(View.INVISIBLE);
        Greeting = (TextView) findViewById(R.id.greeting);
        Greeting.setVisibility(View.INVISIBLE);
        generateQuestion(null);
        countdown(null);


/*        timerText.setVisibility(View.INVISIBLE);
        scoreText.setVisibility(View.INVISIBLE);
        questionText.setVisibility(View.INVISIBLE);
        options.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);*/

    }
}
