package com.example.t3e6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailedAdapter extends RecyclerView.Adapter<DetailedAdapter.MovieViewHolder> {
    private ArrayList<Movie> movies;
    private View.OnClickListener selectionListener = null;

    public DetailedAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView detailedNameText;
        public TextView detailedDirectorText;
        public TextView detailedTheaterText;
        public TextView detailedReleaseDateText;
        public ImageView detailedAgeRatingImage;
        public ImageView detailedFavoriteImage;
        public ImageView detailedCoverImage;

        public MovieViewHolder(View v) {
            super(v);

            detailedNameText = itemView.findViewById(R.id.detailedName);
            detailedDirectorText = itemView.findViewById(R.id.detailedDirector);
            detailedTheaterText = itemView.findViewById(R.id.detailedTheater);
            detailedReleaseDateText = itemView.findViewById(R.id.detailedReleaseDate);
            detailedAgeRatingImage = itemView.findViewById(R.id.detailedAgeRating);
            detailedFavoriteImage = itemView.findViewById(R.id.detailedFavorite);
            detailedCoverImage = itemView.findViewById(R.id.detailedCover);
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_entry_detailed, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        Movie movie = movies.get(position);
        holder.detailedNameText.setText(movie.getMovieName());
        holder.detailedDirectorText.setText(movie.getDirectorName());
        holder.detailedTheaterText.setText(movie.getTheaterName());
        holder.detailedReleaseDateText.setText(movie.getReleaseDate());
        holder.detailedAgeRatingImage.setImageResource(movie.getAgeRating().getResId());
        holder.detailedFavoriteImage.setImageResource(
                movie.isFavorite()
                ? android.R.drawable.btn_star_big_on
                : android.R.drawable.btn_star_big_off);
        holder.detailedCoverImage.setImageResource(movie.getCoverId());
        holder.itemView.setBackgroundResource(R.color.defaultMovieBg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectionListener != null) {
                    selectionListener.onClick(v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setSelectionListener(View.OnClickListener selectionListener) {
        this.selectionListener = selectionListener;
    }
}
