package com.example.juliannr.mymovielist.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class Movie {
    private String title;
    @SerializedName("poster_path")
    private String image;
    @SerializedName("backdrop_path")
    private String backdrop;

    public String getBackdrop() {
        return backdrop;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
