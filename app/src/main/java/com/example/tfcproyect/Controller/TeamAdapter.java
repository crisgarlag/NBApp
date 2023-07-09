package com.example.tfcproyect.Controller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;
import com.example.tfcproyect.R;
import com.example.tfcproyect.SVGParse;
import com.example.tfcproyect.model.Team;
import com.pixplicity.sharp.Sharp;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private List<Team> teamList;
    private View.OnClickListener onClickListener;

    public TeamAdapter(List<Team> teamList) {
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public TeamAdapter.TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        view.setOnClickListener(onClickListener);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.TeamViewHolder holder, int position) {
        Team team = teamList.get(position);
        holder.bind(team);

    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {

        private TextView teamNameTextView;
        private ImageView logoTeamImageView;
        private SVGImageView svgImage;
        private TextView abbreviationTeamTextView;


        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);

            teamNameTextView = itemView.findViewById(R.id.teamNameTextView);
            //logoTeamImageView = itemView.findViewById(R.id.urlPhototeamImageView);
            svgImage = itemView.findViewById(R.id.SVGImageView);
            abbreviationTeamTextView = itemView.findViewById(R.id.teamAbbView);
        }

        public TextView getAbbreviationTeam() {
            return abbreviationTeamTextView;
        }

        public void bind(Team team) {
            //El escudo de clevelan es el unico cuya url obtenida de la API no genera una imagen en formato .svg, por lo que se usa esta url externa
            String urlLogoCleveland = "https://upload.wikimedia.org/wikipedia/commons/4/4b/Cleveland_Cavaliers_logo.svg";

            String urlLogo = team.getUrlLogo();
            teamNameTextView.setText(team.getFullName());
            abbreviationTeamTextView.setText(team.getAbbreviation());
            if (urlLogo.endsWith(".svg")) {
                new AsynTaskTeam().execute(urlLogo);
            } else {
                new AsynTaskTeam().execute(urlLogoCleveland);
            }
        }

        public class AsynTaskTeam extends AsyncTask<String, Void, SVG> {

            @Override
            protected SVG doInBackground(String... strings) {
                return SVGParse.parseo(strings[0]);
            }

            @Override
            protected void onPostExecute(SVG svg) {
                super.onPostExecute(svg);
                if (svg != null)
                    svgImage.setSVG(svg);
            }
        }
    }
}
