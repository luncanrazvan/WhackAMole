package com.example.whacktheicon;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private final String STRING_TIME_IS_OVER = "TIME IS OVER!";

    private Integer timeNumber = 10;
    private Integer scoreNumber = 0;

    private TextView timeValueTW;
    private TextView scoreValueTW;

    private ImageView imageToSmash;
    private Point size;
    private Display display;

    private Random random;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        timeValueTW = findViewById(R.id.textViewTimeValue);
        imageToSmash = findViewById(R.id.imageToSmash);
        scoreValueTW = findViewById(R.id.scoreValueTW);
        scoreValueTW.setText(String.valueOf(scoreNumber));
        timeValueTW.setText(String.valueOf(timeNumber));

        size = new Point();
        random = new Random();

        display = getWindowManager().getDefaultDisplay();
        display.getSize(size);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(timeNumber > 0){
                    timeNumber --;
                    timeValueTW.setText("");
                    if(timeNumber <= 5){
                        timeValueTW.setTextColor(Color.RED);
                    }
                    timeValueTW.setText(String.valueOf(timeNumber));
                }else{
                    timeValueTW.setText(STRING_TIME_IS_OVER);
                    this.cancel();
                }
            }
        },6, 1000);

        imageToSmash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scoreNumber ++;
                scoreValueTW.setText("");
                scoreValueTW.setText(String.valueOf(scoreNumber));

                int newWidth = random.nextInt(size.x - 1);
                int newHeight = random.nextInt(size.y - 1);
                imageToSmash.setX(newWidth);
                imageToSmash.setY(newHeight);

            }
        });
    }
}
