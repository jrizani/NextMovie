package com.example.juliannr.mymovielist.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class Movie {
    private int id;
    private String title;
    @SerializedName("poster_path")
    private String image;
    @SerializedName("backdrop_path")
    private String backdrop;
    @SerializedName("vote_average")
    private float rating;

    public float getRating() {
        return rating;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }


}
