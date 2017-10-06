package com.example.benstjohn.jumper.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.benstjohn.jumper.R;
import com.example.benstjohn.jumper.async.LevelScoreFetch;

public class _MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LevelScoreFetch(this.getApplicationContext(), 1, (TextView)findViewById(R.id.score1)).execute();
        new LevelScoreFetch(this.getApplicationContext(), 2, (TextView)findViewById(R.id.score2)).execute();
    }

    public void startLevel1(View view) {
        Intent i = new Intent(this, Level1Activity.class);
        startActivity(i);
    }

    public void startLevel2(View view) {
        Intent i = new Intent(this, Level2Activity.class);
        startActivity(i);
    }
}
