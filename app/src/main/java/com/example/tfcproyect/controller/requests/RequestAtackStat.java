package com.example.tfcproyect.controller.requests;

import android.app.Person;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tfcproyect.controller.adapters.StatsAdapter;
import com.example.tfcproyect.model.APInterfaces.ApiRequestsStat;
import com.example.tfcproyect.view.toast.PersonalizedToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RequestAtackStat implements ApiRequestsStat {

    private List<String> statList;
    private StatsAdapter statAdapter;
    private RequestQueue requestQueue;
    private Context context;

    public RequestAtackStat(Context context) {
        statList = new ArrayList<>();
        statAdapter = new StatsAdapter(statList);
        requestQueue = Volley.newRequestQueue(context);
        this.context = context;
    }

    @Override
    public void searchStats(String id) {

        String url = String.format(API_BALLDONTLIE_URL, id);
        DecimalFormat df = new DecimalFormat("0.00");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            statList.clear();
                            JSONObject resp = new JSONObject(response.toString());
                            JSONArray jsonArray = resp.getJSONArray("data");
                            if(jsonArray.length()>0){
                                JSONObject dataObject = jsonArray.getJSONObject(0);
                                statList.add("Puntos totales: " + dataObject.getString("pts"));
                                statList.add("Tiros encestados: " + dataObject.getString("fgm"));
                                statList.add("Tiros intentados: " + dataObject.getString("fga"));
                                statList.add("Porcentaje de tiros: " + df.format(Double.parseDouble(dataObject.getString("fg_pct")) * 100) + " %");
                                statList.add("Triples encestados: " + dataObject.getString("fg3m"));
                                statList.add("Triples intentados: " + dataObject.getString("fg3a"));
                                statList.add("Porcentaje de triples: " + df.format(Double.parseDouble(dataObject.getString("fg3_pct")) * 100) + " %");
                                statList.add("Tiros libres encestados: " + dataObject.getString("ftm"));
                                statList.add("Tiros libres intentados: " + dataObject.getString("fta"));
                                statList.add("Porcentaje de tiros libres: " + df.format(Double.parseDouble(dataObject.getString("ft_pct")) * 100) + " %");
                                statList.add("Rebotes ofensivos: " + dataObject.getString("oreb"));
                                statList.add("Asistencias: " + dataObject.getString("ast"));

                                statAdapter.notifyDataSetChanged();
                            } else {
                                PersonalizedToast personalizedToast = new PersonalizedToast(context);
                                personalizedToast.makeToast("No existen estadísticas para este jugador");

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

        requestQueue.add(request);

    }

    public StatsAdapter getStatAdapter() {
        return statAdapter;
    }
}

