package com.projectpico.popularmovies;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**********************************************************************************************************************
 * 
 * 
 * @author mlewis 
 * @version March 16, 2020
 *********************************************************************************************************************/
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    // Invariant of the MovieAdapter.java class
    // 1. The instance variable itemCount is required by the concrete class getItemCount when extending a RecyclerView
    //    Adapter. It provides a count of the number of items in our data source.
    private int itemCount;
    private static final String TAG = "MovieAdapter";

    /**
     * public int getItemCount()
     *  Accessor method that retrieves the number of items in this Adapter.
     * @return int
     *  The number of items in this Adapter.
     */
    @Override
    public int getItemCount() {
        return itemCount;
    }

    /******************************************************************************************************************
     * 
     * 
     * @author mlewis 
     * @version March 16, 2020
     *****************************************************************************************************************/
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        /*
        * Provide a reference to the views for each data item.
         */
        public MovieViewHolder(CardView view) {
            super(view);
            cardView = view;
        }
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
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_movie_detail, parent, false);
        return new MovieViewHolder(cardView);
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
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Log.d(TAG, "Element " + position + " set.");

        /*
         * Get the inflated cardView from our MovieAdapter and use that View to get a reference to the layouts imageView.
         * Get an element from our data set at the specified position and and replace the contents of the view with
         * this element.
         */
        CardView cardView = holder.cardView;
        ImageView imageView = cardView.findViewById(R.id.iv_movie_poster);
        // FIXME: 3/16/20 set the image and text
    }
}
