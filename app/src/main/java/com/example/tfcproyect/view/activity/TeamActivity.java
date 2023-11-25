package com.example.tfcproyect.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfcproyect.controller.adapters.TeamAdapter;
import com.example.tfcproyect.R;
import com.example.tfcproyect.controller.requests.RequestTeam;

public class TeamActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RequestTeam requestTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        getSupportActionBar().hide();
        initializeVariables();
        requestTeam.searchTeams();
        requestTeam.getTeamAdapter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeamAdapter.TeamViewHolder teamViewHolder = new TeamAdapter.TeamViewHolder(view);
                String abbreviationTeam = teamViewHolder.getAbbreviationTeam().getText().toString();
                String teamName = teamViewHolder.getTeamNameTextView().getText().toString();
                startInfoActivity(view, abbreviationTeam, teamName);

            }
        });
    }

    public void initializeVariables() {
        recyclerView = findViewById(R.id.teamsRecyclerView);
        requestTeam = new RequestTeam(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(requestTeam.getTeamAdapter());
    }

    public void startInfoActivity(View view, String abbreviationTeam, String teamName) {
        Intent intent = new Intent(this, InfoPlayersActivity.class);
        intent.putExtra("abbreviationTeam", abbreviationTeam);
        intent.putExtra("teamName", teamName);
        startActivity(intent);
    }


}