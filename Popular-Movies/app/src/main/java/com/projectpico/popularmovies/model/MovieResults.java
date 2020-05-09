package com.projectpico.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieResults implements Parcelable {
    private Integer id;
    private String posterPath;

    public MovieResults() {
        this.id = 0;
        this.posterPath = "";
    }

    // Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.posterPath);
    }

    protected MovieResults(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.posterPath = in.readString();
    }

    public static final Creator<MovieResults> CREATOR = new Creator<MovieResults>() {
        @Override
        public MovieResults createFromParcel(Parcel source) {
            return new MovieResults(source);
        }

        @Override
        public MovieResults[] newArray(int size) {
            return new MovieResults[size];
        }
    };

    // Getter
    public Integer getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
