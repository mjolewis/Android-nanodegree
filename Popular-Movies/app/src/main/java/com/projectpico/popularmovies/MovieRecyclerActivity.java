package com.projectpico.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MovieRecyclerActivity extends AppCompatActivity {
    // Invariant of the MainActivity.java class
    //  1. The instance variable recyclerView is a reference to the a RecyclerView object that acts as a container
    //     for displaying movie data.
    //  2. The instance variable adapter provides access to the items in your data set, creates views for items, and
    //     replaces the content of some of the views with new data items when the original item is no longer visible.
    //  3. The instance variable layoutManager positions item views inside a RecyclerVIew and determines when to reuse
    //     item views that are no longer visible to the user. To recycle a view, a layoutManager asks the adapter to
    //     replace the contents of the view with a different element from the data set.
    //  4. The class variable SPAN_COUNT indicates the number of columns created by the GridLayoutManager.
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final int SPAN_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);

        recyclerView = findViewById(R.id.rv_movies);
        setRecyclerView();
    }

    /**
     * public boolean onCreateOptionsMenu(Menu menu)
     *  Inflates the menu item.
     * @param menu
     *  The menu item to inflate
     * @return boolean
     *  True if menu was created. False otherwise.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_query_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * private void setRecyclerView()
     *  Sets up a recyclerView using the GridLayoutManager.
     * @postcondition
     *  A new GridLayoutManager has been initialized.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new GridLayoutManager.
     */
    private void setRecyclerView() {
        Context context = recyclerView.getContext();
        layoutManager = new GridLayoutManager(context, SPAN_COUNT);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void onMenuItemClick(MenuItem item) {
        // TODO: 3/16/20  
    }
}
