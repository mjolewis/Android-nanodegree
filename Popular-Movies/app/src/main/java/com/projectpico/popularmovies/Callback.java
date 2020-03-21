package com.projectpico.popularmovies;

/**********************************************************************************************************************
 * Allows us to decouple the adapter from the intent to start a detail activity. This is a more modular approach
 * allowing for adapter reuse.
 *
 * @author mlewis
 * @version March 21, 2020
 *********************************************************************************************************************/
public interface Callback {
    void onMovieSelected(String posterPath, String movieTitle, String movieReleaseDate, String voteAverage,
                         String moviePlot);
}
