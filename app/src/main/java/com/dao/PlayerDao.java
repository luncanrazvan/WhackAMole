package com.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.model.Player;

import java.util.List;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM players ORDER BY player_score DESC")
    List<Player> getAllPlayers();

    @Insert
    void insertPlayer(Player... players);

}
