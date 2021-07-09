package com.android.timerdroid;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000,1000){

            public void onTick(long millisecondsleft){
                Log.i("Time Remaining",String.valueOf(millisecondsleft / 1000));
            }
            public void onFinish(){
                Log.i("Great","Timeover");

            }
        }.start();


/*        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getApplicationContext(), "This is a runnable", Toast.LENGTH_SHORT).show();
                handler.postDelayed(this,3000);
            }
        };

        handler.post(runnable);
    */
    }
}
