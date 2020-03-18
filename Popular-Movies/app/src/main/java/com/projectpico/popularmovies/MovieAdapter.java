package com.projectpico.popularmovies;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**********************************************************************************************************************
 * Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView.
 * 
 * @author mlewis 
 * @version March 16, 2020
 *********************************************************************************************************************/
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    // Invariant of the MovieAdapter.java class
    //  1. The instance variable itemCount is required by the concrete class getItemCount when extending a RecyclerView
    //    Adapter. It provides a count of the number of items in our data source.
    //  2. The instance variables movieTitle, movieReleaseDate, movieVoteAverage, moviePlot, and moviePoster are
    //    references to the data items that we retrieve from our data set.
    //  3. The class variable TAG is used for debugging purposes.
    private int itemCount;
    private String movieTitle;
    private String movieReleaseDate;
    private String movieVoteAverage;
    private String moviePlot;
    private String moviePoster;
    private static final String TAG = MovieAdapter.class.getSimpleName();

    /**
     * public MovieAdapter(ArrayList<String> json)
     *  Initializes a custom Adapter, which provides a binding from our movies API data set to views that are displayed
     *  within the apps RecyclerView.
     * @param json
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for a new MovieAdapter.
     */
    public MovieAdapter(ArrayList<String> json) {
        this.movieReleaseDate = json.get(0);
        this.moviePoster = json.get(1);
        this.movieVoteAverage = json.get(2);
        this.moviePlot = json.get(1);
    }

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
         * Get the view id's from our ViewHolder and replace the contents of the view with the appropriate data from
         * our data set in the specified position
         */
        holder.getAdapterPosition();


        holder.getTitle().setText(movieTitle);
        holder.getReleaseDate().setText(movieReleaseDate);
        holder.getVoteAverage().setText(movieVoteAverage);
        holder.getMoviePlot().setText(moviePlot);
        holder.getMoviePoster().setText(moviePoster);

        // FIXME: 3/16/20 bind the position
    }

    /******************************************************************************************************************
     * A MovieViewHolder describes an item view and metadata about its place within the RecyclerView. RecyclerViews
     * should subclass ViewHolder and add fields for caching potentially expensive View.findViewById(int) results.
     *
     * @author mlewis
     * @version March 16, 2020
     *****************************************************************************************************************/
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final TextView title;
        private final TextView releaseDate;
        private final TextView voteAverage;
        private final TextView moviePlot;
        private final TextView moviePoster;

        /**
         * public MovieViewHolder(CardView view)
         *  Initializes a custom ViewHolder, which describes an item view and metadata about its place within a
         *  RecyclerView.
         * @exception OutOfMemoryError
         *  Indicates insufficient memory for a new MovieViewHolder.
         */
        public MovieViewHolder(CardView view) {
            super(view);
            cardView = view.findViewById(R.id.cv_movies);
            title = view.findViewById(R.id.tv_movie_title);
            releaseDate = view.findViewById(R.id.tv_movie_release_date);
            voteAverage = view.findViewById(R.id.tv_movie_vote_average);
            moviePlot = view.findViewById(R.id.tv_movie_plot);
            moviePoster = view.findViewById(R.id.iv_movie_poster);
        }

        /**
         * public CardView getCardView()
         *  Accessor method to retrieve the CardView reference variable.
         * @return CardView
         *  The id for the CardView layout file.
         */
        public CardView getCardView() { return cardView; }

        /**
         * public TextView getTitle()
         *  Accessor method to retrieve the movie title reference variable.
         * @return TextView
         *  The id for the movie title layout file.
         */
        public TextView getTitle() { return title; }

        /**
         * public TextView getReleaseDate()
         *  Accessor method to retrieve the release date reference variable.
         * @return TextView
         *  The id for the release date layout file.
         */
        public TextView getReleaseDate() { return releaseDate; }

        /**
         * public TextView getVoteAverage()
         *  Accessor method to retrieve the movie vote average reference variable.
         * @return TextView
         *  The id for the movie vote average layout file.
         */
        public TextView getVoteAverage() { return voteAverage; }

        /**
         * public TextView getMoviePlot()
         *  Accessor method to retrieve the movie plot reference variable.
         * @return TextView
         *  The id for the movie plot layout file.
         */
        public TextView getMoviePlot() { return moviePlot; }

        /**
         * public ImageView getMoviePoster()
         *  Accessor method to retrieve the movie poster reference variable.
         * @return ImageView
         *  The id for the movie poster layout file.
         */
        public TextView getMoviePoster() { return moviePoster; }
    }
}
