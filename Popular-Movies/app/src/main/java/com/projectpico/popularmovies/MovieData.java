package com.projectpico.popularmovies;

/**********************************************************************************************************************
 * MovieData works with themoviedb.org API to fetch popular movies.
 *
 * @note
 *  1. themoviedb.org API requires to request an API key. In your request for a key, state that your usage will be for
 *     educational/non-commercial use. You will also need to provide some personal information to complete the request.
 *     Once you submit your request, you should receive your key via email shortly after. In order to request popular
 *     movies you will want to request data from the /movie/popular and /movie/top_rated endpoints. An API Key is
 *     required. Once you obtain your key, you append it to your HTTP request as a URL parameter like so:
 *     http://api.themoviedb.org/3/movie/popular?api_key=[YOUR_API_KEY]
 *
 * @author mlewis
 * @version March 15, 2020
 *********************************************************************************************************************/
public class MovieData {
    // Invariant of the MovieData.java class
    //  1. The instance variable movieId will reference a specific movie returned from our query to themoviedb.org API
    private String movieId;

    /**
     * public MovieData()
     *  Constructs a new MovieData object.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new MovieData object.
     */
    public MovieData() {
        movieId = null;
    }

    /**
     * public String getMovieId()
     *  Accessor method to retrieve the movieId
     */
    public String getMovieId() {
        return movieId;
    }
}
