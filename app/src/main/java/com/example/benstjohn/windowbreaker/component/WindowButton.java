package com.example.benstjohn.windowbreaker.component;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.View;

import com.example.benstjohn.windowbreaker.util.LevelManager;

import java.util.Random;

/**
 * Created by benstjohn on 2017-09-22.
 *
 * TODO This will only work on older devices by extending Button - find out why/work arounds.
 */

public class WindowButton extends AppCompatButton {
    private CountDownTimer timer;
    private boolean isActive;
    private long millisRemaining;
    private boolean isEmpty;
    private LevelManager gm = LevelManager.getInstance();

    public WindowButton(Context context, final long millisInFuture, long countDownInterval, boolean isActive, boolean isEmpty) {
        super(context);
        this.isActive = isActive;
        this.isEmpty = isEmpty;
        // TODO this is lazy ... also put strings (colors etc) in strings.xml ... Learn how to properly style buttons etc ...
        setMinWidth(300);
        setTextSize(30);
        setGravity(Gravity.CENTER);
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.parseColor("#a5a3a3"));
        gd.setCornerRadius(5);
        gd.setStroke(1, Color.parseColor("#000000"));
        setBackgroundDrawable(gd);

        if (!isEmpty) {
            // Set up timer.
            timer = new CountDownTimer(millisInFuture, countDownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (WindowButton.this.isActive) {
                        WindowButton.this.millisRemaining = millisUntilFinished;
                        WindowButton.this.setText("" + WindowButton.this.millisRemaining / 1000);
                    }
                }

                @Override
                public void onFinish() {
                    if (WindowButton.this.isActive) {
                        WindowButton.this.setText("x_x");
                        gm.pointsLeftInPlay --;

                    }
                    WindowButton.this.isActive = false;
                }
            };
            // Set on click.
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (WindowButton.this.isActive && WindowButton.this.millisRemaining > 0) {
                        WindowButton.this.isActive = false;
                        WindowButton.this.setText("^_^");
                        gm.score ++;
                        gm.pointsLeftInPlay --;
                    }
                }
            });
        }
    }

    public static int getRandomInt (int lowerBound, int upperBound) {
        Random r = new Random();
        return r.nextInt(upperBound - lowerBound) + lowerBound;
    }

    public void startTimer() {
        timer.start();
    }

}
