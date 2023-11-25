package com.example.tfcproyect.view.activity;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfcproyect.R;
import com.example.tfcproyect.controller.adapters.PlayersAdapter;
import com.example.tfcproyect.controller.requests.RequestPlayer;
import com.example.tfcproyect.model.entitys.Player;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlayerListActivity extends AppCompatActivity {

    private EditText searchEditText;
    private RecyclerView playersRecyclerView;
    private FloatingActionButton floatingactionbutton;
    private RequestPlayer requestPlayer;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_player_list);
        initializeVariables();
        boolean hasAbbreviationTeam = this.getIntent().hasExtra("abbreviationTeam") ;
        if (hasAbbreviationTeam) {
            String abbreviation = this.getIntent().getExtras().getString("abbreviationTeam");
            floatingactionbutton.setVisibility(View.GONE);
            requestPlayer.searchPlayersByTeam(abbreviation);
        } else {
            requestPlayer.searchPlayersByTeam("");
        }
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String nameQuery = editable.toString().trim();
                requestPlayer.searchPlayersByName(nameQuery);//searchPlayers(nameQuery);
            }
        });

        floatingactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEditText.setVisibility(View.VISIBLE);
            }
        });

        requestPlayer.getPlayersAdapter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayersAdapter.PlayerViewHolder playerViewHolder = new PlayersAdapter.PlayerViewHolder(view);
                String name = playerViewHolder.getPlayerNameTextView().getText().toString();
                String urlPhoto = playerViewHolder.getUrlPhotoTextView().getText().toString();
                ImageView urlPhotoImageView = playerViewHolder.getUrlPhotoImageView();
                requestPlayer.searchIdPlayerToStats(context, name, urlPhoto,urlPhotoImageView);
            }
        });
    }

    public void initializeVariables(){
        searchEditText = findViewById(R.id.searchEditText);
        searchEditText.setVisibility(View.GONE);
        playersRecyclerView = findViewById(R.id.playersRecyclerView);
        floatingactionbutton = findViewById(R.id.floatingactionbutton);
        requestPlayer = new RequestPlayer(this);
        playersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        playersRecyclerView.setAdapter(requestPlayer.getPlayersAdapter());
        context = this;
    }

}