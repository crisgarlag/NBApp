package com.example.tfcproyect.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tfcproyect.Controller.CalendarAdapter;
import com.example.tfcproyect.Controller.GameAdapter;
import com.example.tfcproyect.R;
import com.example.tfcproyect.model.Game;
import com.example.tfcproyect.model.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private RequestQueue requestQueue;
    private List<Game> gameList;
    private List<Team> teamList;
    private GameAdapter gameAdapter;
    private RecyclerView recyclerView;
    private CalendarAdapter calendarAdapter;
    private RecyclerView calendarRecyclerView;
    private LocalDate localDateNow;
    private TextView monthYearTextView;
    private static final String URL_TEAM_SPORTDATAIO_API = "https://api.sportsdata.io/v3/nba/scores/json/teams?key=86ec5c1581744920a90b566123618d07";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();
        /*calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearTextView = findViewById(R.id.monthYearTextView);
        localDateNow = LocalDate.now();
        setMonthView();*/

        calendarView = findViewById(R.id.calendarView);
        requestQueue = Volley.newRequestQueue(this);
        gameList = new ArrayList<Game>();
        teamList = new ArrayList<Team>();


        gameAdapter = new GameAdapter(gameList);
        recyclerView = findViewById(R.id.gamesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(gameAdapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Log.d("Fecha", String.format("%s-%s-%s", i, i1 + 1, i2));
                searchGames(i, i1 + 1, i2);
            }
        });


    }

    /*private void setMonthView() {
        monthYearTextView.setText(monthYearFromDate(localDateNow));
        ArrayList<String> daysOfMonth = daysInMonthArray(localDateNow);
        calendarAdapter = new CalendarAdapter(daysOfMonth);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7, RecyclerView.VERTICAL, false);
        calendarRecyclerView.setLayoutManager(gridLayoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        calendarAdapter.notifyDataSetChanged();
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {

        ArrayList<String> daysInMonthList = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = localDateNow.withDayOfMonth(1);
        int dayOfweek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            if (i < dayOfweek || i >= daysInMonth + dayOfweek) {
                daysInMonthList.add("");
            } else {
                daysInMonthList.add(String.valueOf(i - dayOfweek + 1));
            }
        }
        return daysInMonthList;
    }

    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }*/

    public void searchGames(int year, int month, int day) {

        String url = String.format("https://api.sportsdata.io/v3/nba/scores/json/GamesByDate/%s-%s-%s?key=86ec5c1581744920a90b566123618d07", year, month, day);

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

                        //gameAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }

                Log.i("tamaño", String.valueOf(gameList.size()));
                if(gameList.size()!=0){
                    searchTeam(gameList);
                } else{
                    makeToast();
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

    public void searchTeam(List<Game> gameList) {

        JsonArrayRequest response = new JsonArrayRequest(Request.Method.GET, URL_TEAM_SPORTDATAIO_API, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    teamList.clear();
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject objectTeam = response.getJSONObject(i);
                        String teamId = objectTeam.getString("TeamID");
                        String urlLogoTeam = objectTeam.getString("WikipediaLogoUrl");
                        Team team = new Team();
                        team.setId(Integer.parseInt(teamId));
                        team.setUrlLogo(urlLogoTeam);
                        teamList.add(team);
                    }
                    for (Game game : gameList) {
                        for (Team team : teamList) {
                            if (String.valueOf(team.getId()).equals(game.getAwayTeamId())) {
                                game.setUrlLogoAwayTeam(team.getUrlLogo());
                            } else if (String.valueOf(team.getId()).equals(game.getHomeTeamId())) {
                                game.setUrlLogoHomeTeam(team.getUrlLogo());
                            }
                        }
                       gameAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(response);

    }

    public void makeToast() {
        String textToast = "No ha habido partidos en esta fecha";
        Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        View view = getLayoutInflater().inflate(R.layout.item_toast, (ViewGroup) findViewById(R.id.toastLinearLayout));
        TextView textView = view.findViewById(R.id.textToast);
        textView.setText(textToast);
        toast.setView(view);
        toast.show();
    }

    /*public void previousMonthAction(View view) {

        localDateNow = localDateNow.minusMonths(1);
        setMonthView();

    }

    public void nextMonthAction(View view) {
        localDateNow = localDateNow.plusMonths(1);
        setMonthView();
    }*/
}