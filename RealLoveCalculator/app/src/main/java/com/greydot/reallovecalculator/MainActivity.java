package com.greydot.reallovecalculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener , View.OnLongClickListener{
    String Fnamestring;
    String Snamestring;
    EditText firstName;
    EditText secondName;
    static Button syncButton;
    ImageView headerImage;
    RelativeLayout relativeLayout;
    int total;
    int sendResult;
    Button secretButton;
    SeekBar seekBar;
    LinearLayout seekBarLayout;
    TextView seekBarText;
    Button hide;
    int seekBarValue;


    public void Sync(View view) {

            firstName.setOnKeyListener(this);
            secondName.setOnKeyListener(this);
            Fnamestring = firstName.getText().toString().toUpperCase().trim();
            Snamestring = secondName.getText().toString().toUpperCase().trim();
            Pattern p = Pattern.compile("[a-zA-Z0-9]{20}");
            Matcher f = p.matcher(Fnamestring);
            boolean a = f.find();
            Matcher s = p.matcher(Snamestring);
            boolean b = s.find();
            if (Fnamestring.equals(null) || Fnamestring.equals("") || a == true) {
                Toast.makeText(getApplicationContext(), "Oops! That was an error \nYour name is Invalid", Toast.LENGTH_SHORT).show();
            } else if (Snamestring.equals(null) || Snamestring.equals("") ||  b == true) {
                Toast.makeText(getApplicationContext(), "Oops! That was an error \nYour partner name is Invalid", Toast.LENGTH_SHORT).show();
            }
            else {
                if (view.getId() == R.id.syncbutton) {
                    char[] FnameArray = Fnamestring.toCharArray();
                char[] SnameArray = Snamestring.toCharArray();
                int Fnametotal = 0;
                int Snametotal = 0;
                for (int i = 0; i < FnameArray.length; i++) {
                    int Fnamevalue = Character.getNumericValue(FnameArray[i]);
                    Fnametotal = Fnametotal + Fnamevalue;
                }

                for (int j = 0; j < SnameArray.length; j++) {
                    int Snamevalue = Character.getNumericValue(SnameArray[j]);
                    Snametotal = Snametotal + Snamevalue;
                }

                total = Fnametotal + Snametotal;
                int rounded = ((total + 99) / 100);
                Log.i("Rounded", String.valueOf(rounded));
                rounded = rounded * 100;
                Log.i("Rounded * 100", String.valueOf(rounded));
                sendResult = rounded - total;
                Log.i("Total", String.valueOf(total));
                syncButton.setEnabled(false);
                Resultactivity(null);

            }
                else if (view.getId() == R.id.secretbutton) {

                    Intent i = new Intent(getApplicationContext(),ResultPage.class);
                    i.putExtra("Fname",Fnamestring);
                    i.putExtra("Sname",Snamestring);
                    i.putExtra("Result",seekBarValue);
                    startActivity(i);
                }
        }

    }

    public void Resultactivity(View view){

        Intent i = new Intent(getApplicationContext(),ResultPage.class);
        i.putExtra("Fname",Fnamestring);
        i.putExtra("Sname",Snamestring);
        i.putExtra("Result",sendResult);
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
        relativeLayout.setOnClickListener(this);
        headerImage.setOnClickListener(this);
        firstName = (EditText) findViewById(R.id.firstname);
        secondName = (EditText) findViewById(R.id.secondname);
        syncButton = (Button) findViewById(R.id.syncbutton);
        secretButton = (Button) findViewById(R.id.secretbutton);
        syncButton.setEnabled(true);
        syncButton.setOnLongClickListener(this);
        secretButton.setOnLongClickListener(this);
        seekBarLayout = (LinearLayout) findViewById(R.id.seekBarLayout);
        seekBarLayout.setVisibility(View.INVISIBLE);
        seekBarText = (TextView) findViewById(R.id.seekBarText);
        hide = (Button) findViewById(R.id.hideSeekBarLayout);
        Typeface robotoRegular = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface robotoBold = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto-Bold.ttf");
        secretButton.setTypeface(robotoBold);
        firstName.setTypeface(robotoRegular);
        secondName.setTypeface(robotoRegular);
        syncButton.setTypeface(robotoBold);
        seekBarText.setTypeface(robotoBold);
        hide.setTypeface(robotoBold);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
                seekBarValue = progress;
                seekBarText.setText(String.valueOf(seekBarValue) + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            headerImage.setVisibility(View.GONE);
        }


    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_Menu) {
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

    @Override
    public boolean onLongClick(View v) {
        if(v.getId() == R.id.syncbutton){
            syncButton.setVisibility(View.INVISIBLE);
            secretButton.setVisibility(View.VISIBLE);
            seekBarLayout.setVisibility(View.VISIBLE);

        }
        if(v.getId() == R.id.secretbutton){
            syncButton.setVisibility(View.VISIBLE);
            secretButton.setVisibility(View.INVISIBLE);
            seekBarLayout.setVisibility(View.INVISIBLE);


        }
        return true;
    }

    public void HideSeekBarLayout(View view){
        seekBarLayout.setVisibility(View.INVISIBLE);
    }

}
