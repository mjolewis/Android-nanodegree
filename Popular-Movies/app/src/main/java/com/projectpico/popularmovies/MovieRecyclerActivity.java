package com.projectpico.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MovieRecyclerActivity extends AppCompatActivity {
    // Invariant of the MainActivity.java class
    //  1. The instance variable recyclerView is a reference to the a RecyclerView object that acts as a container
    //     for displaying movie data.
    //  2. The instance variable queryParam refers to the type of movies the user wants to query.
    //  3. The class variable SPAN_COUNT indicates the number of columns created by the GridLayoutManager.
    //  4. The class variables POPULAR_MOVIES and TOP_RATED_MOVIES refer to the query options provided to the user and
    //     that are used in our tbmovie.org API request.
    //  5. The class variable TAG is used for debugging purposes.
    private RecyclerView recyclerView;
    private static String queryParam;
    private static final int SPAN_COUNT = 2;
    private static final String POPULAR_MOVIES = "popular";
    private static final String TOP_RATED_MOVIES = "top_rated";
    private static final String TAG = "MovieRecyclerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);

        recyclerView = findViewById(R.id.rv_movies);
        setRecyclerView();
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

    /*
     * private void setRecyclerView()
     *  Sets up a recyclerView using the GridLayoutManager.
     * @postcondition
     *  A new GridLayoutManager has been initialized.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new GridLayoutManager.
     */
    private void setRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));

        // FIXME: 3/16/20
        recyclerView.setAdapter(new MovieAdapter());
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
            queryParam = POPULAR_MOVIES;
        } else if (id == R.id.menu_highest_rated) {
            queryParam = TOP_RATED_MOVIES;
        }
        Log.d(TAG, "Menu item " + title + " was clicked");
    }
}
