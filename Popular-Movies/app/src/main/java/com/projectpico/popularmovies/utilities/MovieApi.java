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

    @GET("3/movie/{path}")
    Call<Movie> getMovies(
            @Path("path") String path,
            @Query("api_key") String apiKey);
}
