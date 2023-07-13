package com.example.tfcproyect.model.APInterfaces;

public interface ApiRequestsStat {

    String API_BALLDONTLIE_URL = "https://www.balldontlie.io/api/v1/season_averages?season=2022&player_ids[]=%s";

    void searchStats(String id);
}
