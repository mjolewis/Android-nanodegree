package com.projectpico.popularmovies.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.projectpico.popularmovies.model.Movie;
import com.projectpico.popularmovies.utilities.MovieApi;
import com.projectpico.popularmovies.utilities.MovieClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**********************************************************************************************************************
 * A repository class that provides a singleton network request using retrofit and a LiveData object for observing the
 * API response.
 *
 * @author mlewis
 * @version May 3, 2020
 *********************************************************************************************************************/
public class MovieRepository {
    // Invariant of the MovieRepository.java class
    // 1.
    private static MovieRepository movieRepository;
    private MovieApi movieApi;
    private static final String TAG = MovieRepository.class.getSimpleName();

    /**
     * public static MovieRepository getInstance()
     *  Initializes a new MovieRepository if one doesn't exist by following the Singleton pattern.
     * @return MovieRepository
     *  A single instance of the MovieRepository
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new MovieRepository.
     */
    public static MovieRepository getInstance() {
        if (movieRepository == null) {
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    /* Helper method that gets an instance of the move client, which is an HTTP client provided by Retrofit. */
    private MovieRepository() { movieApi = MovieClient.getInstance(); }

    /**
     * public MutuableLiveData<MovieModel> getMovies(String path, String key)
     *  Processes an asynchronous network request using a callback.
     * @param path
     *  An endpoint used by the HTTP client.
     * @param key
     *  An API key used by the HTTP client.
     * @return MutableLiveData<MovieModel>
     *  A list of movies.
     */
    public MutableLiveData<Movie> getMovies(String path, String key) {
        final MutableLiveData<Movie> movies = new MutableLiveData<>();

        /* Processes the network request on a background thread. */
        movieApi.getMovies(path, key)
                .enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "Error code: " + response.code());
                } else {
                    movies.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d(TAG, "Response = " + t.toString());
                movies.setValue(null);
            }
        });
        return movies;
    }
}
