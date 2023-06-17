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



    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public PagerAdapter(StatsActivity statsActivity, String id) {
        super(statsActivity);
        this.id = id;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new TabAttack(id);
            case 1: return new TabDefense(id);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
