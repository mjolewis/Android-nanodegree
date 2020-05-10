package com.projectpico.popularmovies.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.projectpico.popularmovies.model.MovieResults;
import com.projectpico.popularmovies.model.Movies;
import com.projectpico.popularmovies.repo.MovieRepository;
import com.projectpico.popularmovies.utilities.Callback;
import com.projectpico.popularmovies.R;
import com.projectpico.popularmovies.adapters.MovieAdapter;
import com.projectpico.popularmovies.utilities.NetworkConnection;
import com.projectpico.popularmovies.viewmodels.MovieViewModel;

import java.util.List;

/**********************************************************************************************************************
 * The top level UI controller.
 *
 * @author mlewis
 * @version May 3, 2020
 *********************************************************************************************************************/
public class MainActivity extends AppCompatActivity implements Callback {
    // Invariant of the MainActivity.java class
    //  1. The instance variable movieAdapter is a reference to a custom adapter used by the recycler view.
    //  2. The instance variable movieViewModel is a reference to the ViewModel that stores and manages the UI
    //     controller data in a lifecycle aware manner.
    //  3. The instance variable recyclerView is a reference to the a RecyclerView object that acts as a container
    //     for displaying movie data.
    //  4. The instance variable movieList is a reference to the MutableLiveData object which holds the list of movies
    //     returned from the RESTful client.
    //  8. The class variable API_KEY must be provided by the developer. It allows us to access themoviedb.org API.
    //  9. The class variable FAVORITES is a reference to the users favorite movies.
    //  10. The class variable SPAN_COUNT indicates the number of columns created by the GridLayoutManager.
    //  11. The class variable TAG is used for debugging purposes.
    private static MovieAdapter movieAdapter;
    private MovieViewModel movieViewModel;
    private static RecyclerView recyclerView;
    private static final int SPAN_COUNT = 2;
    private MovieRepository.SortBy sortBy = MovieRepository.SortBy.MostPopular;
    private Movies movies = new Movies();
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        toolbar.setTitle(getTitle());

        recyclerView = findViewById(R.id.rv_movies);
        setupRecyclerView();

    }

    private void getMovies(final int page) {
        if (sortBy != MovieRepository.SortBy.Favorite) {
            if (isNetworkAvailable()) {

                /* Get a ViewModel and scope it to this activity's lifecycle */
                movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

                /* Create an instance of the MovieRepository, which provides a MutableLiveData data holder */
                movieViewModel.initMovies(sortBy, page);

                /* Create the observer to update the UI */
                Observer<Movies> movieObserver = new Observer<Movies>() {
                    @Override
                    public void onChanged(Movies movies) {
                        List<MovieResults> movieResults = movies.getResults();
//                        movieList.addAll(movieResults);
//                        movieAdapter.notifyDataSetChanged();
                        if (movieResults != null) {
                            if (page == 1) {
                                setRecy
                            }
                        }
                    }
                };

                /* Start observing the LiveData data holder in the ViewModel and use the data in the onChanged method */
                movieViewModel.getMovieRepository().observe(this, movieObserver);
            }
        }
    }

    private boolean isNetworkAvailable() {
        return NetworkConnection.isNetworkAvailable(getApplicationContext());
    }

    private void loadMovies() {
        getMovies(1);
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
        menuInflater.inflate(R.menu.activity_main_menu, menu);

        if (sortBy == MovieRepository.SortBy.TopRated) {
            menu.findItem(R.id.menu_sort_by_top_rated).setChecked(true);
        } else if (sortBy == MovieRepository.SortBy.Favorite) {
            menu.findItem(R.id.menu_sort_by_favorites).setChecked(true);
        }
        return true;
    }

    // TODO: 5/9/20
    /**
     * public void onOptionItemSelected(MenuItem item)
     * The option menu provides a dropdown of movie types for the user to sort by. The movie types are "Popular",
     * "Top Rated", and "Favorites".
     *
     * @param item The MenuItem that the user selected. Indicates the type of movie the user wants to sort by.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_sort_by_popularity:
                if (sortBy != MovieRepository.SortBy.MostPopular) {
                    if (isNetworkAvailable()) {
                        sortBy = MovieRepository.SortBy.MostPopular;
                        loadMovies();
                        item.setChecked(true);
                        //saveSortSelected;
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_SHORT)
                                .show();
                    }
                }
                break;
            case R.id.menu_sort_by_top_rated:
                if (sortBy != MovieRepository.SortBy.TopRated) {
                    if (isNetworkAvailable()) {
                        sortBy = MovieRepository.SortBy.TopRated;
                        loadMovies();
                        item.setChecked(true);
                        //saveSortSelected;
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_SHORT)
                                .show();
                    }
                }
                break;
            case R.id.menu_sort_by_favorites:
                if (sortBy != MovieRepository.SortBy.Favorite) {
                    /* Don't check for internet connectivity here, because we can always show favorites. The favorites
                    *  are stored in a on device by Room persistence.
                     */
                    sortBy = MovieRepository.SortBy.Favorite;
                    loadMovies();
                    item.setChecked(true);
                    //saveSelected;
                }
        }
        setActivityTitleBySortMenuItem();
        return super.onOptionsItemSelected(item);
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

    private void setActivityTitleBySortMenuItem() {
        switch (sortBy) {
            case MostPopular:
                setTitle(getString(R.string.most_popular) + " " + getString(R.string.movies));
                break;
            case TopRated:
                setTitle(getString(R.string.top_rated) + " " + getString(R.string.movies));
                break;
            case Favorite:
                setTitle(getString(R.string.favorites) + " " + getString(R.string.movies));
                break;
        }
    }

    /* Helper method to construct a RecyclerView */
    private void setupRecyclerView() {
            movieAdapter = new MovieAdapter(MainActivity.this, movieList);
            recyclerView.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
            recyclerView.setAdapter(movieAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
    }
}