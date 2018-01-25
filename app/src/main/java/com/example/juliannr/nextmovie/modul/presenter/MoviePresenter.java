package com.example.juliannr.nextmovie.modul.presenter;

import com.example.juliannr.nextmovie.model.MovieResponse;
import com.example.juliannr.nextmovie.model.controller.MovieDetailController;
import com.example.juliannr.nextmovie.utility.App;
import com.example.juliannr.nextmovie.modul.view.MovieView;
import com.example.juliannr.nextmovie.utility.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.juliannr.nextmovie.utility.Constant.Api.API_KEY;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class MoviePresenter {
    MovieView view;
    MovieDetailController controller;

    public MoviePresenter() {
        controller = new MovieDetailController();
    }

    private void loadNowPlaying(int page){
        Call<MovieResponse> call = App.getInstance().getApi().getNowPlaying(API_KEY, page);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        getView().setTotalPages(response.body().getTotalPages());
                        if(response.body().getMovies() != null){
                            getView().onMoviesFound(response.body().getMovies());
                        }
                        else {
                            getView().onError("Failed to found movie list");
                        }
                    }
                    else {
                        getView().onError("No Data");
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                getView().onError("Server Failed: " + t.getMessage());
            }
        });
    }

    private void loadTopRated(int page){
        Call<MovieResponse> call = App.getInstance().getApi().getTopRated(API_KEY, page);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        getView().setTotalPages(response.body().getTotalPages());
                        if(response.body().getMovies() != null){
                            getView().onMoviesFound(response.body().getMovies());
                        }
                        else {
                            getView().onError("Failed to found movie list");
                        }
                    }
                    else {
                        getView().onError("No Data");
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                getView().onError("Server Failed: " + t.getMessage());
            }
        });
    }

    private void loadUpcoming(int page){
        Call<MovieResponse> call = App.getInstance().getApi().getUpcoming(API_KEY, page);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        getView().setTotalPages(response.body().getTotalPages());
                        if(response.body().getMovies() != null){
                            getView().onMoviesFound(response.body().getMovies());
                        }
                        else {
                            getView().onError("Failed to found movie list");
                        }
                    }
                    else {
                        getView().onError("No Data");
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                getView().onError("Server Failed: " + t.getMessage());
            }
        });
    }

    private void loadPopular(int page){
        Call<MovieResponse> call = App.getInstance().getApi().getPopular(API_KEY, page);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        getView().setTotalPages(response.body().getTotalPages());
                        if(response.body().getMovies() != null){
                            getView().onMoviesFound(response.body().getMovies());
                        }
                        else {
                            getView().onError("Failed to found movie list");
                        }
                    }
                    else {
                        getView().onError("No Data");
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                getView().onError("Server Failed: " + t.getMessage());
            }
        });
    }

    public MovieView getView() {
        return view;
    }

    public void setView(MovieView view) {
        this.view = view;
    }

    public void loadMovies(String jenis, int i) {
        switch (jenis){
            case Constant.FragmentChooser.NOW_PLAYING:
                loadNowPlaying(i);
                break;
            case Constant.FragmentChooser.TOP_RATED:
                loadTopRated(i);
                break;
            case Constant.FragmentChooser.UPCOMING:
                loadUpcoming(i);
                break;
            case Constant.FragmentChooser.POPULAR:
                loadPopular(i);
                break;
        }
    }
}
