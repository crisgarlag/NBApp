package com.example.tfcproyect.view.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tfcproyect.controller.adapters.PagerAdapter;
import com.example.tfcproyect.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StatsActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private String id, urlPhoto, playerName;
    private String tabsNames[] = new String[]{"Ataque", "Defensa"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        getSupportActionBar().hide();
        id = this.getIntent().getExtras().getString("id");
        urlPhoto = this.getIntent().getExtras().getString("urlPhoto");
        playerName = this.getIntent().getExtras().getString("playerName");
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

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.view.setBackgroundColor(getResources().getColor(R.color.orange_200));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.view.setBackgroundColor(getResources().getColor(R.color.orange_600));

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}