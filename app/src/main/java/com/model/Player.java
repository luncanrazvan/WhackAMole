package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "players")
public class Player implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int playerID;

    @ColumnInfo(name = "player_name")
    private String playerName;

    @ColumnInfo(name = "player_score")
    private Integer playerScore;

    public int getPlayerID() {
        return playerID;
    }

    public Integer getPlayerScore() {
        return playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerScore(Integer playerScore) {
        this.playerScore = playerScore;
    }
}
