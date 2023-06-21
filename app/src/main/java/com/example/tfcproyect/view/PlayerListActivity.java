package com.example.tfcproyect.view;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tfcproyect.Controller.PlayersAdapter;
import com.example.tfcproyect.R;
import com.example.tfcproyect.model.Player;
import com.example.tfcproyect.model.Team;


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

    private RequestQueue requestQueue;


    private static final String API_BALLDONTLIE_URL = "https://www.balldontlie.io/api/v1/players?per_page=100&search=";
    private static final String API_SPORTDATA_URL = "https://api.sportsdata.io/v3/nba/scores/json/Players?key=86ec5c1581744920a90b566123618d07";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        searchEditText = findViewById(R.id.searchEditText);
        searchEditText.setVisibility(View.VISIBLE);
        playersRecyclerView = findViewById(R.id.playersRecyclerView);
        playersList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        playersAdapter = new PlayersAdapter(playersList);
        playersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        playersRecyclerView.setAdapter(playersAdapter);

        if(this.getIntent().hasExtra("abbreviationTeam")){
            String abbreviation = this.getIntent().getExtras().getString("abbreviationTeam");
            searchEditText.setVisibility(View.GONE);
            searchPlayersByTeam(abbreviation);
        }else{
            searchPlayers("");
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
                String query = editable.toString().trim();
                searchPlayers(query);

            }
        });







        playersAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayersAdapter.PlayerViewHolder playerViewHolder = new PlayersAdapter.PlayerViewHolder(view);
                String name = playerViewHolder.getPlayerNameTextView().getText().toString();
                searchPlayerStats(view, name);

            }
        });
    }


    private void searchPlayerStats(View view, String playerName) {
        String url = API_BALLDONTLIE_URL + playerName;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //playersList.clear();
                            JSONObject resp = new JSONObject(response.toString());
                            JSONArray jsonArrayData = resp.getJSONArray("data");

                            for (int i = 0; i < jsonArrayData.length(); i++) {

                                //Por cada elemento del array crea un nuevo objeto
                                JSONObject dataObject = jsonArrayData.getJSONObject(i);

                                // Accede a los valores de los campos en el objeto JSON
                                id = dataObject.getString("id");
                                /*String firstName = dataObject.getString("first_name");
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
                                playersList.add(player);*/
                                startStatsActivity(view, id);
                            }
                            //playersAdapter.notifyDataSetChanged();
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



    public void startStatsActivity(View view, String id){
        Intent intent = new Intent(this, StatsActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }


}