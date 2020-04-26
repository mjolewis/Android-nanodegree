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

/**********************************************************************************************************************
 * The top level UI.
 *
 * @author mlewis
 * @version April 26, 2020
 *********************************************************************************************************************/
public class MovieRecyclerActivity extends AppCompatActivity implements Callback {
    // Invariant of the MainActivity.java class
    //  1. The instance variable recyclerView is a reference to the a RecyclerView object that acts as a container
    //     for displaying movie data.
    //  2. The instance variable defaultPath is the default path used in the webservice request.
    //  3. The class variable PATH_POPULAR is a reference to the popular movie path from
    //     {https://www.themoviedb.org/?_dc=1584461074}. This is used to fetch popular movies.
    //  4. The class variable PATH_TOP_RATE is a reference to the top rated movie path from
    //     {https://www.themoviedb.org/?_dc=1584461074}. This is used to fetch top rated movies.
    //  5. THe class variable FAVORITES is a reference to the users favorite movies.
    //  6. The class variable SPAN_COUNT indicates the number of columns created by the GridLayoutManager.
    //  7. The class variable TAG is used for debugging purposes.
    private static RecyclerView recyclerView;
    private static String defaultPath = "3/movie/popular";
    private static final String PATH_POPULAR = "3/movie/popular";
    private static final String PATH_TOP_RATED = "3/movie/top_rated";
    //private static final String FAVORITES = "test";
    private static final int SPAN_COUNT = 2;
    private static final String TAG = MovieRecyclerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));

        URL movieSearchUrl = NetworkUtils.UriBuilder(defaultPath);
        new MovieDatabaseQueryTask(this).execute(movieSearchUrl);
    }

    /*
     * private void getUrlAndNetworkConnection
     *  Builds a url that is passed to a background thread. The background thread processes a network requests to
     *  retrieve movie data via the tbmovie.org API.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new background task.
     */
    private void getUrlAndNetworkConnection() {
        URL movieSearchUrl = NetworkUtils.UriBuilder(defaultPath);
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
            defaultPath = PATH_POPULAR;
        } else if (id == R.id.menu_highest_rated) {
            defaultPath = PATH_TOP_RATED;
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
            String networkResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
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
