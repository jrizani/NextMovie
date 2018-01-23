package com.example.juliannr.mymovielist.api;

import com.example.juliannr.mymovielist.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public interface Api {
    @GET("now_playing")
    Call<MovieResponse> getNowPlaying(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("top_rated")
    Call<MovieResponse> getTopRated(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("upcoming")
    Call<MovieResponse> getUpcoming(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("popular")
    Call<MovieResponse> getPopular(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );
}
