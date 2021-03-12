package com.example.whacktheicon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;

import android.content.Intent;

import android.graphics.Color;
import android.graphics.Point;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.database.AppDatabase;
import com.database.DataBasePlayer;
import com.model.Player;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends AppCompatActivity {

    private final String STRING_TIME_IS_OVER = "OVER!";

    private Integer timeNumber = 10;
    private Integer scoreNumber = 0;

    private TextView timeValueTW;
    private TextView scoreValueTW;

    private Button submitButton;

    private ConstraintLayout constraintLayout;

    private ImageView imageToSmash;
    private ImageView hole0;
    private ImageView hole1;
    private ImageView hole2;
    private ImageView hole3;
    private ImageView hole4;
    private ImageView hole5;
    private ImageView hole6;
    private ImageView hole7;
    private ImageView hole8;

    private Point size;
    private Display display;

    private Random random;
    private Timer timer = new Timer();

    private Boolean endGame;

    AppDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        db =  Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        setContentView(R.layout.activity_game);

        timeValueTW = findViewById(R.id.textViewTimeValue);
        imageToSmash = findViewById(R.id.imageToSmash);
        scoreValueTW = findViewById(R.id.scoreValueTW);
        constraintLayout = findViewById(R.id.toolBarLayout);

        hole0 = findViewById(R.id.hole0);
        hole1 = findViewById(R.id.hole1);
        hole2 = findViewById(R.id.hole2);
        hole3 = findViewById(R.id.hole3);
        hole4 = findViewById(R.id.hole4);
        hole5 = findViewById(R.id.hole5);
        hole6 = findViewById(R.id.hole6);
        hole7 = findViewById(R.id.hole7);
        hole8 = findViewById(R.id.hole8);


        scoreValueTW.setText(String.valueOf(scoreNumber));
        timeValueTW.setText(String.valueOf(timeNumber));

        size = new Point();
        random = new Random();

        endGame = false;

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

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(timeNumber <= 0){


                                AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this).create();
                                LayoutInflater inflater = GameActivity.this.getLayoutInflater();
                                final View dialogView = inflater.inflate(R.layout.layout_for_alert_dialog, null);
                                alertDialog.setView(dialogView);
                                alertDialog.show();

                                submitButton = dialogView.findViewById(R.id.submit_button1);

                                submitButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        EditText editText = dialogView.findViewById(R.id.playerName);
                                        String name = editText.getText().toString();
                                        TextView textView = findViewById(R.id.scoreValueTW);
                                        Integer score = Integer.parseInt(textView.getText().toString());

                                        addPlayer(name,score);

                                        Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                        startActivity(intent);

                                    }
                                });

                            }
                        }

                    });

                    endGame = true;
                    timeValueTW.setText(STRING_TIME_IS_OVER);
                    this.cancel();

                }
            }
        },6, 1000);



        imageToSmash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!endGame){

                    scoreNumber ++;
                    scoreValueTW.setText("");
                    scoreValueTW.setText(String.valueOf(scoreNumber));


                    int holePos = random.nextInt(9);

                    switch (holePos){

                        case 0:{
                            imageToSmash.setX(hole0.getX() + (float)(hole0.getWidth()/6.5));
                            imageToSmash.setY(hole0.getY());
                            break;
                        }

                        case 1:{
                            imageToSmash.setX(hole1.getX() + (float)(hole1.getWidth()/6.5));
                            imageToSmash.setY(hole1.getY() + 1);
                            break;
                        }

                        case 2:{
                            imageToSmash.setX(hole2.getX() + (float)(hole2.getWidth()/6.5));
                            imageToSmash.setY(hole2.getY() + 1);
                            break;
                        }

                        case 3:{
                            imageToSmash.setX(hole3.getX() + (float)(hole3.getWidth()/6.5));
                            imageToSmash.setY(hole3.getY() + 1);
                            break;
                        }

                        case 4:{
                            imageToSmash.setX(hole4.getX() + (float)(hole4.getWidth()/6.5));
                            imageToSmash.setY(hole4.getY() + 1);
                            break;
                        }

                        case 5:{
                            imageToSmash.setX(hole5.getX() + (float)(hole5.getWidth()/6.5));
                            imageToSmash.setY(hole5.getY() + 1);
                            break;
                        }

                        case 6:{
                            imageToSmash.setX(hole6.getX() + (float)(hole6.getWidth()/6.5));
                            imageToSmash.setY(hole6.getY() + 1);
                            break;
                        }

                        case 7:{
                            imageToSmash.setX(hole7.getX() + (float)(hole7.getWidth()/6.5));
                            imageToSmash.setY(hole7.getY() + 1);
                            break;
                        }

                        case 8:{
                            imageToSmash.setX(hole8.getX() + (float)(hole8.getWidth()/6.5));
                            imageToSmash.setY(hole8.getY() + 1);
                            break;
                        }

                        default:{
                            imageToSmash.setX(hole0.getX() + (float)(hole0.getWidth()/6.5));
                            imageToSmash.setY(hole0.getY() + 1);
                            break;
                        }

                    }

                }
            }
        });


    }

    private void addPlayer(String name, Integer score){

        final String playerName = name;
        final Integer playerScore = score;

        class AddPlayer extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {

                Player player = new Player();
                player.setPlayerScore(playerScore);
                player.setPlayerName(playerName);

                DataBasePlayer.getInstance(getApplicationContext()).getAppDatabase().playerDao().insertPlayer(player);
                return  null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //finish();
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
            }
        }

        AddPlayer addTask = new AddPlayer();
        addTask.execute();

    }


}


