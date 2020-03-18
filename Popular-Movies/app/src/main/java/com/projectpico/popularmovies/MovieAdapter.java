package com.projectpico.popularmovies;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**********************************************************************************************************************
 * Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView.
 * 
 * @author mlewis 
 * @version March 16, 2020
 *********************************************************************************************************************/
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    // Invariant of the MovieAdapter.java class
    //  1. The instance variable moviePoster is a reference to the poster paths for the movie images.
    //  2. The class variable TAG is used for debugging purposes.
    private List<String> posterPaths;
    private static final String TAG = MovieAdapter.class.getSimpleName();

    /**
     * public MovieAdapter(ArrayList<String> json)
     *  Initializes a custom Adapter, which provides a binding from our movies API data set to views that are displayed
     *  within the apps RecyclerView.
     * @param posterPaths
     *  An array of poster paths for each movie image.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for a new MovieAdapter.
     */
    public MovieAdapter(List<String> posterPaths) {
        this.posterPaths = posterPaths;
    }

    /**
     * public int getItemCount()
     *  Accessor method that retrieves the number of items in this Adapter.
     * @return int
     *  The number of items in this Adapter.
     */
    @Override
    public int getItemCount() {
        return posterPaths.size();
    }

    /**
     * public MovieAdapter.MovieViewHolder onCreate(@NonNull ViewGroup parent, int viewType)
     *  Creates new views (invoked by the LayoutManager).
     * @param parent
     *  The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType
     *  The view type of the new View.
     * @return MovieAdapter.MovieViewHolder
     *  A new ViewHolder that holds a View of the given view type.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new MovieViewHolder.
     */
    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_movie_image, parent, false);
        return new ViewHolder(cardView);
    }

    /**
     * public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position)
     *  Replaces the contents of a view (invoked by the LayoutManager).
     * @param holder
     *  The ViewHolder that should be updated to represent the contents of the item at the given position in the data
     *  set.
     * @param position
     *  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "Element " + position + " set.");

        /*
         * Get the view id's from our ViewHolder and replace the contents of the view with the appropriate data from
         * our data set in the specified position
         */
        holder.getAdapterPosition();
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.iv_movie_poster);

        //Picasso.get()
                //.load(posterPaths)
                //.placeholder()
                //.into(imageView);
        // FIXME: 3/18/20 use piccaso?
    }

    /******************************************************************************************************************
     * A MovieViewHolder describes an item view and metadata about its place within the RecyclerView. RecyclerViews
     * should subclass ViewHolder and add fields for caching potentially expensive View.findViewById(int) results.
     *
     * @author mlewis
     * @version March 16, 2020
     *****************************************************************************************************************/
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        /**
         * public MovieViewHolder(CardView view)
         *  Initializes a custom ViewHolder, which describes an item view and metadata about its place within a
         *  RecyclerView.
         * @exception OutOfMemoryError
         *  Indicates insufficient memory for a new MovieViewHolder.
         */
        public ViewHolder(CardView view) {
            super(view);
            cardView = view;
        }
    }
}
