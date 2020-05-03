package com.projectpico.popularmovies.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.projectpico.popularmovies.utilities.MovieApi;
import com.projectpico.popularmovies.utilities.MovieClient;

import java.util.List;

/**
 * Helper class for the UI controller that is responsible for preparing data for the UI. The ViewModel objects are
 * automatically retained during configuration changes so that data they hold is immediately available to the next
 * activity.
 *
 * @author mlewis
 * @version May 2, 2020
 */
public class MovieViewModel extends ViewModel {

    private MovieApi movieClient;

    /**
     * public MovieViewModel(String path)
     *  Initialize a new MovieViewModel.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new MovieViewModel
     */
    public MovieViewModel() {
        movieClient = MovieClient.getInstance();
//        movieResponse.setPath(path);
    }

    /**
     * public LiveData<List> getMovieList()
     *  Accessor method that returns a list of movies.
     * @return LiveData<List>
     *  A list of movies that can be observed by our UI controller.
     */
    public LiveData<List> getMovieList(String path) {
        return movieClient.getMovies(path, key);
    }
}
