package com.example.asmthreaddemo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Logger;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        String test = getIntent().getStringExtra("test");

        String test = IntentHeloer.getSafeIntent(getIntent(), "test");

    }

}
