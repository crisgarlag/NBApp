package com.example.tfcproyect.model.APInterfaces;

import com.example.tfcproyect.controller.adapterRecycler.GameAdapter;
import com.example.tfcproyect.model.entitys.Game;

import java.util.List;

public interface ApiRequestsTeam {


    String API_SPORTDATA_TEAM_URL = "https://api.sportsdata.io/v3/nba/scores/json/teams?key=86ec5c1581744920a90b566123618d07";
    String API_SPORTDATA_STADIUM_URL = "https://api.sportsdata.io/v3/nba/scores/json/Stadiums?key=86ec5c1581744920a90b566123618d07";

    void searchTeams();
    void searchTeams(List<Game> gameList, GameAdapter gameAdapter);
    void searchTeamByAbbreviationTeam(String abbreviationTeam);


}
