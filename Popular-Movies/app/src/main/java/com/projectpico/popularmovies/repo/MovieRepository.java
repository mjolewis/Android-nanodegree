package com.projectpico.popularmovies.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.projectpico.popularmovies.Constants;
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
    final MutableLiveData<Movie> movieMutableLiveData = new MutableLiveData<>();
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

    public MutableLiveData<Movie> getMovie(int movieId) {

        movieApi.getMovie(movieId, Constants.TMDB_API_KEY)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (!response.isSuccessful()) {
                            Log.d(TAG, "Error code: " + response.code());
                        } else {
                            movieMutableLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Log.d(TAG, "Response = " + t.toString());
                        movieMutableLiveData.setValue(null);
                    }
                });
        return movieMutableLiveData;
    }

    public MutableLiveData<Movie> getMovies(SortBy sortBy, int page) {
        switch (sortBy) {
            case MostPopular:
                getPopularMovies(page);
                break;
            case TopRated:
                getTopRatedMovies(page);
                break;
        }
        return movieMutableLiveData;
    }

    /**
     * public MutuableLiveData<MovieModel> getPopularMovies(String key)
     *  Processes an asynchronous network request using a callback.
     * @param page
     *  An API key used by the HTTP client.
     * @return MutableLiveData<MovieModel>
     *  A list of movies.
     */
    public MutableLiveData<Movie> getPopularMovies(int page) {

        /* Processes the network request on a background thread. */
        movieApi.getPopularMovies(Constants.TMDB_API_KEY, page)
                .enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "Error code: " + response.code());
                } else {
                    movieMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d(TAG, "Response = " + t.toString());
                movieMutableLiveData.setValue(null);
            }
        });
        return movieMutableLiveData;
    }

    /**
     * public MutuableLiveData<MovieModel> getTopRatedMovies(String key)
     *  Processes an asynchronous network request using a callback.
     * @param page
     *  An API key used by the HTTP client.
     * @return MutableLiveData<MovieModel>
     *  A list of movies.
     */
    public MutableLiveData<Movie> getTopRatedMovies(int page) {

        /* Processes the network request on a background thread. */
        movieApi.getTopRatedMovies(Constants.TMDB_API_KEY, page)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (!response.isSuccessful()) {
                            Log.d(TAG, "Error code: " + response.code());
                        } else {
                            movieMutableLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Log.d(TAG, "Response = " + t.toString());
                        movieMutableLiveData.setValue(null);
                    }
                });
        return movieMutableLiveData;
    }

    /**
     * public enum SortBy
     *  A set of fixed constants used by our UI controller to determine what movies to display.
     */
    public enum SortBy {
        MostPopular(0),
        TopRated(1),
        Favorite(2);

        private int value;

        SortBy(int value) {
            this.value = value;
        }

        /**
         * public int id
         *  Accessor method that returns the enum index
         * @return int
         *  The enum index.
         */
        public int id() {
            return value;
        }

        public static SortBy fromId(int value) {
            for (SortBy color : values()) {
                if (color.value == value) {
                    return color;
                }
            }
            return MostPopular;
        }
    }
}
