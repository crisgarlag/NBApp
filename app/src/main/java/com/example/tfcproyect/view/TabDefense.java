package com.example.tfcproyect.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

public class TabDefense extends Fragment {

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

    public TabDefense(String id, String playerName, String urlPhoto) {
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
        searchStatsDefense(id);
        adapter = new StatsAdapter(stats);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_defense, container, false);
        playerNameTextView = view.findViewById(R.id.playerNameTextViewDefense);
        playerNameTextView.setText(playerName);
        recyclerView = view.findViewById(R.id.statsRecyclerViewDefense);
        imagePlayer = view.findViewById(R.id.urlPhotoimageViewDefense);
        Picasso.get().load(urlPhoto).into(imagePlayer);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void searchStatsDefense(String query){
        String url = API_BALLDONTLIE_URL + query;

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
                                stats.add("Rebotes defensivos: " + dataObject.getString("dreb"));
                                stats.add("Robos: " + dataObject.getString("stl"));
                                stats.add("Tapones: " + dataObject.getString("blk"));
                                stats.add("Faltas cometidas: " + dataObject.getString("pf"));

                            }else{
                                //  makeToast();
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
 }

