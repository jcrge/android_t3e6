package com.example.t3e6;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MovieViewHolder> {
    private ArrayList<Movie> movies;
    private int selectedPos = RecyclerView.NO_POSITION;
    private View.OnClickListener selectionListener = null;
    private View.OnClickListener deselectionListener = null;

    public MainAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView movieNameText;
        public TextView directorNameText;
        public ImageView coverImage;
        public ImageView ageRatingImage;

        public MovieViewHolder(View v) {
            super(v);

            movieNameText = itemView.findViewById(R.id.overviewMovieName);
            directorNameText = itemView.findViewById(R.id.overviewDirectorName);
            coverImage = itemView.findViewById(R.id.overviewCover);
            ageRatingImage = itemView.findViewById(R.id.overviewAgeRating);
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_entry, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        Movie movie = movies.get(position);
        holder.movieNameText.setText(movie.getMovieName());
        holder.directorNameText.setText(movie.getDirectorName());
        holder.coverImage.setImageResource(movie.getCoverId());
        holder.ageRatingImage.setImageResource(movie.getAgeRating().getResId());
        holder.itemView.setBackgroundResource(
                position == selectedPos ? R.color.selectedMovieBg : R.color.defaultMovieBg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPos = position == selectedPos ? RecyclerView.NO_POSITION : position;
                setSelectedPos(newPos);

                View.OnClickListener listener =
                    newPos != RecyclerView.NO_POSITION
                    ? selectionListener
                    : deselectionListener;
                if (listener != null) {
                    listener.onClick(v);
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

    public void setDeselectionListener(View.OnClickListener deselectionListener) {
        this.deselectionListener = deselectionListener;
    }

    public void setSelectedPos(int newPos) {
        int oldPos = selectedPos;
        selectedPos = newPos;

        if (oldPos != RecyclerView.NO_POSITION) {
            notifyItemChanged(oldPos);
        }

        if (newPos != RecyclerView.NO_POSITION) {
            notifyItemChanged(newPos);
        }
    }

    public int getSelectedPos() {
        return selectedPos;
    }
}
