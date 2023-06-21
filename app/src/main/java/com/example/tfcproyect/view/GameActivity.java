package com.example.tfcproyect.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tfcproyect.R;
import com.example.tfcproyect.model.Game;
import com.example.tfcproyect.model.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private RequestQueue requestQueue;
    private List<Game> gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        calendarView = findViewById(R.id.calendarView);
        requestQueue = Volley.newRequestQueue(this);
        gameList = new ArrayList<Game>();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                Log.d("Date", String.valueOf(String.format("%s-%s-%s", i, i1,i2)));
            }
        });


    }

    public void searchGames(int year, int month, int day){

        String url = String.format("https://api.sportsdata.io/v3/nba/scores/json/GamesByDate/%s-%s-%s?key=86ec5c1581744920a90b566123618d07", year, month, day);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                gameList.clear();
                for(int i = 0; i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String status = jsonObject.getString("Status");
                        if(!status.equals("Canceled")){
                            String awayTeam = jsonObject.getString("AwayTeam");
                            String homeTeam = jsonObject.getString("HomeTeam");
                            String awayTeamId = jsonObject.getString("AwayTeamID");
                            String homeTeamId = jsonObject.getString("HomeTeamID");
                            String awayTeamScore = jsonObject.getString("AwayTeamScore");
                            String homeTeamScore = jsonObject.getString("HomeTeamScore");
                            Game game = new Game(awayTeam,homeTeam, awayTeamId, homeTeamId, awayTeamScore,homeTeamScore);
                            gameList.add(game);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
            }
}