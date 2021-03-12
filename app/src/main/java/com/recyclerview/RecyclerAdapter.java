package com.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whacktheicon.R;
import com.model.Player;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Player> players;

    public RecyclerAdapter(Context context, List<Player> players){
        this.context = context;
        this.players = players;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_players, parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Player player = players.get(position);
        holder.nameTextView.setText(player.getPlayerName());
        holder.scoreTextView.setText(Integer.toString(player.getPlayerScore()));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView;
        public TextView scoreTextView;

        public ViewHolder(View itemView){
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTW);
            scoreTextView = itemView.findViewById(R.id.scoreTW);
        }
    }
}
