package com.example.juliannr.nextmovie.modul.view;

import com.example.juliannr.nextmovie.model.Movie;

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
