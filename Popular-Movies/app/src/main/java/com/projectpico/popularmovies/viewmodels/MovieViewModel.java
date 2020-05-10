package com.projectpico.popularmovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.projectpico.popularmovies.model.Movie.Movie;
import com.projectpico.popularmovies.model.Movies;
import com.projectpico.popularmovies.repo.MovieRepository;

/**********************************************************************************************************************
 * Helper class for the UI controller that is responsible for preparing data for the UI. The ViewModel objects are
 * automatically retained during configuration changes so that data they hold is immediately available to the next
 * activity.
 *
 * @author mlewis
 * @version May 3, 2020
 *********************************************************************************************************************/
public class MovieViewModel extends ViewModel {

    private MutableLiveData<Movie> movieMutableLiveData;
    private MutableLiveData<Movies> moviesMutableLiveData;
    private MovieRepository movieRepository;

    /**
     * public MovieViewModel(String path)
     *  Initialize a new MovieViewModel.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new MovieViewModel
     */
    public MovieViewModel() { }

    public void initMovies(MovieRepository.SortBy sortBy, int page) {
        movieRepository = MovieRepository.getInstance();
        movieMutableLiveData = movieRepository.getMovies(sortBy, page);
    }

    /**
     * public LiveData<MovieModel> getMovieRepository()
     *  Accessor method that returns a list of movies.
     * @return LiveData<MovieModel>
     *  A list of movies that can be observed by our UI controller.
     */
    public LiveData<Movies> getMovieRepository() {
        return moviesMutableLiveData;
    }
}
