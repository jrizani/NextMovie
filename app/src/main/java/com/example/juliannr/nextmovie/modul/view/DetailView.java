package com.example.juliannr.nextmovie.modul.view;

import com.example.juliannr.nextmovie.model.Cast;
import com.example.juliannr.nextmovie.model.MovieDetail;

import java.util.List;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public interface DetailView {
    void onLoadData(MovieDetail movie, List<Cast> casts);

    void onError(String messsage);

    void setFavorite(boolean favorite);

    void successRate(String message);

    void failedRate(String message);

    void onLoading();

    void onNoLoading();
}
