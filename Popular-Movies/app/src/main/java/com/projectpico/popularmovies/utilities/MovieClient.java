package com.projectpico.popularmovies.utilities;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projectpico.popularmovies.Constants;
import com.projectpico.popularmovies.model.Movie;

import java.io.Serializable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**********************************************************************************************************************
 * Creates a type-safe HTTP client using Retrofit with a custom Gson parser instance.
 *
 * @author mlewis
 * @version May 3, 2020
 *********************************************************************************************************************/
public class MovieClient implements Serializable {
    // Invariant of the MovieResponse.java class
    // 1. The class variable DATE_FORMAT is used to map the date data directly from a String to a Date object.
    // 2. The class variable retrofit is a reference to a type-safe HTTP client.
    // 3. The class variable TAG is used for logging.
    private static final String DATE_FORMAT = "yyyy-MM-dd";
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
                    .baseUrl(Constants.TMDB_API_URL)
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
