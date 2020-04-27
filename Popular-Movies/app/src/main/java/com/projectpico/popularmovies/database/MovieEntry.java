package com.projectpico.popularmovies.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**********************************************************************************************************************
 * A MovieEntry is an entity that represents a table within a SQLite database. The entity will define the table
 * structure and public getters and setters for the DAO to use when interacting with the database.
 *
 * @author mlewis
 * @version April 26, 2020
 *********************************************************************************************************************/
@Entity(tableName = "favorites")
public class MovieEntry {

    // Invariant of the MovieEntry.java class
    // 1. The instance variables define the columns used by the entity to store information about the users favorite
    //    movies.
    @PrimaryKey
    private String title;
    private String posterPath;
    private String releaseDate;
    private String plot;
    private String rating;

    /**
     * public MovieEntry(String title, String posterPath, String releaseDate, String plot, String rating)
     *  Initializes a new MovieEntry entity.
     * @param title
     *  The movie title.
     * @param posterPath
     *  A path to the movie poster.
     * @param releaseDate
     *  The movie's release date.
     * @param plot
     *  The movie plot.
     * @param rating
     *  The movie rating.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new MovieEntry entity.
     */
    public MovieEntry(String title, String posterPath, String releaseDate, String plot, String rating) {
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.plot = plot;
        this.rating = rating;
    }

    /**
     * public String getTitle()
     *  An accessor method that returns the movies title.
     * @return title
     *  The title of the movie.
     */
    public String getTitle() {
        return title;
    }

    /**
     * public void setTitle(String title)
     *  A mutator method to add a title to the database.
     * @param title
     *  The title of the movie being added to the database.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * public String getPosterPath()
     *  An accessor method that returns the poser path.
     * @return posterPath
     *  The poster path for the movie.
     */
    public String getPosterPath() {
        return posterPath;
    }

    /**
     * public void setPosterPath(String posterPath)
     *  A mutator method that adds a poster path to the database.
     * @param posterPath
     *  The posterpath to add to the database.
     */
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
