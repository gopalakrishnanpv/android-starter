package com.android.randomnumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int randomNumber;

    public void guessIt(View view) {
        EditText userInput = (EditText) findViewById(R.id.inputbox);
        int inputNumber = Integer.parseInt(userInput.getText().toString());
        String message = "";
        if(inputNumber < randomNumber){
            message = "Too Less";
        }
        else if(inputNumber > randomNumber){
            message = "Too High";
        }
        else if(inputNumber == randomNumber){
            message = "Correct Welldone";
        }

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random randomvalue = new  Random();
        randomNumber = randomvalue.nextInt(11);

    }
}
