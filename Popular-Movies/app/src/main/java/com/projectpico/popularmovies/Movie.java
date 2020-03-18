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
    private List<String> titles;
    private List<String> releaseDates;
    private List<String> posterPaths;
    private List<String> voteAverages;
    private List<String> plots;

    /**
     * public Movie()
     * Initializes an empty movie object.
     * No args constructor for serialization.
     */
    public Movie() {}

    /**
     * public Movie(String title, String releaseDate, String posterPath, String voteAverage, String plot)
     * Initializes a new movie with the specified parameters.
     * @param titles
     *  An array of movie titles for each movie in our data set.
     * @param releaseDates
     *  An array of release dates for each movie in our data set.
     * @param posterPaths
     *  An array of relative path names for each movie in our data set.
     * @param voteAverages
     *  An array of movie ratings for each movie in our data set.
     * @param plots
     *  An array of plots for each movie in our data set.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this Movie.
     */
    public Movie(List<String> titles, List<String>  releaseDates, List<String> posterPaths, List<String>  voteAverages,
                 List<String>  plots) {
        this.titles = titles;
        this.releaseDates = releaseDates;
        this.posterPaths = posterPaths;
        this.voteAverages = voteAverages;
        this.plots = plots;
    }

    public List<String>  getTitle() { return titles; }

    public void setTitle(List<String>  title) {
        this.titles = title;
    }

    public List<String>  getReleaseDate() {
        return releaseDates;
    }

    public void setReleaseDate(List<String>  releaseDate) {
        this.releaseDates = releaseDate;
    }

    public List<String>  getPosterPath() {
        return posterPaths;
    }

    public void setPosterPath(List<String>  posterPath) {
        this.posterPaths = posterPath;
    }

    public List<String>  getVoteAverage() {
        return voteAverages;
    }

    public void setVoteAverage(List<String>  voteAverage) {
        this.voteAverages = voteAverage;
    }

    public List<String>  getPlot() {
        return plots;
    }

    public void setPlot(List<String>  plot) {
        this.plots = plot;
    }
}
