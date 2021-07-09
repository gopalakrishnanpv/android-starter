package com.android.sync;


import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener{

    String sendResult;
    String Fnamestring;
    String Snamestring;
    ProgressBar progressBar;
    EditText firstName;
    EditText secondName;
    static Button syncButton;
    ImageView headerImage;
    TextView titleText;
    ImageView codeImage;
    RelativeLayout relativeLayout;

    public void Sync(View view) {
        firstName.setOnKeyListener(this);
        secondName.setOnKeyListener(this);
        Fnamestring = firstName.getText().toString().toUpperCase().trim();
        Snamestring = secondName.getText().toString().toUpperCase().trim();

        Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
        Matcher f = p.matcher(Fnamestring);
        boolean a = f.find();
        Matcher s = p.matcher(Snamestring);
        boolean b = s.find();

        if (Fnamestring.equals(null) || Fnamestring.equals("") || Fnamestring.length()>24 || a == true) {
            Toast.makeText(getApplicationContext(), "Oops! That was an error \nYour name is Invalid", Toast.LENGTH_SHORT).show();
        } else if (Snamestring.equals(null) || Snamestring.equals("") || Snamestring.length()>24 || b == true) {
            Toast.makeText(getApplicationContext(), "Oops! That was an error \nYour partner name is Invalid", Toast.LENGTH_SHORT).show();
        } else {
        char[] FnameArray = Fnamestring.toCharArray();
        char[] SnameArray = Snamestring.toCharArray();
        int Fnametotal = 0;
        int Snametotal = 0;
        int total = 4;
        for (int i = 0; i < FnameArray.length; i++) {
            int Fnamevalue = Character.getNumericValue(FnameArray[i]);
            Fnametotal = Fnametotal + Fnamevalue;
        }

        for (int j = 0; j < SnameArray.length; j++) {
            int Snamevalue = Character.getNumericValue(SnameArray[j]);
            Snametotal = Snametotal + Snamevalue;
        }

        if (Fnametotal > Snametotal) {
            total = Fnametotal - Snametotal;
        } else if (Snametotal > Fnametotal) {
            total = Snametotal - Fnametotal;
        }

        String[] Flames = {"FRIENDSHIP", "LOVE", "AFFECTION", "MARRIAGE", "ENEMIES", "SIBLINGS"};
        int totalfinal = total % Flames.length;

            syncButton.setEnabled(false);
            sendResult = Flames[totalfinal];
            progressBar.setVisibility(View.VISIBLE);
            new CountDownTimer(1000,10){
                public void onTick(long milliSeconds){
                    progressBar.setProgress(1000- (int) (milliSeconds));
                }
                public void onFinish(){
                    progressBar.setVisibility(View.INVISIBLE);
                    Resultactivity(null);

                }

            }.start();

        }

    }

    public void Resultactivity(View view){

        Intent i = new Intent(getApplicationContext(),ResultPage.class);
        i.putExtra("Result",sendResult);
        i.putExtra("Fname",Fnamestring);
        i.putExtra("Sname",Snamestring);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.drawable.download);
        menu.setDisplayUseLogoEnabled(true);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        headerImage = (ImageView) findViewById(R.id.headerImage);
        titleText = (TextView) findViewById(R.id.titletext);
        codeImage = (ImageView) findViewById(R.id.codeimage);
        relativeLayout.setOnClickListener(this);
        headerImage.setOnClickListener(this);
        titleText.setOnClickListener(this);
        codeImage.setOnClickListener(this);
        firstName = (EditText) findViewById(R.id.firstname);
        secondName = (EditText) findViewById(R.id.secondname);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        syncButton = (Button) findViewById(R.id.syncbutton);
        syncButton.setEnabled(true);

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_Menu) {
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

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_ENTER){

            Sync(v);
        }
        return false;

    }

    @Override
    public void onClick(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
}
