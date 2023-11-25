package com.example.tfcproyect.controller.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfcproyect.R;
import com.example.tfcproyect.model.entitys.Player;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder> {

    private List<Player> playersList;
    protected View.OnClickListener onClickListener;

    public PlayersAdapter(List<Player> playersList) {

        this.playersList = playersList;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
        view.setOnClickListener(onClickListener);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {

            Player player = playersList.get(position);
            holder.bind(player);
    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }


    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {

        private TextView playerNameTextView;
        private TextView playerTeamTextView;
        private TextView playerIdTextView;
        private ImageView urlPhotoImageView;
        private TextView urlPhotoTextView;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerNameTextView = itemView.findViewById(R.id.playerNameTextView);
            playerTeamTextView = itemView.findViewById(R.id.playerTeamTextView);
            playerIdTextView = itemView.findViewById(R.id.playerId);
            urlPhotoImageView = itemView.findViewById(R.id.urlPhotoimageView);
            urlPhotoTextView = itemView.findViewById(R.id.playerUrlPhoto);

        }

        public void bind(Player player) {
            playerNameTextView.setText(player.getFullName());
            playerTeamTextView.setText(player.getTeam().getFullName());
            playerIdTextView.setText(String.valueOf(player.getId()));
            Picasso.get().load(player.getUrlPhoto()).into(urlPhotoImageView);
            urlPhotoTextView.setText(player.getUrlPhoto());
        }

        public TextView getPlayerNameTextView() {
            return playerNameTextView;
        }

        public TextView getPlayerTeamTextView() {
            return playerTeamTextView;
        }

        public TextView getPlayerIdTextView() {
            return playerIdTextView;
        }

        public TextView getUrlPhotoTextView() {
            return urlPhotoTextView;
        }

        public ImageView getUrlPhotoImageView() {
            return urlPhotoImageView;
        }
    }

}

