package com.example.tfcproyect.controller.adapterRequest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tfcproyect.controller.adapterRecycler.StatsAdapter;
import com.example.tfcproyect.model.APInterfaces.ApiRequestsStat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestDefenseStat implements ApiRequestsStat {

    private List<String> statList;
    private StatsAdapter statAdapter;
    private RequestQueue requestQueue;

    public RequestDefenseStat(Context context) {
        statList = new ArrayList<>();
        statAdapter = new StatsAdapter(statList);
        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void searchStats(String id) {
        String url = String.format(API_BALLDONTLIE_URL, id);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            statList.clear();
                            JSONObject resp = new JSONObject(response.toString());
                            JSONArray jsonArray = resp.getJSONArray("data");
                                JSONObject dataObject = jsonArray.getJSONObject(0);
                                statList.add("Rebotes defensivos: " + dataObject.getString("dreb"));
                                statList.add("Robos: " + dataObject.getString("stl"));
                                statList.add("Tapones: " + dataObject.getString("blk"));
                                statList.add("Faltas cometidas: " + dataObject.getString("pf"));
                            statAdapter.notifyDataSetChanged();
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

    public StatsAdapter getStatAdapter() {
        return statAdapter;
    }
}
