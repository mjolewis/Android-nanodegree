package com.projectpico.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**********************************************************************************************************************
 * The MainActivity is the entry point for the Popular Movie application. It's sole purpose is to send an intent to our
 * MovieDisplay activity.
 *
 * @author mlewis
 * @version March 15, 2020
 *********************************************************************************************************************/
public class MainActivity extends AppCompatActivity {

    /**
     * protected void onCreate(Bundle savedInstanceState)
     *  Inflates the activity_main layout and sends an intent to our MovieDisplay activity
     * @param savedInstanceState
     *  A bundle that that persists both configuration changes and process death.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new Intent object.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Activate the MovieDisplay activity by sending an explicit intent
        Intent intent = new Intent(this, MovieDisplay.class);
        startActivity(intent);
    }
}
