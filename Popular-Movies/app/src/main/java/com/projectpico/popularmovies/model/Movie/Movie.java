package com.projectpico.popularmovies.model.Movie;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**********************************************************************************************************************
 * A model used to generate a POJO from the JSON string provided by the GET request in our MovieClient. This POJO
 * represents the model from themoviedb.org
 *
 * @author mlewis
 * @version May 8, 2020
 *********************************************************************************************************************/
public class Movie implements Parcelable {
    private int id;
    private String title;
    private String overview;
    private String posterPath;
    private String backdropPath;
    private String releaseDate;
    private int runtime;
    private double voteAverage;
    private VideoResults videos;
    private Reviews reviews;
    private List<Genre> genres;

    public Movie() {
        this.id = 0;
        this.title = "";
        this.overview = "";
        this.posterPath = "";
        this.backdropPath = "";
        this.releaseDate = "";
        this.runtime = 0;
        this.voteAverage = 0.0;
        this.videos = new VideoResults();
        this.reviews = new Reviews();
        this.genres = new ArrayList<>();
    }

    public Movie(int id, String title, String overview, String posterPath, String backdropPath, String releaseDate,
                 int runtime, double voteAverage, VideoResults videos, Reviews reviews, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.voteAverage = voteAverage;
        this.videos = videos;
        this.reviews = reviews;
        this.genres = genres;
    }

    // Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeString(this.releaseDate);
        dest.writeInt(this.runtime);
        dest.writeDouble(this.voteAverage);
        dest.writeParcelable(this.videos, flags);
        dest.writeParcelable(this.reviews, flags);
        dest.writeTypedList(this.genres);
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.runtime = in.readInt();
        this.voteAverage = in.readDouble();
        this.videos = in.readParcelable(VideoResults.class.getClassLoader());
        this.reviews = in.readParcelable(Reviews.class.getClassLoader());
        this.genres = in.createTypedArrayList(Genre.CREATOR);
    }

    public static final Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getReleaseDateLocalized(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
        Date date = null;
        try {
            date = sdf.parse(releaseDate);
        } catch (ParseException e) {
            return releaseDate;
        }
        DateFormat dateFormat = android.text.format.DateFormat.getMediumDateFormat(context);
        return dateFormat.format(date);
    }

    public int getRuntime() {
        return runtime;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public VideoResults getVideos() {
        return videos;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getDuration() {
        return String.valueOf(runtime) + " min";
    }
}
