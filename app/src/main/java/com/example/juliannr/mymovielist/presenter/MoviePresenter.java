package com.example.juliannr.mymovielist.presenter;

import com.example.juliannr.mymovielist.model.MovieResponse;
import com.example.juliannr.mymovielist.utility.App;
import com.example.juliannr.mymovielist.view.MovieView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.juliannr.mymovielist.utility.Constant.Api.API_KEY;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class MoviePresenter {
    MovieView view;

    public void loadMovies(int page){
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

    public MovieView getView() {
        return view;
    }

    public void setView(MovieView view) {
        this.view = view;
    }
}
