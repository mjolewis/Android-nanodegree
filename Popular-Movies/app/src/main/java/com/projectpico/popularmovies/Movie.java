package com.projectpico.popularmovies;

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
    public Movie(String title, String releaseDate, String posterPath, String voteAverage, String plot) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.plot = plot;
    }

    public String getTitle() {
        return title;
    }

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
