package com.example.t3e6;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String BUNDLE_SELECTED_MOVIE_POS = "com.example.t3e6.BUNDLE_SELECTED_MOVIE_POS";
    private static final int REQ_FAVORITES = 1;
    private static final int REQ_ADD = 2;

    private RecyclerView browserRecyclerView;
    private Button toggleAbButton;
    private MainAdapter mainAdapter;

    private ConstraintLayout selectionView;
    private TextView selectedName;
    private TextView selectedDirector;
    private TextView selectedTheater;
    private TextView selectedDuration;
    private TextView selectedReleaseDate;
    private TextView selectedAgeRating;
    private ImageView selectedCover;
    private ImageView selectedFavorite;

    private ConstraintLayout noSelectionMessage;

    private ArrayList<Movie> movies;
    private int selectedMoviePos;

    private boolean initialized = false;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        browserRecyclerView = findViewById(R.id.mainRecyclerView);
        toggleAbButton = findViewById(R.id.toggleAB);
        noSelectionMessage = findViewById(R.id.noSelectionMessage);
        selectionView = findViewById(R.id.selectedMovieView);
        selectedName = selectionView.findViewById(R.id.selectedName);
        selectedDirector = selectionView.findViewById(R.id.selectedDirector);
        selectedTheater = selectionView.findViewById(R.id.selectedTheater);
        selectedDuration = selectionView.findViewById(R.id.selectedDuration);
        selectedReleaseDate = selectionView.findViewById(R.id.selectedReleaseDate);
        selectedAgeRating = selectionView.findViewById(R.id.selectedAgeRating);
        selectedCover = selectionView.findViewById(R.id.selectedCover);
        selectedFavorite = selectionView.findViewById(R.id.selectedFavorite);

        movies = MovieList.getInstance();
        mainAdapter = new MainAdapter(movies);
        mainAdapter.setSelectionListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedMovie(browserRecyclerView.getChildAdapterPosition(v));
            }
        });
        mainAdapter.setDeselectionListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedMovie(RecyclerView.NO_POSITION);
            }
        });

        browserRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        browserRecyclerView.setAdapter(mainAdapter);

        setSelectedMovie(RecyclerView.NO_POSITION);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_SELECTED_MOVIE_POS, selectedMoviePos);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int selectedMoviePos = savedInstanceState.getInt(BUNDLE_SELECTED_MOVIE_POS);
        mainAdapter.setSelectedPos(selectedMoviePos);
        setSelectedMovie(selectedMoviePos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuDetailedList: {
                Intent intent = new Intent(this, DetailedViewActivity.class);
                startActivity(intent);
                return true;
            }

            case R.id.menuFavorites: {
                Intent intent = new Intent(this, FavoritesActivity.class);
                startActivityForResult(intent, REQ_FAVORITES);
                return true;
            }

            case R.id.menuAdd: {
                Intent intent = new Intent(this, AddMovieActivity.class);
                startActivityForResult(intent, REQ_ADD);
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_FAVORITES && resultCode == RESULT_OK) {
            mainAdapter.notifyDataSetChanged();
            setSelectedMovie(selectedMoviePos);
        } else if (requestCode == REQ_ADD && resultCode == RESULT_OK) {
            Movie movie = (Movie)data.getSerializableExtra(AddMovieActivity.EXTRA_MOVIE);
            movies.add(movie);
            browserRecyclerView.getAdapter().notifyItemRangeChanged(movies.size() - 1, 1);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void setSelectedMovie(int pos) {
        selectedMoviePos = pos;
        boolean visible = pos != RecyclerView.NO_POSITION;

        if (visible) {
            Movie movie = movies.get(pos);

            selectedName.setText(movie.getMovieName());
            selectedDirector.setText(movie.getDirectorName());
            selectedTheater.setText(movie.getTheaterName());
            selectedDuration.setText(movie.getFormattedDuration());
            selectedReleaseDate.setText(movie.getFormattedReleaseDate());
            selectedAgeRating.setText(movie.getAgeRating().getText());
            selectedCover.setImageResource(movie.getCoverId());
            selectedFavorite.setVisibility(movie.isFavorite() ? View.VISIBLE : View.INVISIBLE);
        }

        noSelectionMessage.setVisibility(visible ? View.GONE : View.VISIBLE);
        selectionView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void toggleActionBarVisibility(View v) {
        ActionBar bar = getSupportActionBar();
        if (bar.isShowing()) {
            bar.hide();
        } else {
            bar.show();
        }
    }
}