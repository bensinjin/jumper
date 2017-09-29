package com.example.benstjohn.windowbreaker.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.benstjohn.windowbreaker.R;

public class _MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startLevel1(View view) {
        Intent i = new Intent(this, Level1Activity.class);
        startActivity(i);
    }
}
