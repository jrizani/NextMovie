package com.example.juliannr.mymovielist.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class MovieDetail {
    private int id;
    @SerializedName("backdrop_path")
    private String header;
    @SerializedName("original_title")
    private String title;
    @SerializedName("overview")
    private String description;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("release_date")
    private Date date;
    @SerializedName("vote_average")
    private double rating;
    @SerializedName("runtime")
    private int duration;

    public int getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPoster() {
        return poster;
    }

    public Date getDate() {
        return date;
    }

    public double getRating() {
        return rating;
    }

    public int getDuration() {
        return duration;
    }
}
