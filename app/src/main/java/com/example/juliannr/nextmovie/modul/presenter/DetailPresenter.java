package com.example.juliannr.nextmovie.modul.presenter;

import com.example.juliannr.nextmovie.model.Cast;
import com.example.juliannr.nextmovie.model.CastResponse;
import com.example.juliannr.nextmovie.model.MovieDetail;
import com.example.juliannr.nextmovie.model.RatingBody;
import com.example.juliannr.nextmovie.model.RatingResponse;
import com.example.juliannr.nextmovie.model.controller.MovieDetailController;
import com.example.juliannr.nextmovie.modul.view.DetailView;
import com.example.juliannr.nextmovie.utility.App;
import com.example.juliannr.nextmovie.utility.Constant;
import com.example.juliannr.nextmovie.utility.Session;

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
                            getCasts(response.body());
                        }else {
                            getView().onError("Failed to get movie detail");
                        }
                    }else {
                        getView().onError("Server Failure: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<MovieDetail> call, Throwable t) {
                    getView().onError("Server Failure: " + t.getMessage());
                }
            });
        }else {
            MovieDetail movie = controller.getMovie(id);
            CastResponse cast = controller.getCast(id);
            getView().onLoadData(movie, cast.getCasts());
            getView().onNoLoading();
        }
    }

    private void getCasts(final MovieDetail detail) {
        Call<CastResponse> call = App.getInstance().getApi().getCast(detail.getId(), Constant.Api.API_KEY);
        call.enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                if(response.code() == 200){
                    getView().onLoadData(detail, response.body().getCasts());
                    getView().onNoLoading();
                }else {
                    getView().onError("Server Failure: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                getView().onError("Server Failure: " + t.getMessage());
            }
        });
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

    public void setFavorite(boolean favorite, MovieDetail movie, List<Cast> casts) {
        if(favorite){
            CastResponse cast = new CastResponse();
            cast.setCasts(casts);
            cast.setId(movie.getId());
            controller.insert(movie, cast);
            getView().setFavorite(true);
        }else {
            controller.delete(movie.getId());
            getView().setFavorite(false);
        }
    }

    public void sendRating(int id, final double rate) {
        RatingBody body = new RatingBody();
        body.setRate(rate);
        Call<RatingResponse> call = App.getInstance().getApi().rate(id, Constant.Api.API_KEY,
                Session.getInstance().getSessionId(), body);
        call.enqueue(new Callback<RatingResponse>() {
            @Override
            public void onResponse(Call<RatingResponse> call, Response<RatingResponse> response) {
                if(response.isSuccessful()){
                    getView().successRate("You send rate " + rate + " to this movie");
                }else {
                    getView().failedRate(response.message());
                }
            }

            @Override
            public void onFailure(Call<RatingResponse> call, Throwable t) {
                getView().failedRate(t.getMessage());
            }
        });
    }
}
