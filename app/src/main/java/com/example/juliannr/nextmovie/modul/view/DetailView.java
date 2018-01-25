package com.example.juliannr.nextmovie.modul.view;

import com.example.juliannr.nextmovie.model.MovieDetail;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public interface DetailView {
    void onLoadData(MovieDetail movie);

    void onError(String messsage);

    void setFavorite(boolean favorite);

    void onLoading();

    void onNoLoading();
}
