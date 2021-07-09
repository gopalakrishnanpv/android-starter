package com.android.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar countdownseek;
    TextView countdown;
    boolean countdownactive = false;
    Button gobutton;

    public void updateTime(int secondsleft){

        int minutes = (int) secondsleft/60;
        int seconds = secondsleft - minutes*60;
        String minutesstring = String.valueOf(minutes);
        String secondsstring = String.valueOf(seconds);
       if(seconds<=9){
            secondsstring = "0"+secondsstring;
        }

        countdown.setText(minutesstring+":"+secondsstring);
    }

    public void startCountDown(View view) {

        countdownactive=true;
        countdownseek.setEnabled(false);
        gobutton.setEnabled(false);

        new CountDownTimer(countdownseek.getProgress()*1000+150, 1000) {
            public void onTick(long milliseconds) {

                updateTime((int)milliseconds/1000);

            }
            public void onFinish() {
                countdown.setText("0:00");
                Log.i("Finish", "Time Over");
                countdownseek.setEnabled(true);
                gobutton.setEnabled(true);

            }

        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdown = (TextView) findViewById(R.id.contdowntext);
        countdownseek = (SeekBar) findViewById(R.id.seekBar);
        gobutton = (Button) findViewById(R.id.timerbutton);
        countdownseek.setMax(600);
        countdownseek.setProgress(30);
        countdownseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updateTime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
