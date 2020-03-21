package com.projectpico.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**********************************************************************************************************************
 * Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView.
 * 
 * @author mlewis 
 * @version March 20, 2020
 *********************************************************************************************************************/
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    // Invariant of the MovieAdapter.java class
    //  1. The instance variable movieArrayList is a list of move objects with the associated meta data for each move.
    //  3. The instance variable listener listens for user clicks to activate the movie detail activity.
    //  2. The class variable TAG is used for debugging purposes.
    private ArrayList<Movie> movieArrayList;
    private Callback activityCallback;
    private static final String BASE_URL = "https://image.tmdb.org/t/p/w185";
    private static final String TAG = MovieAdapter.class.getSimpleName();

    /**
     * public MovieAdapter(ArrayList<String> json)
     *  Initializes a custom Adapter, which provides a binding from our movies API data set to views that are displayed
     *  within the apps RecyclerView.
     * @param movieArrayList
     *  A movie object containing multiple ArrayLists of movie data such as "title', "releaseDate", "posterPaths",
     *  "voteAverages", and "plots".
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for a new MovieAdapter.
     */
    public MovieAdapter(Context activityContext, ArrayList<Movie> movieArrayList) {
        this.movieArrayList = movieArrayList;
        activityCallback = (Callback) activityContext;
        Log.d(TAG, "New adapter constructed.");
    }

    /**
     * public int getItemCount()
     *  Accessor method that retrieves the number of items in this Adapter.
     * @return int
     *  The number of items in this Adapter.
     */
    @Override
    public int getItemCount() {
        return movieArrayList.size();
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
        Log.d(TAG, "Creating new viewholder.");
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
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        Movie currentMovie = movieArrayList.get(position);
        holder.bind(currentMovie);

        String fullUrl = BASE_URL + currentMovie.getPosterPath();
        Picasso.get()
                .load(fullUrl)
                //.placeholder()
                //.error()
                .into(holder.imageView);

        Log.d(TAG, currentMovie.getPosterPath());
    }

    /******************************************************************************************************************
     * A MovieViewHolder describes an item view and metadata about its place within the RecyclerView. RecyclerViews
     * should subclass ViewHolder and add fields for caching potentially expensive View.findViewById(int) results.
     *
     * @author mlewis
     * @version March 20, 2020
     *****************************************************************************************************************/
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private String posterPath;
        private String movieTitle;
        private String movieReleaseDate;
        private String voteAverage;
        private String moviePlot;

        /**
         * public MovieViewHolder(CardView view)
         *  Initializes a custom ViewHolder, which describes an item view and metadata about its place within a
         *  RecyclerView. The view holder also registers a click listener for each view.
         * @param view
         *  The view type that our ViewHolder contains.
         * @exception OutOfMemoryError
         *  Indicates insufficient memory for a new MovieViewHolder.
         */
        public ViewHolder(CardView view) {
            super(view);
            CardView cardView = view.findViewById(R.id.cv_movies);
            imageView = cardView.findViewById(R.id.info_image);
            imageView.setOnClickListener(this);
        }

        /**
         * public void onClick(View v)
         *  A click listener attached to each view within the recyclerview. Once a view is clicked, an explicit intent
         *  will be created to start the MovieDetailActivity.
         * @param v
         *  The current view item.
         */
        @Override
        public void onClick(View v) {
            activityCallback.onMovieSelected(posterPath, movieTitle, movieReleaseDate, voteAverage, moviePlot);
        }

        /**
         * public void bind(Movie currentMovie)
         *  Extracts details from the movie object, which will be used as the params of our onClick listener.
         * @param currentMovie
         *  The movie in the current view.
         */
        public void bind(Movie currentMovie) {
            posterPath = currentMovie.getPosterPath();
            movieTitle = currentMovie.getTitle();
            movieReleaseDate = currentMovie.getReleaseDate();
            voteAverage = currentMovie.getVoteAverage();
            moviePlot = currentMovie.getPlot();
        }
    }
}
