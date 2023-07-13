package com.example.tfcproyect.controller.adapterRequest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tfcproyect.controller.adapterRecycler.GameAdapter;
import com.example.tfcproyect.model.APInterfaces.ApiRequestsGame;
import com.example.tfcproyect.model.entitys.Game;
import com.example.tfcproyect.view.toast.PersonalizedToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestGame implements ApiRequestsGame {

    private List<Game> gameList;
    private GameAdapter gameAdapter;
    private RequestQueue requestQueue;
    private RequestTeam requestTeam;
    private PersonalizedToast personalizedToast;

    public RequestGame(Context context) {
        gameList = new ArrayList<Game>();
        gameAdapter = new GameAdapter(gameList);
        requestQueue = Volley.newRequestQueue(context);
        requestTeam = new RequestTeam(context);
        personalizedToast = new PersonalizedToast(context);
    }

    @Override
    public void searchGames(int year, int month, int day) {

        String url = String.format(API_SPORTDATA_GAME_URL, year, month, day);
        String toastMessage = "No ha habido partidos en esta fecha";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                gameList.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String status = jsonObject.getString("Status");
                        if (!status.equals("Canceled") && !status.equals("NotNecessary")) {
                            String awayTeam = jsonObject.getString("AwayTeam");
                            String homeTeam = jsonObject.getString("HomeTeam");
                            String awayTeamId = jsonObject.getString("AwayTeamID");
                            String homeTeamId = jsonObject.getString("HomeTeamID");
                            String awayTeamScore = jsonObject.getString("AwayTeamScore");
                            String homeTeamScore = jsonObject.getString("HomeTeamScore");
                            Game game = new Game(awayTeam, homeTeam, awayTeamId, homeTeamId, awayTeamScore, homeTeamScore);
                            gameList.add(game);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (gameList.size() != 0) {
                    requestTeam.searchTeams(gameList, gameAdapter);
                } else {
                    personalizedToast.makeToast(toastMessage);
                    gameAdapter.notifyDataSetChanged();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);
    }

    public GameAdapter getGameAdapter() {
        return gameAdapter;
    }
}
