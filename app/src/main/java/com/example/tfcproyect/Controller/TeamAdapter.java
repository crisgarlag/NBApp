package com.example.tfcproyect.Controller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
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

    List<Team> teamList;

    public TeamAdapter(List<Team> teamList) {
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public TeamAdapter.TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
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

    public class TeamViewHolder extends RecyclerView.ViewHolder {

        private TextView teamNameTextView;
        private ImageView logoTeamImageView;
        private SVGImageView svgImage;


        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);

            teamNameTextView = itemView.findViewById(R.id.teamNameTextView);
            logoTeamImageView = itemView.findViewById(R.id.urlPhototeamImageView);
            svgImage = itemView.findViewById(R.id.recentlyViewed_imgView);

        }

        public void bind(Team team) {

            String urlLogo = team.getUrlLogo();
            teamNameTextView.setText(team.getFullName());
            //"https://en.wikipedia.org/wiki/Cleveland_Cavaliers#/media/File:Cleveland_Cavaliers_logo.svg";
            // Inicia una nueva Thread para cargar la imagen SVG desde la URL
            Thread thread = new Thread(() -> {
                try {
                    // Crea una conexión HTTP para obtener el InputStream de la URL
                    URL url = new URL(urlLogo);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    // Parsea el SVG desde el InputStream
                    SVG svg = SVG.getFromInputStream(inputStream);

                    // Actualiza el SVGImageView con el SVG cargado
                    svgImage.setSVG(svg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Inicia la Thread para cargar la imagen
            thread.start();
        }


    }
}
