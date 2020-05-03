package com.projectpico.popularmovies.utilities;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.projectpico.popularmovies.model.MovieModel;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**********************************************************************************************************************
 * A POJO used to map the movie keys to a list of Movie objects.
 *
 * @author mlewis
 * @version May 2, 2020
 *********************************************************************************************************************/
public class MovieClient {
    // Invariant of the MovieResponse.java class
    // 1. The instance variable movies is a list of movies that the API responds with.
    // 2. The class variable DATE_FORMAT is used to map the date data directly from a String to a Date object.
    // 3. The class variable TAG is used for logging.
    @SerializedName("results")
    private MovieModel movies;
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static Retrofit retrofit;
    private static final String TAG = MovieClient.class.getSimpleName();

    /*
     * private MovieResponse()
     *  Initializes a new list of movies.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new list of movies.
     */
    private MovieClient() { }


    public static MovieApi getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder()))
                    .build();
        }
        return retrofit.create(MovieApi.class);
    }

    /*
    * private static Gson gsonBuilder()
    *  Helper method used to create a custom Gson parser instance. This parser is used to convert a JSON string into
    *  a POJO.
    * @return Gson
    *  The custom Gson parser.
     */
    private static Gson gsonBuilder() {
        /* A custom Gson parser instance used to convert the JSON string into a POJO */
        return new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }
}
