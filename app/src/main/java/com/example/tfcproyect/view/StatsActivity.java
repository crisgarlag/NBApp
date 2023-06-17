package com.example.tfcproyect.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.example.tfcproyect.Controller.PagerAdapter;
import com.example.tfcproyect.R;
import com.example.tfcproyect.Controller.StatsAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class StatsActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private String id;
    private String tabsNames [] = new String[]{"Ataque", "Defensa"};

    private static String API_BALLDONTLIE_URL = "https://www.balldontlie.io/api/v1/season_averages?season=2022&player_ids[]=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        id = this.getIntent().getExtras().getString("id");
        //VER PAGINA 133 para comentar sobre los tabs
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter(this, id));
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabsNames[position]);
                    }
                }).attach();
    }
}