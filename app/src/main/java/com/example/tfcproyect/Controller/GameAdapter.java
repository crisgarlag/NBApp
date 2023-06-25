package com.example.tfcproyect.Controller;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;
import com.example.tfcproyect.R;
import com.example.tfcproyect.model.Game;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<Game> gameList;

    public GameAdapter(List<Game> gameList) {
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public GameAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent,false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.GameViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.bind(game);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewPoints1, textViewPoints2, textViewBar;
        private SVGImageView svgImageHomeTeam, svgImageAwayTeam;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPoints1 = itemView.findViewById(R.id.textViewPoints1);
            textViewPoints2 = itemView.findViewById(R.id.textViewPoints2);
            textViewBar = itemView.findViewById(R.id.textViewBar);
            svgImageHomeTeam = itemView.findViewById(R.id.SVGImageViewHomeTeam);
            svgImageAwayTeam = itemView.findViewById(R.id.SVGImageViewAwayTeam);

        }
        public void bind(Game game){

            String urlLogoHomeTeam = game.getUrlLogohomeTeam();
            String urlLogoAwayTeam = game.getUrlLogoAwayTeam();

            textViewPoints1.setText(game.getHomeTeamScore());
            textViewPoints2.setText(game.getAwayTeamScore());
            if(textViewPoints1.getText().toString().equals("") && textViewPoints2.getText().toString().equals("")){
                textViewBar.setText("");
            }else{
                textViewBar.setText(" - ");
            }

            if(urlLogoHomeTeam == null){
                Log.d("Prueba home", "home es un elemento null");
            }

            if(urlLogoAwayTeam ==null){
                Log.d("Prueba away", "away es un elemento null");
            }

            Thread thread =  new Thread(()->{
                try {
                    URL urlHomeTeam = new URL(urlLogoHomeTeam);
                    HttpURLConnection connectionHomeTeam = (HttpURLConnection) urlHomeTeam.openConnection();
                    InputStream inputStreamHomeTeam = connectionHomeTeam.getInputStream();
                    SVG svgHomeTeam = SVG.getFromInputStream(inputStreamHomeTeam);
                    svgImageHomeTeam.setSVG(svgHomeTeam);
                    URL urlAwayTeam = new URL(urlLogoAwayTeam);
                    HttpURLConnection connectionAwayTeam = (HttpURLConnection) urlAwayTeam.openConnection();
                    InputStream inputStreamAwayHome = connectionAwayTeam.getInputStream();
                    SVG svgAwayTeam = SVG.getFromInputStream(inputStreamAwayHome);
                    svgImageAwayTeam.setSVG(svgAwayTeam);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            thread.start();


        }

    }
}
