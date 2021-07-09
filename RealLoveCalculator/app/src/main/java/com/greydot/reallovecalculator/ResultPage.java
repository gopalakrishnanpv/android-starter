package com.greydot.reallovecalculator;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class ResultPage extends AppCompatActivity {
    TextView result;
    TextView FirstName;
    TextView SecondName;
    TextView quote;
    ImageView loveIcon;
    String[] quotes = null;
    int Result=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_result_page);
        MainActivity.syncButton.setEnabled(true);
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("Fname");
        String secondName = intent.getStringExtra("Sname");
        Result = intent.getIntExtra("Result", 0);
        FirstName = (TextView) findViewById(R.id.firname);
        SecondName = (TextView) findViewById(R.id.secname);
        result = (TextView) findViewById(R.id.result);
        loveIcon = (ImageView) findViewById(R.id.imageView);
        quote = (TextView) findViewById(R.id.quote);
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto-Bold.ttf");
        FirstName.setTypeface(font);
        SecondName.setTypeface(font);
        quote.setTypeface(font);
        try {
            getQuote(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirstName.setText(firstName);
        SecondName.setText(secondName);
        Log.i("Firstname", firstName);
        Log.i("Secondname", secondName);
        Log.i("Result", String.valueOf(Result));

        try {
            animateTextView(0, Result, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getQuote(View view) throws IOException {
        InputStream in = null;
        BufferedReader reader = null;
        String quotesCollection = "";
        String str="";

            in = getResources().openRawResource(R.raw.quote);
            reader = new BufferedReader(new InputStreamReader(in));
        if (reader != null) {
            while (reader.readLine() != null) {
                quotesCollection = quotesCollection.concat(reader.readLine() + "\n");
            }
            quotesCollection = quotesCollection.toString();
            in.close();
            }
                if (quotesCollection.contains(".")) {
                    quotes = quotesCollection.split("\\.");
                }
                Random random = new Random();
                int randomSelection = random.nextInt(quotes.length);
                if(quotes[randomSelection].length() >= 5) {
                    quote.setText(quotes[randomSelection].toUpperCase());
                } else {
                    quote.setText(quotes[2].toUpperCase());
                }

            }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.home) {
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }

        if (id == R.id.action_Menu) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }


        if (id == R.id.about) {
            Intent intent = new Intent(this, About.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.help) {
            Intent intent = new Intent(this, Help.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater findMenuItems = getMenuInflater();
        findMenuItems.inflate(R.menu.resultmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void animateTextView(int initialValue, int finalValue, final TextView textview) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(initialValue, finalValue);
            valueAnimator.setDuration(5000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        textview.setText(valueAnimator.getAnimatedValue().toString() + " %");
                    }
                }
            });
            valueAnimator.start();
        } else {
            textview.setText(String.valueOf(Result) + " %");

        }
    }
}
