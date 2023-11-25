package com.example.tfcproyect.model.APInterfaces;

import android.content.Context;
import android.widget.ImageView;

import com.example.tfcproyect.view.activity.PlayerListActivity;

public interface ApiRequestPlayer {
    String API_BALLDONTLIE_URL = "https://www.balldontlie.io/api/v1/players?per_page=100&search=%s";
    String API_SPORTDATA_PLAYERS_URL = "https://api.sportsdata.io/v3/nba/scores/json/Players?key=86ec5c1581744920a90b566123618d07";

    void searchPlayersByName(String playerName);
    void searchPlayersByTeam(String abbreviationTeamName);
    void searchIdPlayerToStats(Context context, String playerName, String urlPhoto, ImageView urlPhotoImageView);
}
