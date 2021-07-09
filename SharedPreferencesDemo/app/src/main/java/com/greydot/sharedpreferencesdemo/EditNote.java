package com.greydot.sharedpreferencesdemo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView titleText = (TextView) findViewById(R.id.titleText);
        TextView descText = (TextView) findViewById(R.id.descText);
        EditText titleBox = (EditText) findViewById(R.id.titleBox);
        EditText descBox = (EditText) findViewById(R.id.descBox);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        Intent intent = getIntent();
        int noteId = intent.getIntExtra("notesId" , -1);

        if(noteId != -1){
            titleBox.setText(MainActivity.arrayList.get(noteId));
        }
        titleBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.i("Title Text : ", s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        descBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("Description Text : ", s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void SaveNote(View view){


    }

    public void CancelNote(View view){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;

            case R.id.addnote:
                Log.i("Menu Item", "Clicked");
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
