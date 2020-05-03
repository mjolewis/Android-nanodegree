package com.projectpico.popularmovies.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.projectpico.popularmovies.model.Movie;
import com.projectpico.popularmovies.model.Reviews;
import com.projectpico.popularmovies.model.Trailers;
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
     * public MutuableLiveData<MovieModel> getPopularMovies(String key)
     *  Processes an asynchronous network request using a callback.
     * @param key
     *  An API key used by the HTTP client.
     * @return MutableLiveData<MovieModel>
     *  A list of movies.
     */
    public MutableLiveData<Movie> getPopularMovies(String key) {
        final MutableLiveData<Movie> popularMovies = new MutableLiveData<>();

        /* Processes the network request on a background thread. */
        movieApi.getPopularMovies(key)
                .enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "Error code: " + response.code());
                } else {
                    popularMovies.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d(TAG, "Response = " + t.toString());
                popularMovies.setValue(null);
            }
        });
        return popularMovies;
    }

    /**
     * public MutuableLiveData<MovieModel> getTopRatedMovies(String key)
     *  Processes an asynchronous network request using a callback.
     * @param key
     *  An API key used by the HTTP client.
     * @return MutableLiveData<MovieModel>
     *  A list of movies.
     */
    public MutableLiveData<Movie> getTopRatedMovies(String key) {
        final MutableLiveData<Movie> topRatedMovies = new MutableLiveData<>();

        /* Processes the network request on a background thread. */
        movieApi.getTopRatedMovies(key)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (!response.isSuccessful()) {
                            Log.d(TAG, "Error code: " + response.code());
                        } else {
                            topRatedMovies.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Log.d(TAG, "Response = " + t.toString());
                        topRatedMovies.setValue(null);
                    }
                });
        return topRatedMovies;
    }

    /**
     * public MutuableLiveData<MovieModel> getMovieReviews(int movieId, String key)
     *  Processes an asynchronous network request using a callback.
     * @param movieId
     *  An endpoint used by the HTTP client.
     * @param key
     *  An API key used by the HTTP client.
     * @return MutableLiveData<MovieModel>
     *  A list of movies.
     */
    public MutableLiveData<Reviews> getMovieReviews(int movieId, String key) {
        final MutableLiveData<Reviews> movieReviews = new MutableLiveData<>();

        /* Processes the network request on a background thread. */
        movieApi.getMovieReviews(movieId, key)
                .enqueue(new Callback<Reviews>() {
                    @Override
                    public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                        if (!response.isSuccessful()) {
                            Log.d(TAG, "Error code: " + response.code());
                        } else {
                            movieReviews.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Reviews> call, Throwable t) {
                        Log.d(TAG, "Response = " + t.toString());
                        movieReviews.setValue(null);
                    }
                });
        return movieReviews;
    }

    /**
     * public MutuableLiveData<MovieModel> getMovieTrailers(int movieId, String key)
     *  Processes an asynchronous network request using a callback.
     * @param movieId
     *  An endpoint used by the HTTP client.
     * @param key
     *  An API key used by the HTTP client.
     * @return MutableLiveData<MovieModel>
     *  A list of movies.
     */
    public MutableLiveData<Trailers> getMovieTrailers(int movieId, String key) {
        final MutableLiveData<Trailers> movieTrailers = new MutableLiveData<>();

        movieApi.getMovieTrailers(movieId, key)
                .enqueue(new Callback<Trailers>() {
                    @Override
                    public void onResponse(Call<Trailers> call, Response<Trailers> response) {
                        if (!response.isSuccessful()) {
                            Log.d(TAG, "Error code: " + response.code());
                        } else {
                            movieTrailers.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Trailers> call, Throwable t) {
                        Log.d(TAG, "Response = " + t.toString());
                        movieTrailers.setValue(null);
                    }
                });
        return movieTrailers;
    }
}
