package com.projectpico.popularmovies.model.Movie;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**********************************************************************************************************************
 * A model used to generate a POJO from the JSON string provided by the GET request in our MovieClient. This POJO
 * represents the model from themoviedb.org
 *
 * @author mlewis
 * @version May 8, 2020
 *********************************************************************************************************************/
public class VideoResults implements Parcelable {
    private List<Video> results;

    public VideoResults() {
        this.results = new ArrayList<>();
    }

    // Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.results);
    }

    protected VideoResults(Parcel in) {
        this.results = in.createTypedArrayList(Video.CREATOR);
    }

    public static final Creator<VideoResults> CREATOR = new Creator<VideoResults>() {
        @Override
        public VideoResults createFromParcel(Parcel source) {
            return new VideoResults(source);
        }

        @Override
        public VideoResults[] newArray(int size) {
            return new VideoResults[size];
        }
    };

    // Getters

    public List<Video> getResults() {
        return results;
    }
}
