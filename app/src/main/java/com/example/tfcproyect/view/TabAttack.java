package com.example.tfcproyect.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tfcproyect.Controller.StatsAdapter;
import com.example.tfcproyect.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TabAttack extends Fragment {

    private List<String> stats;
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private StatsAdapter adapter;
    private ImageView imagePlayer;
    private TextView playerNameTextView;
    private String id;
    private String playerName;
    private String urlPhoto;

    private static String API_BALLDONTLIE_URL = "https://www.balldontlie.io/api/v1/season_averages?season=2022&player_ids[]=";

    public TabAttack(String id, String playerName, String urlPhoto) {
        super();
        this.id = id;
        this.playerName = playerName;
        this.urlPhoto = urlPhoto;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stats = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());
        searchStasAtack(id);
        adapter = new StatsAdapter(stats);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_attack, container, false);
        playerNameTextView = view.findViewById(R.id.playerNameTextViewAtack);
        playerNameTextView.setText(playerName);
        recyclerView = view.findViewById(R.id.statsRecyclerViewAttack);
        imagePlayer = view.findViewById(R.id.urlPhotoimageViewAtack);
        Picasso.get().load(urlPhoto).into(imagePlayer);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void searchStasAtack(String query){
        String url = API_BALLDONTLIE_URL + query;
        DecimalFormat df = new DecimalFormat("0.00");


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            stats.clear();
                            JSONObject resp = new JSONObject(response.toString());
                            JSONArray jsonArray = resp.getJSONArray("data");
                            if(jsonArray.length()==1){
                                JSONObject dataObject = jsonArray.getJSONObject(0);
                                stats.add("Puntos totales: " + dataObject.getString("pts"));
                                stats.add("Tiros encestados: "+ dataObject.getString("fgm"));
                                stats.add("Tiros intentados: " + dataObject.getString("fga"));
                                stats.add("Porcentaje de tiros: " + df.format(Double.parseDouble(dataObject.getString("fg_pct"))*100)+ " %");
                                stats.add("Triples encestados: " + dataObject.getString("fg3m"));
                                stats.add("Triples intentados: " + dataObject.getString("fg3a"));
                                stats.add("Porcentaje de triples: " + df.format(Double.parseDouble(dataObject.getString("fg3_pct"))*100)+ " %");
                                stats.add("Tiros libres encestados: " + dataObject.getString("ftm"));
                                stats.add("Tiros libres intentados: " + dataObject.getString("fta"));
                                stats.add("Porcentaje de tiros libres: " + df.format(Double.parseDouble(dataObject.getString("ft_pct"))*100)+ " %");
                                stats.add("Rebotes ofensivos: " + dataObject.getString("oreb"));
                                stats.add("Asistencias: " + dataObject.getString("ast"));



                            }else{
                              makeToast();
                            }
                            adapter.notifyDataSetChanged();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);


    }
    public void makeToast(){
        Toast.makeText(getContext(), "El jugador no ha jugado esta temporada", Toast.LENGTH_SHORT).show();
    }

}
