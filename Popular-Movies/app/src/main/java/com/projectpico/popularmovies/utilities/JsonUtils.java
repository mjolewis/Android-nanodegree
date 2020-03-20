package com.projectpico.popularmovies.utilities;

import com.projectpico.popularmovies.Movie;
import com.projectpico.popularmovies.MovieRecyclerActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**********************************************************************************************************************
 * A utility class to parse JSON objects.
 *
 * @author mlewis
 * @version March 16, 2020
 *********************************************************************************************************************/
public class JsonUtils {
    // Invariant of the JsonUtils.java class.
    //  1. The class variables are used as keys for JSON parsing.
    //  2. The class variable TAG is used for debugging purposes.
    private static final String RESULTS = "results";
    private static final String TITLE = "title";
    private static final String RELEASE_DATE = "release_date";
    private static final String POSTER_PATH = "poster_path";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String PLOT = "overview";
    private static final String TAG = "JsonUtils";

    /**
     * public static Movie parseMovieData(String jsonString) throws JSONException
     * Parses JSON retrieved from our API call to tbmovie.org.
     * @param jsonString
     *  The results from our API call to tbmovie.org. The structure of this string is a JSON object with the movie data
     *  contained in a child array called "results".
     * @return Movie
     *  A new Move object.
     * @throws JSONException
     *  Indicates attempts to parse or construct malformed documents, use of null as a name, out of range lookups, or
     *  type mismatches on lookups.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new Movie object.
     */
    public static ArrayList<Movie> parseMovieData(String jsonString) throws JSONException {
        ArrayList<Movie> movieData = new ArrayList<>();

        // Get the JSON object representing the entire API query result.
        JSONObject apiResults = new JSONObject(jsonString);

        /* The movie results are in a child array called "results". This array contains multiple JSON "movieObjects"
         * that we iterate through while searching for the target key/value pairs.
         */
        JSONArray resultsArray = apiResults.optJSONArray(RESULTS);
        if (resultsArray != null) {
            JSONObject movieObjects;
            for (int i = 0; i < resultsArray.length(); i++) {
                movieObjects = resultsArray.optJSONObject(i);
                if (movieObjects != null) {
                    Movie movie = new Movie(movieObjects);
                    movieData.add(movie);
                }
            }
        }

        return movieData;
    }
}
