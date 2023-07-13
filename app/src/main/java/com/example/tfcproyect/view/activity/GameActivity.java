package com.example.tfcproyect.view.activity;

import android.os.Bundle;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfcproyect.R;
import com.example.tfcproyect.controller.adapterRequest.RequestGame;

public class GameActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private RecyclerView recyclerView;
    private RequestGame requestGame;
    //private CalendarAdapter calendarAdapter;
    //private RecyclerView calendarRecyclerView;
    //private LocalDate localDateNow;
    //private TextView monthYearTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();
        /*calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearTextView = findViewById(R.id.monthYearTextView);
        localDateNow = LocalDate.now();
        setMonthView();*/
        initializeVariables();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                requestGame.searchGames(i, i1 + 1, i2);
            }
        });

    }

    public void initializeVariables() {
        requestGame = new RequestGame(this);
        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.gamesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(requestGame.getGameAdapter());
    }

    /*private void setMonthView() {
        monthYearTextView.setText(monthYearFromDate(localDateNow));
        ArrayList<String> daysOfMonth = daysInMonthArray(localDateNow);
        calendarAdapter = new CalendarAdapter(daysOfMonth);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7, RecyclerView.VERTICAL, false);
        calendarRecyclerView.setLayoutManager(gridLayoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        calendarAdapter.notifyDataSetChanged();
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {

        ArrayList<String> daysInMonthList = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = localDateNow.withDayOfMonth(1);
        int dayOfweek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            if (i < dayOfweek || i >= daysInMonth + dayOfweek) {
                daysInMonthList.add("");
            } else {
                daysInMonthList.add(String.valueOf(i - dayOfweek + 1));
            }
        }
        return daysInMonthList;
    }

    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }*/


    /*public void previousMonthAction(View view) {

        localDateNow = localDateNow.minusMonths(1);
        setMonthView();

    }

    public void nextMonthAction(View view) {
        localDateNow = localDateNow.plusMonths(1);
        setMonthView();
    }*/
}