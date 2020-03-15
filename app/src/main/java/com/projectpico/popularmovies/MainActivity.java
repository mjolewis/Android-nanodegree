package com.projectpico.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    // Invariant of the MainActivity.java class
    //  1. The instance variable recyclerView is a reference to the a RecyclerView object that acts as a container
    //     for displaying movie data.
    //  2. The instance variable adapter provides access to the items in your data set, creates views for items, and
    //     replaces the content of some of the views with new data items when the original item is no longer visible.
    //  3. The instance variable layoutManager positions item views inside a RecyclerVIew and determines when to reuse
    //     item views that are no longer visible to the user. To recycle a view, a layoutManager asks the adapter to
    //     replace the contents of the view with a different element from the data set.
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_movies);


    }

    public void setRecyclerView(RecyclerView recyclerView) {

    }

}
