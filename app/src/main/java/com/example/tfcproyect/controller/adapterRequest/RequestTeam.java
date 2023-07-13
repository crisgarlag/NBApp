package com.example.tfcproyect.controller.adapterRequest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tfcproyect.controller.adapterRecycler.GameAdapter;
import com.example.tfcproyect.controller.adapterRecycler.TeamAdapter;
import com.example.tfcproyect.model.APInterfaces.ApiRequestsTeam;
import com.example.tfcproyect.model.entitys.Game;
import com.example.tfcproyect.model.entitys.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestTeam implements ApiRequestsTeam {

    private List<Team> teamList;
    private TeamAdapter teamAdapter;
    private RequestQueue requestQueue;

    public RequestTeam(Context context){
        teamList = new ArrayList<Team>();
        teamAdapter = new TeamAdapter(teamList);
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

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public TeamAdapter getTeamAdapter() {
        return teamAdapter;
    }

    public void setTeamAdapter(TeamAdapter teamAdapter) {
        this.teamAdapter = teamAdapter;
    }
}
