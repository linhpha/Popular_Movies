package com.example.phamlinh.popularmovie;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        final String TMDB_POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185";
        this.imageUrl = TMDB_POSTER_BASE_URL + imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    private String title;
    private String imageUrl;
    private String description;
    private String releaseDate;
    private Double voteAverage;


    public Movie() {
        
    }
    public Movie(String title, String imageUrl, String description, String releaseDate, Double voteAverage) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }

    public Movie(Parcel in) {
        title = in.readString();
        imageUrl = in.readString();
        description = in.readString();
        releaseDate = in.readString();
        voteAverage = (Double) in.readValue(Double.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(imageUrl);
        dest.writeString(description);
        dest.writeString(releaseDate);
        dest.writeValue(voteAverage);
    }


    @Override
    public int describeContents() {
        return 0;
    }
}
