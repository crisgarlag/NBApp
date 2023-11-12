package com.example.tfcproyect.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfcproyect.R;
import com.example.tfcproyect.controller.adapterRequest.RequestAtackStat;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class TabAttack extends Fragment {

    private RecyclerView recyclerView;
    private RequestAtackStat requestAtackStat;
    private ImageView imagePlayer;
    private TextView playerNameTextView;
    private String id, playerName, urlPhoto;

    public TabAttack(String id, String playerName, String urlPhoto) {
        super();
        this.id = id;
        this.playerName = playerName;
        this.urlPhoto = urlPhoto;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestAtackStat = new RequestAtackStat(getContext());
        requestAtackStat.searchStats(id);


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_attack, container, false);
        initializerView(view);
        return view;
    }

    public void initializerView(View view) {
        playerNameTextView = view.findViewById(R.id.playerNameTextViewAtack);
        playerNameTextView.setText(playerName);
        recyclerView = view.findViewById(R.id.statsRecyclerViewAttack);
        imagePlayer = view.findViewById(R.id.urlPhotoimageViewAtack);
        Picasso.get().load(urlPhoto).into(imagePlayer);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(requestAtackStat.getStatAdapter());
    }

}
