package com.example.juliannr.mymovielist.view;

import com.example.juliannr.mymovielist.model.Movie;

import java.util.List;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public interface MovieView {
    void onMoviesFound(List<Movie> movies);

    void setTotalPages(int pages);

    void onError(String message);
}
