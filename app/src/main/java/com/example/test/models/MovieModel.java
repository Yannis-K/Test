package com.example.test.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {



    private String title;
    private String poster_path;
    private float vote_average;
    private int runtime;
    private String original_language;



    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        vote_average = in.readFloat();
        runtime = in.readInt();
        original_language = in.readString();

    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }


    public float getVote_average() {
        return vote_average;
    }



    public int getRuntime() {
        return runtime;
    }

    public String getOriginal_language() {
        return original_language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeFloat(vote_average);
        parcel.writeString(original_language);
    }


    @Override
    public String toString() {
        return "MovieModel{" +
                "title='" + title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", vote_average=" + vote_average +
                ", runtime='" + runtime + '\'' +
                ", original_language='" + original_language + '\'' +
                '}';
    }
}
