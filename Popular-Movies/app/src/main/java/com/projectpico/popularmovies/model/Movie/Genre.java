package com.projectpico.popularmovies.model.Movie;

import android.os.Parcel;
import android.os.Parcelable;

/**********************************************************************************************************************
 * A model used to generate a POJO from the JSON string provided by the GET request in our MovieClient. This POJO
 * represents the model from themoviedb.org
 *
 * @author mlewis
 * @version May 8, 2020
 *********************************************************************************************************************/
public class Genre implements Parcelable {
    private int id;
    private String name;

    public Genre() {
        this.id = 0;
        this.name = "";
    }

    // Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    protected Genre(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel source) {
            return new Genre(source);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
