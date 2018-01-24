package com.example.juliannr.mymovielist.modul.presenter;

import com.example.juliannr.mymovielist.model.MovieDetail;
import com.example.juliannr.mymovielist.model.controller.MovieDetailController;
import com.example.juliannr.mymovielist.modul.view.DetailView;
import com.example.juliannr.mymovielist.utility.App;
import com.example.juliannr.mymovielist.utility.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class DetailPresenter {
    private DetailView view;
    private MovieDetailController controller;

    public DetailPresenter() {
        controller = new MovieDetailController();
    }

    public void loadDetail(int id, String jenis){
        getView().onLoading();
        if(!jenis.equals(Constant.FragmentChooser.FAVORITE)){
            Call<MovieDetail> call = App.getInstance().getApi().getDetail(id, Constant.Api.API_KEY);
            call.enqueue(new Callback<MovieDetail>() {
                @Override
                public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                    if(response.isSuccessful()){
                        if (response.body() != null){
                            getView().onLoadData(response.body());
                            getView().onNoLoading();
                        }else {
                            getView().onError("Failed to get movie detail");
                        }
                    }
                }

                @Override
                public void onFailure(Call<MovieDetail> call, Throwable t) {
                    getView().onError("Server Failure: " + t.getMessage());
                }
            });
        }else {
            MovieDetail movie = controller.getMovie(id);
            getView().onLoadData(movie);
            getView().onNoLoading();
        }
    }

    public DetailView getView() {
        return view;
    }

    public void setView(DetailView view) {
        this.view = view;
    }

    public void checkFavorite(int id) {
        getView().setFavorite(controller.isExist(id));
    }

    public void setFavorite(boolean favorite, MovieDetail movie) {
        if(favorite){
            controller.insert(movie);
            getView().setFavorite(true);
        }else {
            controller.delete(movie.getId());
            getView().setFavorite(false);
        }
    }
}
