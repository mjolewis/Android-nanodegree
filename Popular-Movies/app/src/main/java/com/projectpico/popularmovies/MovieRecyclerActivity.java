package com.projectpico.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.projectpico.popularmovies.utilities.JsonUtils;
import com.projectpico.popularmovies.utilities.NetworkUtils;

import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;

public class MovieRecyclerActivity extends AppCompatActivity implements MovieAdapter.Callback {
    // Invariant of the MainActivity.java class
    //  1. The instance variable recyclerView is a reference to the a RecyclerView object that acts as a container
    //     for displaying movie data.
    //  2. The instance variable defaultQueryParam refers to the type of movies the user wants to query.
    //  3. The class variable SPAN_COUNT indicates the number of columns created by the GridLayoutManager.
    //  4. The class variables POPULAR_MOVIES and TOP_RATED_MOVIES refer to the query options provided to the user and
    //     that are used in our tbmovie.org API request.
    //  5. The class variable TAG is used for debugging purposes.
    private static RecyclerView recyclerView;
    private static String path = "3/movie/popular";
    private static final String PATH_POPULAR = "3/movie/popular";
    private static final String PATH_TOP_RATED = "3/movie/top_rated";
    private static final int SPAN_COUNT = 2;
    private static final String TAG = MovieRecyclerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));

        URL movieSearchUrl = NetworkUtils.UriBuilder(path);
        new MovieDatabaseQueryTask(this).execute(movieSearchUrl);
    }

    /**
     * public void getUrlAndNetworkConnection
     *  Builds a url that is passed to a background thread. The background thread processes a network requests to
     *  retrieve movie data via the tbmovie.org API.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new background task.
     */
    public void getUrlAndNetworkConnection() {
        URL movieSearchUrl = NetworkUtils.UriBuilder(path);
        new MovieDatabaseQueryTask(this).execute(movieSearchUrl);
    }

    /**
     * public boolean onCreateOptionsMenu(Menu menu)
     *  Inflates the menu item.
     * @param menu
     *  The menu item to inflate
     * @return boolean
     *  True if menu was created. False otherwise.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_query_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * public void onMenuQuerySelected(MenuItem item)
     *  Captures the type of query the user wants to use in the API request.
     * @param item
     *  The MenuItem that the user selected. Indicates the type of movie the user wants to search for.
     */
    public void onMenuQuerySelected(MenuItem item) {
        int id = item.getItemId();
        String title = (String) item.getTitle();

        if (id == R.id.menu_most_popular_movie) {
            path = PATH_POPULAR;
        } else if (id == R.id.menu_highest_rated) {
            path = PATH_TOP_RATED;
        }

        getUrlAndNetworkConnection();
        Log.d(TAG, "Menu item " + title + " was clicked");
    }

    @Override
    public void onMovieSelected(String posterPath, String movieTitle, String movieReleaseDate, String voteAverage,
                                String moviePlot) {
        Intent intent = new Intent(this, MovieDetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(MovieDetailActivity.EXTRA_POSTER_PATH, posterPath);
        bundle.putString(MovieDetailActivity.EXTRA_MOVIE_TITLE, movieTitle);
        bundle.putString(MovieDetailActivity.EXTRA_MOVIE_RELEASE_DATE, movieReleaseDate);
        bundle.putString(MovieDetailActivity.EXTRA_MOVIE_VOTE_AVERAGE, voteAverage);
        bundle.putString(MovieDetailActivity.EXTRA_MOVIE_PLOT, moviePlot);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    /******************************************************************************************************************
     * The ApiRequest class extends AsyncTask to perform a network request on a background thread. The results on the
     * network request are then published on our UI thread.
     *
     * @author mlewis
     * @version March 20, 2020
     *****************************************************************************************************************/
    public static class MovieDatabaseQueryTask extends AsyncTask<URL, Void, ArrayList<Movie>> {
        private String networkResults;
        private ArrayList<Movie> moviesObject;
        private WeakReference<Context> context;

        /**
         * public MovieDatabaseQueryTask(Context context)
         *  Initializes a new MovieDatabaseQueryTask
         * @param context
         *  The context of the parent thread.
         * @exception OutOfMemoryError
         *  Indicates insufficient memory for the weak reference.
         */
        public MovieDatabaseQueryTask(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "AsyncTask has started working.");
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Movie> doInBackground(URL... urls) {
            Log.d(TAG, "Background thread has started.");

            URL searchUrl = urls[0];
            networkResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            try {
                moviesObject = JsonUtils.parseMovieData(networkResults);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return moviesObject;
        }

        /**
         * protected void onPostExecute
         *  Executes on the main thread.
         * @param moviesObject
         *  An ArrayList<Movie> object
         * @exception OutOfMemoryError
         *  Indicates insufficient memory for this new Movie Adapter.
         */
        @Override
        protected void onPostExecute(ArrayList<Movie> moviesObject) {
            super.onPostExecute(moviesObject);

            MovieAdapter adapter = new MovieAdapter(context.get(), moviesObject);
            recyclerView.setAdapter(adapter);
            recyclerView.invalidate();
        }
    }
}
