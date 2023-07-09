package com.example.tfcproyect.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tfcproyect.Controller.TeamAdapter;
import com.example.tfcproyect.R;
import com.example.tfcproyect.model.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity {

    List<Team> teamList;
    TeamAdapter teamAdapter;
    RecyclerView recyclerView;

    RequestQueue requestQueue;

    private static final String URL_TEAM_SPORTDATAIO_API = "https://api.sportsdata.io/v3/nba/scores/json/teams?key=86ec5c1581744920a90b566123618d07";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.teamsRecyclerView);
        teamList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        teamAdapter = new TeamAdapter(teamList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(teamAdapter);
        searchTeam();

        teamAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeamAdapter.TeamViewHolder teamViewHolder = new TeamAdapter.TeamViewHolder(view);
                String abbreviationTeam = teamViewHolder.getAbbreviationTeam().getText().toString();
                startPlayerActivity(view, abbreviationTeam);

            }
        });

    }

    public void searchTeam(){

        JsonArrayRequest response =  new JsonArrayRequest(Request.Method.GET, URL_TEAM_SPORTDATAIO_API, null, new Response.Listener<JSONArray>() {
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

    public void startPlayerActivity(View view, String abbreviationTeam){
        Intent intent = new Intent(this, PlayerListActivity.class);
        intent.putExtra("abbreviationTeam", abbreviationTeam);
        startActivity(intent);
    }


}