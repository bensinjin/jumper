package com.example.benstjohn.jumper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.benstjohn.jumper.util.LevelManager;
import com.example.benstjohn.jumper.R;
import com.example.benstjohn.jumper.component.WindowButton;
import com.example.benstjohn.jumper.entity.LevelScore;
import com.example.benstjohn.jumper.async.LevelScorePersist;

public class Level1Activity extends AppCompatActivity {
    private LevelManager gm = LevelManager.getInstance();
    private final int levelScoreID = 1;
    private final int gameLengthMilis = 20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);

        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT);

        TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);

        final TextView hud = (TextView) findViewById(R.id.hud);


        gm.score = 0;
        gm.possibleScore = 0;
        gm.pointsLeftInPlay = 0;
        // Represents a 3x7 grid.
        // 0's represent empty spots.
        // Other numbers are window durations.
        int[][] grid = new int[][] {
                {2, 3, 1},
                {7, 5, 5},
                {0, 15, 3},
                {0, 5, 0},
                {20, 5, 5},
                {0, 3, 18},
                {0, 0, 5},
                {3, 0, 5},
        };
        // Level grid.
        for (int x = 0; x < grid.length; x ++) {
            // Set up the row.
            TableRow tableRow = new TableRow(getApplicationContext());
            tableRow.setLayoutParams(rowParams);
            for (int y = 0; y < grid[x].length; y ++) {
                // Set up the cell.
                Boolean isEmpty = grid[x][y] == 0 ? true : false;
                Boolean isActive = true;
                WindowButton wb = new WindowButton(
                        getApplicationContext(),
                        grid[x][y] * 1000,
                        1000,
                        isActive,
                        isEmpty);
                // Add cell to the row.
                tableRow.addView(wb);
                if (!isEmpty) {
                    wb.startTimer();
                    gm.possibleScore ++;
                    gm.pointsLeftInPlay ++;
                }
            }
            // Add row to the table.
            table.addView(tableRow);
        }
        // Level timer.
        new CountDownTimer(gameLengthMilis, 1000) {
            public void onTick(long millisUntilFinished) {
                hud.setText(gm.pointsLeftInPlay + " jumpers left!!!");
                if (gm.pointsLeftInPlay == 0) {
                    cancel();
                    onFinish();
                }
            }

            public void onFinish() {
                hud.setText("Game over! Score: " + gm.score + "/" + gm.possibleScore);
                LevelScore ls = new LevelScore(levelScoreID, gm.score, gm.possibleScore);
                new LevelScorePersist(getApplicationContext(),ls).execute();
            }
        }.start();
    }

    public void backToLevelSelect(View view) {
        Intent i = new Intent(this, _MainActivity.class);
        startActivity(i);
    }
}
