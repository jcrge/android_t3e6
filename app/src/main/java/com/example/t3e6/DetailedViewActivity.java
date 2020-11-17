package com.example.t3e6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class DetailedViewActivity extends AppCompatActivity {
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