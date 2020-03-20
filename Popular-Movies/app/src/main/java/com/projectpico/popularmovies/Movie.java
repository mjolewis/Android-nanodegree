package com.projectpico.popularmovies;

import org.json.JSONObject;

import java.util.List;

/**********************************************************************************************************************
 * Builds a movie object.
 *
 * @author mlewis
 * @version March 18, 2020
 *********************************************************************************************************************/
public class Movie {
    // Invariant of the Movie.java class
    //  1. The instance variables describe metadata about each movie object.
    private String title;
    private String releaseDate;
    private String posterPath;
    private String voteAverage;
    private String plot;

    /**
     * public Movie()
     * Initializes an empty movie object.
     * No args constructor for serialization.
     */
    public Movie() {}

    /**
     * public Movie(String title, String releaseDate, String posterPath, String voteAverage, String plot)
     * Initializes a new movie with the specified parameters.
     * @param movieData
     *  An array of movie titles for each movie in our data set.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this Movie.
     */
    public Movie(JSONObject movieData) {
        this.title = movieData.optString("title");
        this.releaseDate = movieData.optString("release_date");
        this.posterPath = movieData.optString("poster_path");
        this.voteAverage = movieData.optString("vote_average");
        this.plot = movieData.optString("overview");
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
