package com.database;

import android.content.Context;

import androidx.room.Room;

public class DataBasePlayer {

    private Context context;

    private static DataBasePlayer dataBasePlayer;

    private AppDatabase appDatabase;

    private DataBasePlayer(Context context){
        this.context = context;
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "player_db").build();
    }

    public static synchronized DataBasePlayer getInstance(Context context){
        if(dataBasePlayer == null){
            dataBasePlayer = new DataBasePlayer(context);
        }
        return dataBasePlayer;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
