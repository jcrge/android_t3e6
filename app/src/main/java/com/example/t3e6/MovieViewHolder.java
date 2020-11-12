package com.example.t3e6;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private TextView movieNameText;
    private TextView directorNameText;
    private ImageView coverImage;
    private ImageView ageRatingImage;

    public MovieViewHolder(@NonNull ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_entry, parent, false));

        movieNameText = itemView.findViewById(R.id.overviewMovieName);
        directorNameText = itemView.findViewById(R.id.overviewDirectorName);
        coverImage = itemView.findViewById(R.id.overviewCover);
        ageRatingImage = itemView.findViewById(R.id.overviewAgeRating);
    }

    public void setMovieName(String movieName) {
                                             movieNameText.setText(movieName);
                                                                              }

    public void setDirectorName(String directorName) {
                                                   directorNameText.setText(directorName);
                                                                                          }

    public void setCoverId(int coverId) {
        coverImage.setImageResource(coverId);
    }

    public void setAgeRating(Movie.AgeRating ageRating) {
        ageRatingImage.setImageResource(ageRating.getResId());
    }
}
