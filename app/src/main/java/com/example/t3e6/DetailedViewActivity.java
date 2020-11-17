package com.example.t3e6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class DetailedViewActivity extends AppCompatActivity {
    public static final String EXTRA_SELECTED_MOVIE = "com.example.t3e6.EXTRA_SELECTED_MOVIE";

    private RecyclerView detailedRecyclerView;

    private ArrayList<Movie> movies;
    private DetailedAdapter detailedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailedRecyclerView = findViewById(R.id.detailedRecyclerView);

        movies = MovieList.getInstance();
        detailedAdapter = new DetailedAdapter(movies);
        detailedAdapter.setSelectionListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = detailedRecyclerView.getChildAdapterPosition(v);
                Intent intent = new Intent(getApplicationContext(), SummaryActivity.class);
                intent.putExtra(EXTRA_SELECTED_MOVIE, pos);
                startActivity(intent);
            }
        });

        detailedRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        detailedRecyclerView.setAdapter(detailedAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}