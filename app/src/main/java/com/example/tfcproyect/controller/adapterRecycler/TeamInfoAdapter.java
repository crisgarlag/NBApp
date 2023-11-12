package com.example.tfcproyect.controller.adapterRecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfcproyect.R;
import com.example.tfcproyect.model.entitys.Team;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;


public class TeamInfoAdapter extends RecyclerView.Adapter<TeamInfoAdapter.TeamInfoViewHolder> {

    private static List<Team> teamList;
    private View.OnClickListener onClickListener;

    public TeamInfoAdapter(List<Team> teamList) {
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public TeamInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_info, parent, false);
        view.setOnClickListener(onClickListener);
        return new TeamInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamInfoViewHolder holder, int position) {
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

    public static List<Team> getTeamList() {
        return teamList;
    }

    public static class TeamInfoViewHolder extends RecyclerView.ViewHolder {

        private TextView headCoachTextView,teamConferenceTextView, teamDivisionTextView, countryTextView
                ,cityTextView, stadiumTextView, addressTextView, lat, lon;

        public TeamInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            headCoachTextView = itemView.findViewById(R.id.teamHeadCoachInfoTextView);
            teamConferenceTextView = itemView.findViewById(R.id.teamConferenceInfoTextView);
            teamDivisionTextView = itemView.findViewById(R.id.teamDivisionInfoTextView);
            countryTextView= itemView.findViewById(R.id.teamCountryInfoTextView);
            cityTextView= itemView.findViewById(R.id.teamStadiumCityInfoTextView);
            stadiumTextView= itemView.findViewById(R.id.teamStadiumNameInfoTextView);
            addressTextView= itemView.findViewById(R.id.addressInfoTextView);
            lat = itemView.findViewById(R.id.teamLatInfoTextView);
            lon = itemView.findViewById(R.id.teamLonInfoTextView);

        }


        public void bind(Team team) {

            headCoachTextView.setText(team.getHeadCoach());
            teamConferenceTextView.setText(team.getConference());
            teamDivisionTextView.setText(team.getDivision());
            countryTextView.setText(team.getStadium().getCountry());
            cityTextView.setText(team.getStadium().getCity());
            stadiumTextView.setText(team.getStadium().getName());
            addressTextView.setText(team.getStadium().getAddress());
            lat.setText(String.valueOf(team.getStadium().getLatLng().latitude));
            lon.setText(String.valueOf(team.getStadium().getLatLng().longitude));

        }

        public TextView getLat() {
            return lat;
        }

        public TextView getLon() {
            return lon;
        }

        public TextView getStadiumTextView() {
            return stadiumTextView;
        }
    }
}
