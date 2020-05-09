package com.projectpico.popularmovies.utilities;

import com.projectpico.popularmovies.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**********************************************************************************************************************
 * An interface for our themoviedb.org API request.
 *
 * @author mlewis
 * @version May 2, 2020
 *********************************************************************************************************************/
public interface MovieApi {

    @GET("movie/popular")
    Call<Movie> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/top_rated")
    Call<Movie> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);

    /* The API methods support a query parameter called append_to_response. This makes it possible to make sub requests
    *  within the same namespace in a single HTTP request. Each request will get appended to the response as a new JSON
    *   object. This style of request only count as one request against the rate limits so you can speed up the user
    *   experience.
     */
    @GET("movie/{movieId}?append_to_response=videos, reviews")
    Call<Movie> getMovie(@Path("movieId") int movieId, @Query("api_key") String apiKey);
}