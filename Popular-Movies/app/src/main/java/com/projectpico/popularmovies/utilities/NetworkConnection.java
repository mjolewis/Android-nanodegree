package com.projectpico.popularmovies.utilities;

import android.content.Context;
import android.net.ConnectivityManager;

/**********************************************************************************************************************
 * A utility class to determine if the device has a network connection.
 *
 * @author mlewis
 * @version May 9, 2020
 *********************************************************************************************************************/
public class NetworkConnection {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetwork() != null;
    }
}
