package com.projectpico.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Movies implements Parcelable {
    private Integer page;
    private List<MovieResults> results;

    public Movies() {
        this.page = 0;
        this.results = new ArrayList<>();
    }

    protected Movies(Parcel in) {
        this.page = (Integer) in.readValue((Integer.class.getClassLoader()));
        this.results = in.createTypedArrayList(MovieResults.CREATOR);
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.page);
        dest.writeTypedList(this.results);
    }

    public Integer getPage() {
        return page;
    }

    public List<MovieResults> getResults() {
        return results;
    }

    public void appendMovies(Movies movies) {
        this.page = movies.getPage();
        this.results.addAll(movies.getResults());
    }
}
