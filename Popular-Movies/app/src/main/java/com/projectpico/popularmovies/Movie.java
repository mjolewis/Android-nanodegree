package com.projectpico.popularmovies;

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
    private List<String> title;
    private List<String> releaseDate;
    private List<String> posterPath;
    private List<String> voteAverage;
    private List<String> plot;

    /**
     * public Movie()
     * Initializes an empty movie object.
     * No args constructor for serialization.
     */
    public Movie() {}

    /**
     * public Movie(String title, String releaseDate, String posterPath, String voteAverage, String plot)
     * Initializes a new movie with the specified parameters.
     * @param title
     *  An array of movie titles for each movie in our data set.
     * @param releaseDate
     *  An array of release dates for each movie in our data set.
     * @param posterPath
     *  An array of relative path names for each movie in our data set.
     * @param voteAverage
     *  An array of movie ratings for each movie in our data set.
     * @param plot
     *  An array of plots for each movie in our data set.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this Movie.
     */
    public Movie(List<String> title, List<String>  releaseDate, List<String>  posterPath, List<String>  voteAverage,
                 List<String>  plot) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.plot = plot;
    }

    public List<String>  getTitle() {
        return title;
    }

    public void setTitle(List<String>  title) {
        this.title = title;
    }

    public List<String>  getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(List<String>  releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String>  getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(List<String>  posterPath) {
        this.posterPath = posterPath;
    }

    public List<String>  getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(List<String>  voteAverage) {
        this.voteAverage = voteAverage;
    }

    public List<String>  getPlot() {
        return plot;
    }

    public void setPlot(List<String>  plot) {
        this.plot = plot;
    }
}
