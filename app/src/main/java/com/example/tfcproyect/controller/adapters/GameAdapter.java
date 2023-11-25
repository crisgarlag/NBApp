package com.example.tfcproyect.controller.adapters;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.example.tfcproyect.R;
import com.example.tfcproyect.controller.useCases.SVGParse;
import com.example.tfcproyect.model.entitys.Game;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<Game> gameList;

    public GameAdapter(List<Game> gameList) {
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public GameAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
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

    public static class GameViewHolder extends RecyclerView.ViewHolder {

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

        public void bind(Game game) {
            //El escudo de clevelan es el unico cuya url obtenida de la API no genera una imagen en formato .svg, por lo que se usa esta url externa
            String urlLogoCleveland = "https://upload.wikimedia.org/wikipedia/commons/4/4b/Cleveland_Cavaliers_logo.svg";
            String urlLogoHomeTeam = game.getUrlLogoHomeTeam();
            String urlLogoAwayTeam = game.getUrlLogoAwayTeam();

            textViewPoints1.setText(game.getHomeTeamScore());
            textViewPoints2.setText(game.getAwayTeamScore());

            if (urlLogoHomeTeam.endsWith(".svg")) {
                new AsyncTaskHome().execute(urlLogoHomeTeam);
            } else {
                new AsyncTaskHome().execute(urlLogoCleveland);
            }

            if (urlLogoHomeTeam.endsWith(".svg")) {
                new AsyncTaskAway().execute(urlLogoAwayTeam);
            } else {
                new AsyncTaskHome().execute(urlLogoCleveland);
            }


        }

        public class AsyncTaskHome extends AsyncTask<String, Void, SVG> {
            @Override
            protected SVG doInBackground(String... strings) {

                return SVGParse.parseo(strings[0]);
            }

            @Override
            protected void onPostExecute(SVG svg) {
                super.onPostExecute(svg);
                if (svg != null) {
                    svgImageHomeTeam.setSVG(svg);
                }
            }
        }

        public class AsyncTaskAway extends AsyncTask<String, Void, SVG> {
            @Override
            protected SVG doInBackground(String... strings) {

                return SVGParse.parseo(strings[0]);
            }

            @Override
            protected void onPostExecute(SVG svg) {
                super.onPostExecute(svg);
                if (svg != null)
                    svgImageAwayTeam.setSVG(svg);
            }
        }

    }
}
