package com.example.tfcproyect.controller.adapterRecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfcproyect.R;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    private List<String> dayOfWeekList;
    private View.OnClickListener onClickListener;


    public CalendarAdapter(List<String> dayOfWeekList) {
        this.dayOfWeekList = dayOfWeekList;

    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar, parent, false);
        view.setOnClickListener(onClickListener);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        holder.bind(dayOfWeekList.get(position));

    }

    @Override
    public int getItemCount() {
        return dayOfWeekList.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class CalendarViewHolder extends RecyclerView.ViewHolder {

        private TextView cellDayTextView;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            cellDayTextView = itemView.findViewById(R.id.cellDayTextView);
        }

        public void bind(String day){
            cellDayTextView.setText(day);
        }
    }


}
