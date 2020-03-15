package com.projectpico.popularmovies;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    // Invariant of the MovieAdapter.java class
    // 1. The instance variable itemCount is required by the concrete class getItemCount when extending a RecyclerView
    //    Adapter. It provides a count of the number of items in our data source.
    private int itemCount;

    /**
     * public int getItemCount()
     *  Accessor method that retrieves the number of items in this Adapter.
     * @return int
     *  The number of items in this Adapter
     */
    @Override
    public int getItemCount() {
        return itemCount;
    }

    /**
     *
     */
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public View itemView;

        /*
        * Provide a reference to the views for each data item.
         */
        public MovieViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder holder, int position) {

    }
}
