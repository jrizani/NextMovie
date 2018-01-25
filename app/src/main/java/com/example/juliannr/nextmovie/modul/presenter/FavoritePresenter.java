package com.example.juliannr.nextmovie.modul.presenter;

import com.example.juliannr.nextmovie.model.MovieDetail;
import com.example.juliannr.nextmovie.model.controller.MovieDetailController;
import com.example.juliannr.nextmovie.modul.view.FavoriteView;

import java.util.List;

/**
 * Created by Julian Noor Rizani on 24/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class FavoritePresenter {
    FavoriteView view;
    MovieDetailController controller;

    public FavoritePresenter() {
        controller = new MovieDetailController();
    }

    public void loadFavorite(){
        List<MovieDetail> movies = controller.getAll();
        if(movies != null && !movies.isEmpty()){
            getView().onDataFound(movies);
        }else {
            getView().onNoFavorite();
        }
    }

    public FavoriteView getView() {
        return view;
    }

    public void setView(FavoriteView view) {
        this.view = view;
    }
}
