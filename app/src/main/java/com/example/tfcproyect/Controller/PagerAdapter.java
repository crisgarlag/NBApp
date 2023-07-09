package com.example.tfcproyect.Controller;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tfcproyect.view.StatsActivity;
import com.example.tfcproyect.view.TabAttack;
import com.example.tfcproyect.view.TabDefense;

public class PagerAdapter extends FragmentStateAdapter {

    private String id;
    private String urlPhoto;
    private String playerName;



    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public PagerAdapter(StatsActivity statsActivity, String id, String playerName, String urlPhoto) {
        super(statsActivity);
        this.id = id;
        this.urlPhoto = urlPhoto;
        this.playerName = playerName;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new TabAttack(id, playerName, urlPhoto);
            case 1: return new TabDefense(id, playerName, urlPhoto);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
