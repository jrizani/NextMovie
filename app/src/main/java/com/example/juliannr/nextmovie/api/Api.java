package com.example.juliannr.nextmovie.api;

import com.example.juliannr.nextmovie.model.CastResponse;
import com.example.juliannr.nextmovie.model.MovieDetail;
import com.example.juliannr.nextmovie.model.MovieResponse;
import com.example.juliannr.nextmovie.model.RatingBody;
import com.example.juliannr.nextmovie.model.RatingResponse;
import com.example.juliannr.nextmovie.model.SessionResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public interface Api {
    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlaying(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRated(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcoming(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/popular")
    Call<MovieResponse> getPopular(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/{movie_id}")
    Call<MovieDetail> getDetail(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}/credits")
    Call<CastResponse> getCast(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey
    );

    @POST("movie/{movie_id}/rating")
    Call<RatingResponse> rate(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("guest_session_id") String sessionId,
            @Body RatingBody body
            );

    @GET("authentication/guest_session/new")
    Call<SessionResponse> getSession(
            @Query("api_key") String apiKey
    );
}
