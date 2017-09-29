package com.example.benstjohn.windowbreaker.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.benstjohn.windowbreaker.GameManager;
import com.example.benstjohn.windowbreaker.R;
import com.example.benstjohn.windowbreaker.components.WindowButton;
import com.example.benstjohn.windowbreaker.database.LevelScore;
import com.example.benstjohn.windowbreaker.database.LevelScorer;
import com.example.benstjohn.windowbreaker.database.LevelScorerParams;

public class Level1Activity extends AppCompatActivity {
    private GameManager gm = GameManager.getInstance();
    private final int levelScoreID = 1;

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
                {0, 10, 2},
                {7, 5, 5},
                {0, 15, 20},
                {0, 3, 0},
                {20, 3, 5},
                {0, 20, 18},
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
        new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                hud.setText("We got " + gm.pointsLeftInPlay + " jumpers!!!");
                if (gm.pointsLeftInPlay == 0) {
                    cancel();
                    onFinish();
                }
            }

            public void onFinish() {
                hud.setText("Game over! Score: " + gm.score + "/" + gm.possibleScore);
                LevelScorerParams params =
                        new LevelScorerParams(getApplicationContext(),
                        new LevelScore(levelScoreID, gm.score, gm.possibleScore));

                new LevelScorer().execute(params);
            }
        }.start();
    }
}
