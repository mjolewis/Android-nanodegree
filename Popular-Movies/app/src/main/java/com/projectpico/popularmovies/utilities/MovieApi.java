package com.projectpico.popularmovies.utilities;

import com.projectpico.popularmovies.model.Movie;
import com.projectpico.popularmovies.model.Reviews;
import com.projectpico.popularmovies.model.Trailers;

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

    @GET("3/movie/popular")
    Call<Movie> getPopularMovies(@Query("api_key") String apiKey);

    @GET("3/movie/top_rated")
    Call<Movie> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("3/movie/{movieId}/reviews")
    Call<Movie> getMovie(@Path("movieId") int movieId, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/videos")
    Call<Trailers> getMovieTrailers(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/videos")
    Call<Reviews> getMovieReviews(@Path("id") int id, @Query("api_key") String apiKey);
}
