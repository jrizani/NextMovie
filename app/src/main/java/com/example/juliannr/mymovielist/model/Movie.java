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

    private boolean favorited = false;

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
