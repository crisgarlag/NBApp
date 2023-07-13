package com.example.tfcproyect.view.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tfcproyect.controller.adapterRecycler.PagerAdapter;
import com.example.tfcproyect.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StatsActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private String id;
    private String urlPhoto;
    private String playerName;
    private String tabsNames[] = new String[]{"Ataque", "Defensa"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        getSupportActionBar().hide();
        id = this.getIntent().getExtras().getString("id");
        urlPhoto = this.getIntent().getExtras().getString("urlPhoto");
        playerName = this.getIntent().getExtras().getString("playerName");
        //VER PAGINA 133 para comentar sobre los tabs
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter(this, id, playerName, urlPhoto));
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabsNames[position]);
                    }
                }).attach();
    }
}