package com.android.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void currencyConvert(View view){
        EditText input = (EditText) findViewById(R.id.inputbox);
        String pound = input.getText().toString();
        Double poundamount = Double.parseDouble(pound);
        Double rupeeamount = poundamount*89.02;
        String rupee = rupeeamount.toString();
        Toast.makeText(getApplicationContext(),pound + " pound(£) is equal to " + rupee + " rupees(₹)",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
