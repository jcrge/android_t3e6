package com.example.t3e6;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {
    private ListView favoritesView;

    private ArrayList<Movie> movies;
    private ArrayAdapter<String> adapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoritesView = findViewById(R.id.favoritesView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        movies = MovieList.getInstance();
        ArrayList<String> movieNames = new ArrayList<>();
        for (Movie movie: movies) {
            movieNames.add(movie.getMovieName());
        }

        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_multiple_choice, movieNames);
        favoritesView.setAdapter(adapter);
        favoritesView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).isFavorite()) {
                favoritesView.setItemChecked(i, true);
            }
        }

        favoritesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = movies.get(position);
                movie.setFavorite(!movie.isFavorite());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }
}