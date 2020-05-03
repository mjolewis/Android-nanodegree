package com.projectpico.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.projectpico.popularmovies.adapters.MovieAdapter;
import com.projectpico.popularmovies.model.Movie;
import com.projectpico.popularmovies.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

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
    private static MovieAdapter movieAdapter;
    private MovieViewModel movieViewModel;
    private static RecyclerView recyclerView;

    List<Movie.Results> movieList = new ArrayList<>();

    private static String defaultPath = "popular";
    private static final String PATH_POPULAR = "popular";
    private static final String PATH_TOP_RATED = "top_rated";
    private static final String API_KEY = "f8c05a84150db926a13b793d60890bf4";
    private static final int SPAN_COUNT = 2;
    private static final String TAG = MovieRecyclerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.rv_movies);

        /* Get a ViewModel and scope it to this activity's lifecycle */
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        /* Get an instance of the MovieRepository, which provides a MutableLiveData data holder */
        movieViewModel.init(defaultPath, API_KEY);

        /* Create the observer to update the UI */
        Observer<Movie> movieObserver = new Observer<Movie>() {
            @Override
            public void onChanged(Movie movieModel) {
                List<Movie.Results> movieResults = movieModel.getResults();
                movieList.addAll(movieResults);
                movieAdapter.notifyDataSetChanged();
            }
        };

        /* Start observing the LiveData data holder in the ViewModel and use the data in the onChanged method */
        movieViewModel.getMovieRepository().observe(this, movieObserver);

        setupRecyclerView();
    }

    /**
     * public boolean onCreateOptionsMenu(Menu menu)
     * Inflates the menu item.
     *
     * @param menu The menu item to inflate
     * @return boolean
     * True if menu was created. False otherwise.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_query_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * public void onMenuQuerySelected(MenuItem item)
     * Captures the type of query the user wants to use in the API request.
     *
     * @param item The MenuItem that the user selected. Indicates the type of movie the user wants to search for.
     */
    public void onMenuQuerySelected(MenuItem item) {
        int id = item.getItemId();
        String title = (String) item.getTitle();

        if (id == R.id.menu_most_popular_movie) {
            defaultPath = PATH_POPULAR;
        } else if (id == R.id.menu_highest_rated) {
            defaultPath = PATH_TOP_RATED;
        }

        /* Get an instance of the MovieRepository, which provides a MutableLiveData data holder */
        movieViewModel.init(defaultPath, API_KEY);
        Log.d(TAG, "Menu item " + title + " was clicked");
    }

    @Override
    public void onMovieSelected(String posterPath, String movieTitle, String movieReleaseDate, double voteAverage,
                                String moviePlot) {
        Intent intent = new Intent(this, MovieDetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(MovieDetailActivity.EXTRA_POSTER_PATH, posterPath);
        bundle.putString(MovieDetailActivity.EXTRA_MOVIE_TITLE, movieTitle);
        bundle.putString(MovieDetailActivity.EXTRA_MOVIE_RELEASE_DATE, movieReleaseDate);
        bundle.putDouble(MovieDetailActivity.EXTRA_MOVIE_VOTE_AVERAGE, voteAverage);
        bundle.putString(MovieDetailActivity.EXTRA_MOVIE_PLOT, moviePlot);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    /* Helper method to construct a RecyclerView */
    private void setupRecyclerView() {
        if (movieAdapter == null) {
            movieAdapter = new MovieAdapter(MovieRecyclerActivity.this, movieList);
            recyclerView.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
            recyclerView.setAdapter(movieAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
            recyclerView.setAdapter(movieAdapter);
        }
    }
}