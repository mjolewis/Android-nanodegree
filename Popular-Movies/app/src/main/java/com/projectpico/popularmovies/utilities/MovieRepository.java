package com.projectpico.popularmovies.utilities;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.projectpico.popularmovies.model.MovieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    // Invariant of the MovieRepository.java class
    // 1.
    private static MovieRepository movieRepository;
    private MovieApi movieApi;
    private static final String API_KEY = "f8c05a84150db926a13b793d60890bf4";
    private static final String TAG = MovieRepository.class.getSimpleName();

    public static MovieRepository getInstance() {
        if (movieRepository == null) {
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    public MovieRepository() {
        movieApi = MovieClient.getInstance();
    }

    /**
     * public MutuableLiveData<MovieModel> getMovies()
     *  Processes an asynchronous network request using a callback.
     * @return MutableLiveData<MovieModel>
     *  A list of movies.
     */
    public MutableLiveData<MovieModel> getMovies(String path, String key) {
        final MutableLiveData<MovieModel> movies = new MutableLiveData<>();

        /* Processes the network request on a background thread. */
        movieApi.getMovies(path, key)
                .enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "Error code: " + response.code());
                } else {
                    movies.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Log.d(TAG, "Response = " + t.toString());
                movies.setValue(null);
            }
        });
        return movies;
    }
}
