package com.example.juliannr.mymovielist.modul.view;

import com.example.juliannr.mymovielist.model.MovieDetail;

import java.util.List;

/**
 * Created by Julian Noor Rizani on 24/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public interface FavoriteView {
    void onDataFound(List<MovieDetail> movies);

    void onNoFavorite();
}
