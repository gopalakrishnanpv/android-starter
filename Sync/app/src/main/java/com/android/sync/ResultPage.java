package com.android.sync;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultPage extends AppCompatActivity {

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
        String Result = intent.getStringExtra("Result");
        TextView FirstName = (TextView) findViewById(R.id.firname);
        TextView SecondName = (TextView) findViewById(R.id.secname);
        FirstName.setText(firstName);
        SecondName.setText(secondName);
        Log.i("Firstname",firstName);
        Log.i("Secondname",secondName);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        switch(Result){
            case "FRIENDSHIP":
                imageView.setImageResource(R.drawable.friends);
                break;
            case "LOVE":
                imageView.setImageResource(R.drawable.lovers);
                break;
            case "AFFECTION":
                imageView.setImageResource(R.drawable.affection);
                break;
            case "MARRIAGE":
                imageView.setImageResource(R.drawable.marriage);
                break;
            case "ENEMIES":
                imageView.setImageResource(R.drawable.enemies);
                break;
            case "SIBLINGS":
                imageView.setImageResource(R.drawable.siblings);
                break;
            default:
                imageView.setImageResource(R.drawable.friends);

        }
        TextView result = (TextView) findViewById(R.id.result);
        result.setText(Result);

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_Menu) {
            Intent intent = new Intent(this, MainActivity.class);
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
}
