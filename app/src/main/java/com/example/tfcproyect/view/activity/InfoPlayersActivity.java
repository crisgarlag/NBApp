package com.example.tfcproyect.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tfcproyect.R;

public class InfoPlayersActivity extends AppCompatActivity {

    private Button buttonInfo, buttonPlayer;
    private String abbreviationTeam, teamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_info);
        getSupportActionBar().hide();
        initializeVariables();

        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startInfoTeamActivity(view, abbreviationTeam);
            }
        });

        buttonPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlayerListActivity(view, abbreviationTeam);
            }
        });

    }

    public void initializeVariables() {
        buttonInfo = findViewById(R.id.buttonInfo);
        buttonPlayer = findViewById(R.id.buttonPlayerInfo);
        abbreviationTeam = getIntent().getStringExtra("abbreviationTeam");
        teamName = getIntent().getStringExtra("teamName");

    }

    public void startInfoTeamActivity(View view, String abbreviationTeam) {
        Intent intent = new Intent(this, TeamInfoActivity.class);
        intent.putExtra("abbreviationTeam", abbreviationTeam);
        intent.putExtra("teamName", teamName);
        startActivity(intent);
    }

    public void startPlayerListActivity(View view, String abbreviationTeam) {
        Intent intent = new Intent(this, PlayerListActivity.class);
        intent.putExtra("abbreviationTeam", abbreviationTeam);
        startActivity(intent);
    }

}