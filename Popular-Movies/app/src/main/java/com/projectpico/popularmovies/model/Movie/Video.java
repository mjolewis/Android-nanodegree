package com.projectpico.popularmovies.model.Movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**********************************************************************************************************************
 * A model used to generate a POJO from the JSON string provided by the GET request in our MovieClient. This POJO
 * represents the model from themoviedb.org
 *
 * @author mlewis
 * @version May 8, 2020
 *********************************************************************************************************************/
public class Video implements Parcelable {
    private String site;
    private int size;
    @SerializedName("iso_639_1")
    @Expose
    private String iso31661;
    private String name;
    private String id;
    private String type;
    @SerializedName("iso_3166_1")
    @Expose
    private String iso6391;
    private String key;

    public Video() {
        this.site = "";
        this.size = 0;
        this.iso31661 = "";
        this.name = "";
        this.id = "";
        this.type = "";
        this.iso6391 = "";
        this.key = "";
    }

    // Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.site);
        dest.writeInt(this.size);
        dest.writeString(this.iso31661);
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeString(this.type);
        dest.writeString(this.iso6391);
        dest.writeString(this.key);
    }

    protected Video(Parcel in) {
        this.site = in.readString();
        this.size = in.readInt();
        this.iso31661 = in.readString();
        this.name = in.readString();
        this.id = in.readString();
        this.type = in.readString();
        this.iso6391 = in.readString();
        this.key = in.readString();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    // Getters
    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    public String getIso31661() {
        return iso31661;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getIso6391() {
        return iso6391;
    }

    public String getKey() {
        return key;
    }
}
