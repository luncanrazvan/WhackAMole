package com.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dao.PlayerDao;
import com.model.Player;

@Database(entities = Player.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PlayerDao playerDao();

}
