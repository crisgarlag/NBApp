package com.example.tfcproyect.controller.requests;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tfcproyect.controller.adapters.GameAdapter;
import com.example.tfcproyect.controller.adapters.TeamAdapter;
import com.example.tfcproyect.controller.adapters.TeamInfoAdapter;
import com.example.tfcproyect.model.APInterfaces.ApiRequestsTeam;
import com.example.tfcproyect.model.entitys.Game;
import com.example.tfcproyect.model.entitys.Stadium;
import com.example.tfcproyect.model.entitys.Team;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestTeam implements ApiRequestsTeam {

    private List<Team> teamList;
    private TeamAdapter teamAdapter;
    private TeamInfoAdapter teamInfoAdapter;
    private RequestQueue requestQueue;
    String API_SPORTDATA_STADIUM_URL = "https://api.sportsdata.io/v3/nba/scores/json/Stadiums?key=86ec5c1581744920a90b566123618d07";



    public RequestTeam(Context context){
        teamList = new ArrayList<>();
        teamAdapter = new TeamAdapter(teamList);
        teamInfoAdapter = new TeamInfoAdapter(teamList);
        requestQueue = Volley.newRequestQueue(context);


    }

    @Override
    public void searchTeams() {
        JsonArrayRequest response =  new JsonArrayRequest(Request.Method.GET, API_SPORTDATA_TEAM_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    teamList.clear();
                    for(int i= 0; i<response.length();i++){

                        JSONObject objectTeam =  response.getJSONObject(i);
                        String cityTeam = objectTeam.getString("City");
                        String nameTeam = objectTeam.getString("Name");
                        String urlLogoTeam = objectTeam.getString("WikipediaLogoUrl");
                        String abbreviaton = objectTeam.getString("Key");
                        Team team = new Team();
                        String fullName = cityTeam + " " + nameTeam;
                        team.setFullName(fullName);
                        team.setUrlLogo(urlLogoTeam);
                        team.setAbbreviation(abbreviaton);
                        teamList.add(team);

                    }

                    teamAdapter.notifyDataSetChanged();

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        requestQueue.add(response);

    }

    @Override
    public void searchTeams(List<Game> gameList, GameAdapter gameAdapter) {
        JsonArrayRequest response = new JsonArrayRequest(Request.Method.GET, API_SPORTDATA_TEAM_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    teamList.clear();
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject objectTeam = response.getJSONObject(i);
                        String teamId = objectTeam.getString("TeamID");
                        String urlLogoTeam = objectTeam.getString("WikipediaLogoUrl");
                        Team team = new Team();
                        team.setId(Integer.parseInt(teamId));
                        team.setUrlLogo(urlLogoTeam);
                        teamList.add(team);
                    }
                    for (Game game : gameList) {
                        for (Team team : teamList) {
                            if (String.valueOf(team.getId()).equals(game.getAwayTeamId())) {
                                game.setUrlLogoAwayTeam(team.getUrlLogo());
                            } else if (String.valueOf(team.getId()).equals(game.getHomeTeamId())) {
                                game.setUrlLogoHomeTeam(team.getUrlLogo());
                            }
                        }
                        gameAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(response);

    }

    @Override
    public void searchTeamByAbbreviationTeam(String abbreviationTeam) {
        JsonArrayRequest response =  new JsonArrayRequest(Request.Method.GET, API_SPORTDATA_TEAM_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i= 0; i<response.length();i++){

                        JSONObject objectTeam =  response.getJSONObject(i);
                        String abbreviaton = objectTeam.getString("Key");
                        if(abbreviaton.equals(abbreviationTeam)){
                            String cityTeam = objectTeam.getString("City");
                            String nameTeam = objectTeam.getString("Name");
                            String fullName = cityTeam + " " + nameTeam;
                            String conference = objectTeam.getString("Conference");
                            String division = objectTeam.getString("Division");
                            String headCoach = objectTeam.getString("HeadCoach");
                            Team team = new Team(0, abbreviaton, cityTeam,conference, division, nameTeam, null, fullName, headCoach);
                            int stadiumId = objectTeam.getInt("StadiumID");
                            searchStadium(stadiumId, team);
                            break;
                        }
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        requestQueue.add(response);

    }

    public void searchStadium(int id, Team team){

        JsonArrayRequest response = new JsonArrayRequest(Request.Method.GET, API_SPORTDATA_STADIUM_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i=0;i<response.length();i++){
                        JSONObject objectStadium = response.getJSONObject(i);
                        int idStadium = objectStadium.getInt("StadiumID");
                        if(idStadium == id){
                            String nameStadium = objectStadium.getString("Name");
                            String addressStadium = objectStadium.getString("Address");
                            String cityStadium = objectStadium.getString("City");
                            String countryStadium = objectStadium.getString("Country");
                            double geoLat = objectStadium.getDouble("GeoLat");
                            double geoLong = objectStadium.getDouble("GeoLong");
                            Stadium stadium = new Stadium(idStadium, nameStadium, addressStadium, cityStadium, countryStadium, new LatLng(geoLat,geoLong));
                            team.setStadium(stadium);
                            teamList.add(team);
                            break;
                        }
                    }
                    teamInfoAdapter.notifyDataSetChanged();
                }catch(JSONException ex){
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(response);

    }

    public TeamAdapter getTeamAdapter() {
        return teamAdapter;
    }
    public TeamInfoAdapter getTeamInfoAdapter() {
        return teamInfoAdapter;
    }

}
