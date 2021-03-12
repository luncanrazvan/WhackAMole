package com.example.whacktheicon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.database.DataBasePlayer;
import com.model.Player;
import com.recyclerview.RecyclerAdapter;

import java.util.List;

public class ScoreBoardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    //private RecyclerView.Adapter adapter;
    //private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        this.recyclerView = findViewById(R.id.recView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getPlayers();
    }

    private void getPlayers(){
        class GetPlayers extends AsyncTask<Void, Void, List<Player>> {
            @Override
            protected List<Player> doInBackground(Void... voids) {
                List<Player> playerList = DataBasePlayer.getInstance(getApplicationContext()).getAppDatabase()
                        .playerDao().getAllPlayers();
                System.out.println(playerList.size());
                return playerList;
            }

            @Override
            protected void onPostExecute(List<Player> players)
            {
                super.onPostExecute(players);
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ScoreBoardActivity.this,players);
                recyclerView.setAdapter(recyclerAdapter);

            }
        }

        GetPlayers getPlayers = new GetPlayers();
        getPlayers.execute();
    }
}
