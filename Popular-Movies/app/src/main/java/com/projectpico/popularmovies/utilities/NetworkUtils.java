package com.projectpico.popularmovies.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**********************************************************************************************************************
 * A utility class to facilitate network communications.
 *
 * @note
 *  1. This class requires an API key. You will need to request an API key by creating an account
 *  @link {https://www.themoviedb.org/?_dc=1584461074}.
 *
 * @author mlewis
 * @version March 16, 2020
 *********************************************************************************************************************/
public class NetworkUtils {
    // Invariant of the MovieData.java class
    //  1. The class variable SCHEME is used to build our uri which is used in our network request.
    //  2. The class variable AUTHORITY is used to build our uri which is used in our network request.
    //  3. THe class variable PATH is used to build our uri which is used in our network request.
    //  5. The class variable TAG is used for debugging purposes.
    private static final String SCHEME = "https";
    private static final String AUTHORITY = "api.themoviedb.org";
    private static final String PATH_POPULAR = "3/movie/popular";
    private static final String PATH_TOP_RATED = "3/movie/top_rated";
    private static final String API_KEY = "api_key";
    private static final String API_VALUE = "f8c05a84150db926a13b793d60890bf4";
    private static final String TAG = NetworkUtils.class.getSimpleName();


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
    public static URL UriBuilder(String movieSearchQuery) {
        Log.d(TAG, "Building url for network request.");

        String path = PATH_POPULAR;
        if (!movieSearchQuery.equals(path)) { path = PATH_TOP_RATED; }

        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEME)
                .authority(AUTHORITY)
                .path(path)
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
     */
    public static String getResponseFromHttpUrl(URL url) {
        Log.d(TAG, "Opening a url connection.");
        HttpsURLConnection urlConnection = null;
        try {
            urlConnection = (HttpsURLConnection) url.openConnection();
            Log.d(TAG, "URL connection is: " + urlConnection);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (urlConnection != null) {
            try {
                InputStream inputStream = urlConnection.getInputStream();
                Log.d(TAG, "InputStream is: " + inputStream);

                Scanner scanner = new Scanner(inputStream);
                scanner.useDelimiter("\\A");

                if (scanner.hasNext()) {
                    return scanner.next();
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }
        }
        return "";
    }
}
