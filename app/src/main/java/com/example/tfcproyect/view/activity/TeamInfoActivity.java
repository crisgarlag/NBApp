package com.example.tfcproyect.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tfcproyect.R;
import com.example.tfcproyect.controller.adapterRecycler.TeamInfoAdapter;
import com.example.tfcproyect.controller.adapterRequest.RequestTeam;
import com.google.android.gms.maps.model.LatLng;

public class TeamInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestTeam requestTeam;
    private TextView teamNameTextView;
    private Button buttonGoMap;
    private String teamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_team_info);
       initializeVariables();
       teamName = getIntent().getStringExtra("teamName");
       teamNameTextView.setText(teamName);
       requestTeam.searchTeamByAbbreviationTeam(getIntent().getStringExtra("abbreviationTeam"));
       buttonGoMap.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               View itemTeamView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_team_info,null);
               TeamInfoAdapter.TeamInfoViewHolder teamInfoViewHolder = new TeamInfoAdapter.TeamInfoViewHolder(itemTeamView);
               teamInfoViewHolder.bind(TeamInfoAdapter.getTeamList().get(0));
               String lat = teamInfoViewHolder.getLat().getText().toString();
               String lon = teamInfoViewHolder.getLon().getText().toString();
               String stadiumName = teamInfoViewHolder.getStadiumTextView().getText().toString();
               LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
               startActivityMap(view, latLng, stadiumName);
           }
       });
    }

    public void initializeVariables() {
        recyclerView = findViewById(R.id.infoTeamsRecyclerView);
        requestTeam = new RequestTeam(this);
        LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(requestTeam.getTeamInfoAdapter());
        teamNameTextView = findViewById(R.id.teamNameInfo);
        buttonGoMap = findViewById(R.id.buttonGoMap);
    }

    public void startActivityMap(View view, LatLng latLng, String stadiumName){
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("latitude",latLng.latitude);
        intent.putExtra("longitude",latLng.longitude);
        intent.putExtra("teamName", teamName);
        intent.putExtra("stadiumName", stadiumName);
        startActivity(intent);
    }

}