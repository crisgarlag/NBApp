package com.example.tfcproyect.view.activity;

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

import com.example.tfcproyect.R;
import com.example.tfcproyect.controller.requests.RequestDefenseStat;
import com.squareup.picasso.Picasso;

public class TabDefense extends Fragment {

    private RecyclerView recyclerView;
    private RequestDefenseStat requestDefenseStat;
    private ImageView imagePlayer;
    private TextView playerNameTextView;
    private String id;
    private String playerName;
    private String urlPhoto;

    public TabDefense(String id, String playerName, String urlPhoto) {
        super();
        this.id = id;
        this.playerName = playerName;
        this.urlPhoto = urlPhoto;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestDefenseStat = new RequestDefenseStat(getContext());
        requestDefenseStat.searchStats(id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_defense, container, false);
        initializerView(view);
        return view;
    }

    public void initializerView(View view) {

        playerNameTextView = view.findViewById(R.id.playerNameTextViewDefense);
        playerNameTextView.setText(playerName);
        recyclerView = view.findViewById(R.id.statsRecyclerViewDefense);
        imagePlayer = view.findViewById(R.id.urlPhotoimageViewDefense);
        Picasso.get().load(urlPhoto).into(imagePlayer);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(requestDefenseStat.getStatAdapter());
    }

}

