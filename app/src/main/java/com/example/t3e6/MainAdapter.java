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

public class MainAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private ArrayList<Movie> movies;
    private int selectedPos = RecyclerView.NO_POSITION;
    private View.OnClickListener selectionListener = null;
    private View.OnClickListener deselectionListener = null;

    public MainAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        Movie movie = movies.get(position);
        holder.setMovieName(movie.getMovieName());
        holder.setDirectorName(movie.getDirectorName());
        holder.setCoverId(movie.getCoverId());
        holder.setAgeRating(movie.getAgeRating());
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
