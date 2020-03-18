package com.projectpico.popularmovies.utilities;

import android.util.Log;

import com.projectpico.popularmovies.Movie;
import com.projectpico.popularmovies.MovieAdapter;

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

    public static Movie parseMovieData(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject results;
        List<String> titles = new ArrayList<>();
        List<String> releaseDates = new ArrayList<>();
        List<String> posterPaths = new ArrayList<>();
        List<String> voteAverages = new ArrayList<>();
        List<String> plots = new ArrayList<>();

        /*
        * The variables below are all string elements in JSON. We retrieve each of these directly using opString()
         */
        results = jsonObject.optJSONObject(RESULTS);
        if (results != null) {
            for (int i = 0; i < results.length(); i++) {
                titles.add(results.optString(TITLE));
                releaseDates.add(results.optString(RELEASE_DATE));
                posterPaths.add(results.optString(POSTER_PATH));
                voteAverages.add(results.optString(VOTE_AVERAGE));
                plots.add(results.optString(PLOT));
            }
        }

        return new Movie(titles,releaseDates, posterPaths, voteAverages, plots);
    }
}
