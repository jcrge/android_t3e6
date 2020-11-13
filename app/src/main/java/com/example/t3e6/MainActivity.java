package com.example.t3e6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView browserRecyclerView;
    private Button toggleAbButton;
    private MainAdapter mainAdapter;

    private ConstraintLayout selectionView;
    private TextView selectedName;
    private TextView selectedDirector;
    private TextView selectedTheater;
    private TextView selectedReleaseDate;
    private TextView selectedAgeRating;
    private ImageView selectedCover;

    private ConstraintLayout noSelectionMessage;

    private ArrayList<Movie> movies;

    private boolean initialized = false;

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
        selectedReleaseDate = selectionView.findViewById(R.id.selectedReleaseDate);
        selectedAgeRating = selectionView.findViewById(R.id.selectedAgeRating);
        selectedCover = selectionView.findViewById(R.id.selectedCover);

        movies = getDefaultMovieList();
        mainAdapter = new MainAdapter(movies);
        mainAdapter.setSelectionListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = movies.get(browserRecyclerView.getChildAdapterPosition(v));
                selectedName.setText(movie.getMovieName());
                selectedDirector.setText(movie.getDirectorName());
                selectedTheater.setText(movie.getTheaterName());
                selectedReleaseDate.setText(movie.getReleaseDate());
                selectedAgeRating.setText(movie.getAgeRating().getText());
                selectedCover.setImageResource(movie.getCoverId());
                selectionView.setVisibility(View.VISIBLE);
                setSelectedMovieVisible(true);
            }
        });
        mainAdapter.setDeselectionListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedMovieVisible(false);
            }
        });

        browserRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        browserRecyclerView.setAdapter(mainAdapter);

        setSelectedMovieVisible(false);
    }

    private void setSelectedMovieVisible(boolean visible) {
        noSelectionMessage.setVisibility(visible ? View.GONE : View.VISIBLE);
        selectionView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private ArrayList<Movie> getDefaultMovieList() {
        ArrayList<Movie> res = new ArrayList<>();
        Movie movie;

        movie = new Movie();
        movie.setMovieName(getString(R.string.movie_akira_name));
        movie.setDirectorName(getString(R.string.movie_akira_director_name));
        movie.setAgeRating(Movie.AgeRating.R_);
        movie.setCoverId(R.drawable.akira);
        movie.setReleaseDate("1988");
        movie.setSummary(getString(R.string.movie_akira_summary));
        movie.setTheaterName(getString(R.string.theater_1));
        movie.setTrailer("https://www.youtube.com/watch?v=oHg5SJYRHA0");
        res.add(movie);

        movie = new Movie();
        movie.setMovieName(getString(R.string.movie_alien_name));
        movie.setDirectorName(getString(R.string.movie_alien_director_name));
        movie.setAgeRating(Movie.AgeRating.PG);
        movie.setCoverId(R.drawable.alien);
        movie.setReleaseDate("1989");
        movie.setSummary(getString(R.string.movie_alien_summary));
        movie.setTheaterName(getString(R.string.theater_2));
        movie.setTrailer("https://www.youtube.com/watch?v=oHg5SJYRHA0");
        res.add(movie);

        movie = new Movie();
        movie.setMovieName(getString(R.string.movie_blade_name));
        movie.setDirectorName(getString(R.string.movie_blade_director_name));
        movie.setAgeRating(Movie.AgeRating.NC17);
        movie.setCoverId(R.drawable.blade);
        movie.setReleaseDate("1990");
        movie.setSummary(getString(R.string.movie_blade_summary));
        movie.setTheaterName(getString(R.string.theater_3));
        movie.setTrailer("https://www.youtube.com/watch?v=oHg5SJYRHA0");
        res.add(movie);

        movie = new Movie();
        movie.setMovieName(getString(R.string.movie_d2001_name));
        movie.setDirectorName(getString(R.string.movie_d2001_director_name));
        movie.setAgeRating(Movie.AgeRating.G);
        movie.setCoverId(R.drawable.d2001);
        movie.setReleaseDate("2001");
        movie.setSummary(getString(R.string.movie_d2001_summary));
        movie.setTheaterName(getString(R.string.theater_2));
        movie.setTrailer("https://www.youtube.com/watch?v=oHg5SJYRHA0");
        res.add(movie);

        movie = new Movie();
        movie.setMovieName(getString(R.string.movie_dune_name));
        movie.setDirectorName(getString(R.string.movie_dune_director_name));
        movie.setAgeRating(Movie.AgeRating.PG13);
        movie.setCoverId(R.drawable.dune);
        movie.setReleaseDate("1995");
        movie.setSummary(getString(R.string.movie_dune_summary));
        movie.setTheaterName(getString(R.string.theater_1));
        movie.setTrailer("https://www.youtube.com/watch?v=oHg5SJYRHA0");
        res.add(movie);

        movie = new Movie();
        movie.setMovieName(getString(R.string.movie_interstellar_name));
        movie.setDirectorName(getString(R.string.movie_interstellar_director_name));
        movie.setAgeRating(Movie.AgeRating.PG);
        movie.setCoverId(R.drawable.interstellar);
        movie.setReleaseDate("2015");
        movie.setSummary(getString(R.string.movie_interstellar_summary));
        movie.setTheaterName(getString(R.string.theater_3));
        movie.setTrailer("https://www.youtube.com/watch?v=oHg5SJYRHA0");
        res.add(movie);

        movie = new Movie();
        movie.setMovieName(getString(R.string.movie_martian_name));
        movie.setDirectorName(getString(R.string.movie_martian_director_name));
        movie.setAgeRating(Movie.AgeRating.PG);
        movie.setCoverId(R.drawable.martian);
        movie.setReleaseDate("1989");
        movie.setSummary(getString(R.string.movie_martian_summary));
        movie.setTheaterName(getString(R.string.theater_1));
        movie.setTrailer("https://www.youtube.com/watch?v=oHg5SJYRHA0");
        res.add(movie);

        movie = new Movie();
        movie.setMovieName(getString(R.string.movie_matrix_name));
        movie.setDirectorName(getString(R.string.movie_matrix_director_name));
        movie.setAgeRating(Movie.AgeRating.R_);
        movie.setCoverId(R.drawable.matrix);
        movie.setReleaseDate("1901");
        movie.setSummary(getString(R.string.movie_matrix_summary));
        movie.setTheaterName(getString(R.string.theater_2));
        movie.setTrailer("https://www.youtube.com/watch?v=oHg5SJYRHA0");
        res.add(movie);

        movie = new Movie();
        movie.setMovieName(getString(R.string.movie_startrek_name));
        movie.setDirectorName(getString(R.string.movie_startrek_director_name));
        movie.setAgeRating(Movie.AgeRating.PG13);
        movie.setCoverId(R.drawable.startrek);
        movie.setReleaseDate("1985");
        movie.setSummary(getString(R.string.movie_startrek_summary));
        movie.setTheaterName(getString(R.string.theater_2));
        movie.setTrailer("https://www.youtube.com/watch?v=oHg5SJYRHA0");
        res.add(movie);

        return res;
    }
}