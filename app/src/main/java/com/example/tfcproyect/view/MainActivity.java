package com.example.tfcproyect.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tfcproyect.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonPlayers, buttonTeams, buttonGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlayers = findViewById(R.id.buttonPlayers);
        buttonTeams = findViewById(R.id.buttonTeams);
        buttonGames = findViewById(R.id.buttonGames);

        buttonPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPlayerListActivity(view);
            }
        });

        buttonTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTeamActivity(view);
            }
        });

        buttonGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGameActivity(view);
            }
        });
    }

    public void startPlayerListActivity(View view){
        Intent intent = new Intent(this, PlayerListActivity.class);
        startActivity(intent);
    }
    public void startTeamActivity(View view){
        Intent intent = new Intent(this, TeamActivity.class);
        startActivity(intent);
    }
    public void startGameActivity(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}