package com.example.tfcproyect.view.activity;


import android.app.ActivityOptions;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tfcproyect.R;
import com.example.tfcproyect.controller.adapterRecycler.PlayersAdapter;
import com.example.tfcproyect.controller.adapterRequest.RequestPlayer;
import com.example.tfcproyect.model.entitys.Player;
import com.example.tfcproyect.model.entitys.Team;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerListActivity extends AppCompatActivity {

    private EditText searchEditText;
    private RecyclerView playersRecyclerView;
    private PlayersAdapter playersAdapter;
    private List<Player> playersList;
    private String id;
    private FloatingActionButton floatingactionbutton;
    private RequestPlayer requestPlayer;
    private RequestQueue requestQueue;


    private static final String API_BALLDONTLIE_URL = "https://www.balldontlie.io/api/v1/players?per_page=100&search=";
    private static final String API_SPORTDATA_URL = "https://api.sportsdata.io/v3/nba/scores/json/Players?key=86ec5c1581744920a90b566123618d07";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_player_list);
        searchEditText = findViewById(R.id.searchEditText);
        searchEditText.setVisibility(View.GONE);
        playersRecyclerView = findViewById(R.id.playersRecyclerView);
        floatingactionbutton = findViewById(R.id.floatingactionbutton);
        //requestPlayer = new RequestPlayer(this);
        playersList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        playersAdapter = new PlayersAdapter(playersList);
        playersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        playersRecyclerView.setAdapter(playersAdapter);


        if (this.getIntent().hasExtra("abbreviationTeam")) {
            String abbreviation = this.getIntent().getExtras().getString("abbreviationTeam");
            floatingactionbutton.setVisibility(View.GONE);
            //requestPlayer.searchPlayersbyTeam(abbreviation);
            searchPlayersByTeam(abbreviation);
        } else {
            //requestPlayer.searchPlayersbyName("");
            searchPlayersByTeam("");
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
                //requestPlayer.searchPlayersbyName(nameQuery);
                searchPlayers(nameQuery);
            }
        });

        floatingactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEditText.setVisibility(View.VISIBLE);
            }
        });


        playersAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayersAdapter.PlayerViewHolder playerViewHolder = new PlayersAdapter.PlayerViewHolder(view);
                String name = playerViewHolder.getPlayerNameTextView().getText().toString();
                String urlPhoto = playerViewHolder.getUrlPhotoTextView().getText().toString();
                ImageView urlPhotoImageView = playerViewHolder.getUrlPhotoImageView();
                //requestPlayer.searchPlayerToChangeAtivity(name);
                searchPlayerStats(name, urlPhoto, urlPhotoImageView);
                //startStatsActivity(requestPlayer.getIdPlayer(), name, urlPhoto, urlPhotoImageView);

            }
        });
    }

    private void searchPlayerStats(String playerName, String urlPhoto, ImageView urlPhotoImageView) {
        String url = API_BALLDONTLIE_URL + playerName;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //playersList.clear();
                            JSONObject resp = new JSONObject(response.toString());
                            JSONArray jsonArrayData = resp.getJSONArray("data");
                            if (jsonArrayData.length() > 0) {
                                for (int i = 0; i < jsonArrayData.length(); i++) {

                                    //Por cada elemento del array crea un nuevo objeto
                                    JSONObject dataObject = jsonArrayData.getJSONObject(i);

                                    // Accede a los valores de los campos en el objeto JSON
                                    id = dataObject.getString("id");
                                    String firstName = dataObject.getString("first_name");
                                    String lastName = dataObject.getString("last_name");
                                    // Accede al objeto "team" dentro del objeto JSON
                                    JSONObject teamObject = dataObject.getJSONObject("team");
                                    String teamFullName = teamObject.getString("full_name");

                                    Team team = new Team();
                                    team.setFullName(teamFullName);
                                    Player player = new Player();
                                    //player.setId(id);
                                    player.setFirstName(firstName);
                                    player.setLastName(lastName);
                                    player.setTeam(team);
                                    playersList.add(player);
                                    startStatsActivity(id, playerName, urlPhoto, urlPhotoImageView);
                                }
                                //playersAdapter.notifyDataSetChanged();
                            } else {
                                makeToast();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        requestQueue.add(request);

    }

    private void searchPlayers(String playerName) {

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, API_SPORTDATA_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            playersList.clear();
                            for (int i = 0; i < response.length(); i++) {
                                //Por cada elemento del array crea un nuevo objeto
                                JSONObject dataObject = response.getJSONObject(i);

                                // Accede a los valores de los campos en el objeto JSON
                                String firstName = dataObject.getString("FirstName");
                                String lastName = dataObject.getString("LastName");
                                String teamFullName = dataObject.getString("Team");
                                String urlPhoto = dataObject.getString("PhotoUrl");
                                Team team = new Team();
                                team.setFullName(teamFullName);
                                Player player = new Player();
                                player.setFirstName(firstName);
                                player.setLastName(lastName);
                                player.setTeam(team);
                                player.setUrlPhoto(urlPhoto);
                                playersList.add(player);

                            }
                            playersAdapter.setPlayersList(playersList.stream().filter(e -> e.getFullName().toLowerCase().contains(playerName.toLowerCase())).collect(Collectors.toList()));
                            playersAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        requestQueue.add(request);
    }


    private void searchPlayersByTeam(String abbreviationTeam) {

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, API_SPORTDATA_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            playersList.clear();
                            for (int i = 0; i < response.length(); i++) {
                                //Por cada elemento del array crea un nuevo objeto
                                JSONObject dataObject = response.getJSONObject(i);

                                // Accede a los valores de los campos en el objeto JSON
                                String firstName = dataObject.getString("FirstName");
                                String lastName = dataObject.getString("LastName");
                                String teamFullName = dataObject.getString("Team");
                                String urlPhoto = dataObject.getString("PhotoUrl");
                                String abbreviation = dataObject.getString("Team");
                                Team team = new Team();
                                team.setFullName(teamFullName);
                                team.setAbbreviation(abbreviation);
                                Player player = new Player();
                                player.setFirstName(firstName);
                                player.setLastName(lastName);
                                player.setTeam(team);
                                player.setUrlPhoto(urlPhoto);
                                playersList.add(player);

                            }
                            playersAdapter.setPlayersList(playersList.stream().filter(e -> e.getTeam().getAbbreviation().contains(abbreviationTeam)).collect(Collectors.toList()));
                            playersAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        requestQueue.add(request);
    }

    public void makeToast() {
        String textToast = "El jugador no ha jugado esta temporada";
        Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        View view = getLayoutInflater().inflate(R.layout.item_toast, (ViewGroup) findViewById(R.id.toastLinearLayout));
        TextView textView = view.findViewById(R.id.textToast);
        textView.setText(textToast);
        toast.setView(view);
        toast.show();
    }


    public void startStatsActivity(String idPlayer, String playerName, String urlPhoto, ImageView urlPhotoImageView) {
        Intent intent = new Intent(this, StatsActivity.class);
        intent.putExtra("id", idPlayer);
        intent.putExtra("playerName", playerName);
        intent.putExtra("urlPhoto", urlPhoto);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, urlPhotoImageView, ViewCompat.getTransitionName(urlPhotoImageView));
        startActivity(intent, options.toBundle());
    }
}