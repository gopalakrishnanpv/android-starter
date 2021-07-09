package com.android.buttondroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void buttonClick(View view){
        String name;
        String pass;
        EditText username = (EditText) findViewById(R.id.namebox);
        EditText password = (EditText) findViewById(R.id.passwordbox);
        name = username.getText().toString();
        pass = password.getText().toString();
        Log.i("USERNAME","Hello " + name);
        Log.i("PASSWORD", + pass " is your password");
        Toast.makeText(getApplicationContext(),"Hello " + name,Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), pass + " is your password",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
