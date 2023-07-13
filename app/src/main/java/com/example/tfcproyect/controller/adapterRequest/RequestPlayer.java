package com.example.tfcproyect.controller.adapterRequest;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tfcproyect.controller.adapterRecycler.PlayersAdapter;
import com.example.tfcproyect.model.APInterfaces.ApiRequestPlayer;
import com.example.tfcproyect.model.entitys.Player;
import com.example.tfcproyect.model.entitys.Team;
import com.example.tfcproyect.view.toast.PersonalizedToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestPlayer implements ApiRequestPlayer {

    private List<Player> playersList;
    private PlayersAdapter playersAdapter;
    private RequestQueue requestQueue;
    private PersonalizedToast personalizedToast;
    private String idPlayer;

    public RequestPlayer(Context context) {
        playersList = new ArrayList<>();
        playersAdapter = new PlayersAdapter(playersList);
        requestQueue = Volley.newRequestQueue(context);
        personalizedToast = new PersonalizedToast(context);
    }

    @Override
    public void searchPlayersbyName(String playerName) {

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, API_SPORTDATA_PLAYERS_URL, null,
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

    @Override
    public void searchPlayersbyTeam(String abbreviationTeamName) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, API_SPORTDATA_PLAYERS_URL, null,
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
                            playersAdapter.setPlayersList(playersList.stream().filter(e -> e.getTeam().getAbbreviation().contains(abbreviationTeamName)).collect(Collectors.toList()));
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

    @Override
    public void searchPlayerToChangeAtivity(String playerName) {

        String url = String.format(API_BALLDONTLIE_URL, playerName);
        String toastMessage = "El jugador no ha jugado esta temporada";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject resp = new JSONObject(response.toString());
                            JSONArray jsonArrayData = resp.getJSONArray("data");
                            if(jsonArrayData.length()==1){
                                for (int i = 0; i < jsonArrayData.length(); i++) {

                                    //Por cada elemento del array crea un nuevo objeto
                                    JSONObject dataObject = jsonArrayData.getJSONObject(i);

                                    // Accede a los valores de los campos en el objeto JSON
                                    idPlayer = dataObject.getString("id");

                                    //startStatsActivity(id, playerName, urlPhoto, urlPhotoImageView);
                                }
                            }else{
                                personalizedToast.makeToast(toastMessage);
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

    public PlayersAdapter getPlayersAdapter() {
        return playersAdapter;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

}
