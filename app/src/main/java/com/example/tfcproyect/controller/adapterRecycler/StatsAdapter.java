package com.example.tfcproyect.controller.adapterRecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tfcproyect.R;

import java.util.List;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsViewHolder> {

    private List<String> stats;

    public StatsAdapter(List<String> stats){
        this.stats = stats;

    }

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stats, parent, false);
        return new StatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {
        String stat = stats.get(position);
        holder.bind(stat);

    }

    @Override
    public int getItemCount() {
        return stats.size();
    }

    public static class StatsViewHolder extends RecyclerView.ViewHolder {

        private TextView statTextView;

        public StatsViewHolder(@NonNull View itemView) {
            super(itemView);
            statTextView = itemView.findViewById(R.id.statsTextView);
        }

        public void bind(String stat) {
           statTextView.setText(stat);
        }
    }
}
