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
public class Review implements Parcelable {
    private String author;
    private String id;
    private String content;
    private String url;

    public Review() {
        this.author = "";
        this.id = "";
        this.content = "";
        this.url = "";
    }

    // Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.id);
        dest.writeString(this.content);
        dest.writeString(this.url);
    }

    protected Review(Parcel in) {
        this.author = in.readString();
        this.id = in.readString();
        this.content = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel source) {
            return new Review(source);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    // Getters
    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}
