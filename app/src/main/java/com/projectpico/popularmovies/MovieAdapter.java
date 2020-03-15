package com.projectpico.popularmovies;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    // Invariant of the MovieAdapter.java class
    // 1. a
    private int count;

    /**
     * public int getItemCount()
     *  Accessor method that retrieves the number of items in this Adapter.
     * @return int
     *  The number of items in this Adapter
     */
    @Override
    public int getItemCount() {
        return count;
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /**
     *
     */
    public static class MovieViewHolder extends RecyclerView.ViewHolder {

    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder holder, int position) {

    }
}
