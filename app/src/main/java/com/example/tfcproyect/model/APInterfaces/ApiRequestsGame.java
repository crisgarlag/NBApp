package com.example.tfcproyect.model.APInterfaces;

public interface ApiRequestsGame {

    String API_SPORTDATA_GAME_URL = "https://api.sportsdata.io/v3/nba/scores/json/GamesByDate/%s-%s-%s?key=86ec5c1581744920a90b566123618d07";

    void searchGames(int year, int month, int day);
}
