package com.example.juliannr.mymovielist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class MovieResponse {
    @SerializedName("results")
    private List<Movie> movies;
    @SerializedName("total_pages")
    private int totalPages;

    public List<Movie> getMovies() {
        return movies;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
