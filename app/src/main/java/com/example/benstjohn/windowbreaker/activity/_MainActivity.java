package com.example.benstjohn.windowbreaker.activity;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.benstjohn.windowbreaker.R;
import com.example.benstjohn.windowbreaker.async.LevelScoreFetch;
import com.example.benstjohn.windowbreaker.database.AppDatabase;
import com.example.benstjohn.windowbreaker.entity.LevelScore;

public class _MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LevelScoreFetch(this.getApplicationContext(), 1, (EditText)findViewById(R.id.editText)).execute();
    }

    public void startLevel1(View view) {
        Intent i = new Intent(this, Level1Activity.class);
        startActivity(i);
    }
}
