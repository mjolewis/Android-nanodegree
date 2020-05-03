package com.projectpico.popularmovies.model;

import java.util.List;

/**********************************************************************************************************************
 * A POJO that stores the value of each JSON object from themoviedb.org service request.
 *
 * @author mlewis
 * @version May 2, 2020
 *********************************************************************************************************************/
public class Movie {
    // Invariant of the Movie.java class
    //  1. The instance variables are references to important metadata about each movie object.
    private int page;
    private int totalResults;
    private int totalPages;
    private List<Results> results = null;

    /**
     * public int getPage()
     *  Accessor method that returns the total number of pages.
     * @return int
     *  The specific page for the given list of movies.
     */
    public int getPage() { return page; }

    /**
     * public int getTotal_results()
     *  Accessor method that returns the total number of movie results.
     * @return int
     *  The total number of movies.
     */
    public int getTotalResults() { return totalResults; }

    /**
     * public int getTotal_pages()
     *  Accessor method that returns the total number of pages.
     * @return int
     *  The total number of pages required to hold all movies.
     */
    public int getTotalPages() { return totalPages; }

    /**
     * public List<Results> getResults()
     *  Accessor method that returns the nested JSON object under the movie's results element.
     * @return Results
     *  A nested JSON object containing movie specific metadata.
     */
    public List<Results> getResults() { return results; }

    /******************************************************************************************************************
     * A nested JSON object under the movies Result element. Contains movie specific metadata that will be used in our
     * Movie model.
     *
     * @author mlewis
     * @version May 2, 2020
     *****************************************************************************************************************/
    public class Results {
        // Invariant of the Movie.java class
        //  1. The instance variables are references each key in the nested JSON object that represents the Result
        //  element.
        private double popularity;
        private int voteCount;
        private boolean video;
        private String posterPath;
        private int id;
        private boolean adult;
        private String backdropPath;
        private String originalLanguage;
        private String originalTitle;
        private List<Integer> genreIds;
        private String title;
        private double voteAverage;
        private String overview;
        private String releaseDate;

        /**
         * public double getPopularity()
         *  Accessor method that returns the movies popularity rating.
         * @return int
         *  The popularity rating of the movie.
         */
        public double getPopularity() { return popularity; }

        /**
         * public int getVoteCount()
         *  Accessor method that returns the number of votes received by the movie.
         * @return int
         *  The number of votes for the movie.
         */
        public int getVoteCount() { return voteCount; }

        /**
         * public boolean isVideo()
         *  Accessor method that returns a boolean value representing whether or not this movie has a video.
         * @return boolean
         *  True if this movie has a video. False otherwise.
         */
        public boolean isVideo() { return video; }

        /**
         * public String getPosterPath()
         *  Accessor method that returns the path for the movie poster. Must be used with the base URL.
         * @return String
         *  The path indicating where to find the movie poster.
         */
        public String getPosterPath() { return posterPath; }

        /**
         * public int getId()
         *  Accessor method that returns the id of the movie.
         * @return int
         *  An integer identifier for the movie.
         */
        public int getId() { return id; }

        /**
         * public boolean isAdult()
         *  Accessor method that returns a boolean value to indicate whether or not the movie is rated as an adult
         *  film.
         * @return boolean
         *  True if the movie is rated as an adult film. Otherwise false.
         */
        public boolean isAdult() { return adult; }

        /**
         * public String getBackdropPath()
         *  Accessor method that returns the path to an image of the movie backdrop. Must be used with a base URL.
         * @return String
         *  The path indicating where to find the backdrop image for the movie.
         */
        public String getBackdropPath() { return backdropPath; }

        /**
         * public String getOriginalLanguage()
         *  Accessor method that returns original language of the film.
         * @return String
         *  The original language of the film.
         */
        public String getOriginalLanguage() { return originalLanguage; }

        /**
         * public String getOriginalTitle()
         *  Accessor method that returns the original title of the movie.
         * @return String
         *  The original title of the movie.
         */
        public String getOriginalTitle() { return originalTitle; }

        /**
         * public List<Integer> getGenreIds()
         *  Accessor method that returns a list of integer based genre identifiers. Used as a way to classify the
         *  movie.
         * @return List<Integer>
         *  A list of integer based genre identifiers.
         */
        public List<Integer> getGenreIds() { return genreIds; }

        /**
         * public String getTitle()
         *  Accessor method that returns the title of the movie.
         * @return String
         *  The title of the movie.
         */
        public String getTitle() { return title; }

        /**
         * public double getVoteAverage()
         *  Accessor method that returns the average rating for the movie.
         * @return int
         * The average rating of the movie.
         */
        public double getVoteAverage() { return voteAverage; }

        /**
         * public String getOverview()
         *  Accessor method that returns the movie plot.
         * @return String
         *  The movie plot.
         */
        public String getOverview() { return overview; }

        /**
         * public String getReleaseDate()
         *  Accessor method that returns the string representation of the movie release date.
         * @return String
         *  A string representation of the movie release date.
         */
        public String getReleaseDate() { return releaseDate; }
    }

}
