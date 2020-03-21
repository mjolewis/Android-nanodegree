package com.projectpico.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
 */
public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_POSTER_PATH = "posterPath";
    public static final String EXTRA_MOVIE_TITLE = "title";
    public static final String EXTRA_MOVIE_RELEASE_DATE = "releaseDate";
    public static final String EXTRA_MOVIE_VOTE_AVERAGE = "voteAverage";
    public static final String EXTRA_MOVIE_PLOT = "plot";

    private static final String BASE_URL = "https://image.tmdb.org/t/p/w185";
    private String fullUrl;

    private String poster;
    private String movieTitle;
    private String releaseDate;
    private String voteAverage;
    private String plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Unpack the extras from the bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            poster = bundle.getString(EXTRA_POSTER_PATH);
            movieTitle = bundle.getString(EXTRA_MOVIE_TITLE);
            releaseDate = bundle.getString(EXTRA_MOVIE_RELEASE_DATE);
            voteAverage = bundle.getString(EXTRA_MOVIE_VOTE_AVERAGE);
            plot = bundle.getString(EXTRA_MOVIE_PLOT);

        }

        ImageView posterView = findViewById(R.id.iv_movie_poster);
        fullUrl = BASE_URL + poster;
        Picasso.get()
                .load(fullUrl)
                //.placeholder()
                //.error()
                .into(posterView);

        // Set the header and content for the title
        TextView titleTag = findViewById(R.id.tv_movie_title_tag);
        titleTag.setText(titleTag.getText());

        TextView titleView = findViewById(R.id.tv_movie_title);
        titleView.setText(movieTitle);

        // Set the header and content for the release date
        TextView releaseDateTag = findViewById(R.id.tv_movie_release_date_tag);
        releaseDateTag.setText(releaseDateTag.getText());

        TextView releaseDateView = findViewById(R.id.tv_movie_release_date);
        releaseDateView.setText(releaseDate);

        // Set the header and content for the movie rating
        TextView ratingTag = findViewById(R.id.tv_movie_vote_average_tag);
        ratingTag.setText(ratingTag.getText());

        TextView voteView = findViewById(R.id.tv_movie_vote_average);
        voteView.setText(voteAverage);

        // Set the header and content for the plot
        TextView plotTag = findViewById(R.id.tv_movie_plot_tag);
        plotTag.setText(plotTag.getText());

        TextView plotView = findViewById(R.id.tv_movie_plot);
        plotView.setText(plot);
    }
}
