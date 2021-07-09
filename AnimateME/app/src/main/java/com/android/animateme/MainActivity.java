package com.android.animateme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView appleImage;
    ImageView androidImage;

    public void changeAndroid(View view){

        appleImage.animate().alpha(1f).setDuration(1000);
        androidImage.animate().alpha(0f).setDuration(1000);
    }
    public void changeApple(View view){


        appleImage.animate().alpha(0f).setDuration(1000);
        androidImage.animate().alpha(1f).setDuration(1000);
    }

    public void TranslateX(View view){

        androidImage.animate().translationX(-1000f).setDuration(2000);
        androidImage.animate().rotation(360f).setDuration(2000);
    }

    public void TranslateY(View view){

        androidImage.animate().translationY(-1000f).setDuration(2000);
    }

    public void Rotation(View view){

        androidImage.animate().rotationBy(360f).setDuration(2000);

    }
    public void Scale(View view){

        androidImage.animate().scaleX(0.5f).setDuration(2000);
        androidImage.animate().scaleY(0.5f).setDuration(2000);
    }
    public void reset(View view){
        androidImage.setAlpha(1f);
        appleImage.setAlpha(0f);
        androidImage.setTranslationX(0f);
        androidImage.setTranslationY(0f);
        androidImage.setRotationX(0f);
        androidImage.setRotationY(0f);
        androidImage.setScaleX(1f);
        androidImage.setScaleY(1f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appleImage = (ImageView) findViewById(R.id.apple);
        androidImage = (ImageView) findViewById(R.id.android);

    }
}
