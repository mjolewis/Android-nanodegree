package com.projectpico.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.projectpico.popularmovies.utilities.JsonUtils;
import com.projectpico.popularmovies.utilities.NetworkUtils;

import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;

public class MovieRecyclerActivity extends AppCompatActivity {
    // Invariant of the MainActivity.java class
    //  1. The instance variable recyclerView is a reference to the a RecyclerView object that acts as a container
    //     for displaying movie data.
    //  2. The instance variable defaultQueryParam refers to the type of movies the user wants to query.
    //  3. The class variable SPAN_COUNT indicates the number of columns created by the GridLayoutManager.
    //  4. The class variables POPULAR_MOVIES and TOP_RATED_MOVIES refer to the query options provided to the user and
    //     that are used in our tbmovie.org API request.
    //  5. The class variable TAG is used for debugging purposes.
    private RecyclerView recyclerView;
    private static String defaultQueryParam = "popular.asc";
    private static final int SPAN_COUNT = 2;
    private static final String POPULAR_MOVIES = "popular.asc";
    private static final String TOP_RATED_MOVIES = "vote_average.asc";
    private static final String TAG = MovieRecyclerActivity.class.getSimpleName();
    private static String networkResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        recyclerView.setHasFixedSize(true);

        getUrlAndNetworkConnection();

        Movie movie = null;
        try {
            movie = JsonUtils.parseMovieData(networkResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Setting adapter.");
        recyclerView.setAdapter(movie.getPosterPath());
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
            defaultQueryParam = POPULAR_MOVIES;
        } else if (id == R.id.menu_highest_rated) {
            defaultQueryParam = TOP_RATED_MOVIES;
        }

        getUrlAndNetworkConnection();
        Log.d(TAG, "Menu item " + title + " was clicked");
    }

    public void getUrlAndNetworkConnection() {
        URL movieSearchUrl = NetworkUtils.UriBuilder(defaultQueryParam);
        new MovieDatabaseQueryTask().execute(movieSearchUrl);
    }

    /******************************************************************************************************************
     * The ApiRequest class extends AsyncTask to perform a network request on a background thread. The results on the
     * network request are then published on our UI thread.
     *
     * @author mlewis
     * @version March 16, 2020
     *****************************************************************************************************************/
    public static class MovieDatabaseQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            Log.d(TAG, "AsyncTask has started working.");
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            Log.d(TAG, "Background thread has started.");
            URL searchUrl = urls[0];
            networkResult = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            return networkResult;
        }

        @Override
        protected void onPostExecute(String movieSearchResults) {
            super.onPostExecute(movieSearchResults);
        }
    }
}
