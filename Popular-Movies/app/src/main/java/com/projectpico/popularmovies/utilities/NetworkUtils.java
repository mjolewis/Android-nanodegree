package com.projectpico.popularmovies.utilities;

import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

/**********************************************************************************************************************
 * A utility class to facilitate network communications.
 *
 * @author mlewis
 * @version March 16, 2020
 *********************************************************************************************************************/
public class NetworkUtils {
    // Invariant of the MovieData.java class
    //  1. The instance variable scheme is used to build our uri which is used in our network request.
    //  2. The instance variable hostUrl is used to build our uri which is used in our network request.
    //  3. THe instance variable path is used to build our uri which is used in our network request.
    //  4. The instance variable queryParam is used to build our uri which is used in our network request.
    //  5. The class variable TAG is used for debugging purposes.
    private static final String SCHEME = "https";
    private static final String AUTHORITY = "api.themoviedb.org";
    private static final String PATH = "3/movie";
    private static final String QUERY_KEY = "q";
    private String defaultQueryValue = "popular";
    private static final String API_KEY = "api_key";
    private static final String API_VALUE = "";
    private static final String TAG = "NetworkUtils";


    /**
     * public NetworkUtils(String scheme, String hostUrl, String path, String queryParam)
     *  Initializes a new NetworkUtils object.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this NetworkUtilsObject.
     */
    public NetworkUtils() { }

    /**
     * public URL UriBuilder(String movieSearchQuery)
     *  Builds a URI object and casts it into a URL.
     * @param movieSearchQuery
     *  The keyword that will be used to sort the data.
     * @return URL
     *  The URL to use to query the API.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this Uri.Builder.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new URL.
     */
    public URL UriBuilder(String movieSearchQuery) {
        Log.d(TAG, "Building url for network request.");
        if (!movieSearchQuery.equals(defaultQueryValue)) { defaultQueryValue = movieSearchQuery; }

        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEME)
                .authority(AUTHORITY)
                .path(PATH)
                .appendQueryParameter(QUERY_KEY, defaultQueryValue)
                .appendQueryParameter(API_KEY, API_VALUE)
                .build();

        URL url = null;
        try {
            url = new URL(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * public static String getResponseFromHttpUrl(URL url) throw IOException
     * Opens a url connection and attempts to return an input stream that reads from this open connection.
     * @param url
     *  The url we are attempting to connect to and to ready data from.
     * @return String
     *  The input stream.
     * @throws IOException
     *  Indicates an I/O error has occurred while creating the input stream.
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        Log.d(TAG, "Opening a url connection.");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream inputStream = urlConnection.getInputStream();

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
