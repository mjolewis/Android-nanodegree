package com.projectpico.popularmovies.utilities;

import android.util.Log;

import com.projectpico.popularmovies.MovieAdapter;

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

    public static ArrayList<String> parseJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        ArrayList<String> arrayList = new ArrayList<>();
        JSONObject results;
        String title = "";
        String release_date = "";
        String posterPath = "";
        String voteAverage = "";
        String plot = "";

        results = jsonObject.optJSONObject(RESULTS);
        Log.d(TAG, "Json result values are: " + String.valueOf(results));
        if (results != null) {
            Log.d(TAG, "has started parsing.");

            title = results.optString(TITLE);
            arrayList.add(results.optString(RELEASE_DATE));
            arrayList.add(results.optString(POSTER_PATH));
            arrayList.add(results.optString(VOTE_AVERAGE));
            arrayList.add(results.optString(PLOT));

            Log.d(TAG, title + " " + release_date + " " + voteAverage);
        }

        return arrayList;
    }
}
