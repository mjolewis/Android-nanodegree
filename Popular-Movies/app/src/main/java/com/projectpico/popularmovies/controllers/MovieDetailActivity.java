package com.projectpico.popularmovies.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectpico.popularmovies.R;
import com.squareup.picasso.Picasso;

/**********************************************************************************************************************
 * The MovieDetailActivity is responsible for displaying content for an individual movie. The content displayed
 * includes the movie poster, title, release date, rating, and plot summary.
 *
 * @note
 *  1. The MovieDetailActivity is activated by an explicit intent from the MovieRecyclerActivity. Each movie poster
 *     in the RecyclerActivity has an onClickListener that activates the detail activity when a user clicks the poster.
 *
 * @author mlewis
 * @version March 21, 2020
 *********************************************************************************************************************/
public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_POSTER_PATH = "posterPath";
    public static final String EXTRA_MOVIE_TITLE = "title";
    public static final String EXTRA_MOVIE_RELEASE_DATE = "releaseDate";
    public static final String EXTRA_MOVIE_VOTE_AVERAGE = "voteAverage";
    public static final String EXTRA_MOVIE_PLOT = "plot";
    private static final String MAX_RATING = "/10";


    private static final String BASE_URL = "https://image.tmdb.org/t/p/w185";

    private String poster;
    private String movieTitle;
    private String releaseDate;
    private double voteAverage;
    private String plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

//        Toolbar toolbar = findViewById(R.id.detail_toolbar);
//        setSupportActionBar(toolbar);

        // Show the up button.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment
            // TODO: 5/9/20
            Bundle bundle = new Bundle();
            bundle.putParcelable(MovieDetailFragment.EXTRA_MOVIE_KEY,
                    getIntent().getParcelableExtra(MovieDetailFragment.EXTRA_MOVIE_KEY));

            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            movieDetailFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movieDetailContainer, movieDetailFragment)
                    .commit();
        }

//        // Unpack the extras from the bundle
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            poster = bundle.getString(EXTRA_POSTER_PATH);
//            movieTitle = bundle.getString(EXTRA_MOVIE_TITLE);
//            releaseDate = bundle.getString(EXTRA_MOVIE_RELEASE_DATE);
//            voteAverage = bundle.getDouble(EXTRA_MOVIE_VOTE_AVERAGE);
//            plot = bundle.getString(EXTRA_MOVIE_PLOT);
//        }
//
//        /* Use Picasso to get the movie poster and place it into the UI */
//        ImageView posterView = findViewById(R.id.iv_movie_poster);
//        String fullUrl = BASE_URL + poster;
//        Picasso.get()
//                .load(fullUrl)
//                //.placeholder()
//                //.error()
//                .into(posterView);
//
//        /* Place the movie title into the UI */
//        ImageView titleView = findViewById(R.id.iv_movie_backdrop);
//        Picasso.get()
//                .load(fullUrl)
//                .into(titleView);
////        titleView.setText(movieTitle);
//
//        /* Add the release date (in YYYY format) into the UI */
//        String year = releaseDate.substring(0, 4);
//        TextView releaseDateView = findViewById(R.id.tv_movie_release_date);
//        releaseDateView.setText(year);
//
//        /* Add the the movie rating into the UI */
//        TextView voteView = findViewById(R.id.tv_movie_vote_average);
//        String score = voteAverage + MAX_RATING;
//        voteView.setText(score);
//
//        /* Add the plot into the UI */
//        TextView plotView = findViewById(R.id.tv_movie_plot);
//        plotView.setText(plot);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
