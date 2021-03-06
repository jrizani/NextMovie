package com.example.juliannr.nextmovie.model.controller;

import com.example.juliannr.nextmovie.model.Cast;
import com.example.juliannr.nextmovie.model.CastResponse;
import com.example.juliannr.nextmovie.model.MovieDetail;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class MovieDetailController {
    Realm realm;

    public void insert(MovieDetail movie, CastResponse casts){
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insert(movie);
        realm.insert(casts);
        realm.commitTransaction();
    }

    public List<MovieDetail> getAll(){
        realm = Realm.getDefaultInstance();
        List<MovieDetail> movies = new ArrayList<>();
        movies.addAll(realm.where(MovieDetail.class).findAll());
        return movies;
    }

    public CastResponse getCast(int id){
        realm = Realm.getDefaultInstance();
        List<CastResponse> casts = new ArrayList<>();
        casts.addAll(realm.where(CastResponse.class).equalTo("id", id).findAll());
        return casts.get(0);
    }

    public boolean isExist(int id){
        realm = Realm.getDefaultInstance();
        MovieDetail movie;
        movie = realm.where(MovieDetail.class).equalTo("id", id).findFirst();
        if(movie == null) return false;
        else return true;
    }

    public void delete(int id){
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(MovieDetail.class).equalTo("id", id).findFirst().deleteFromRealm();
        realm.where(CastResponse.class).equalTo("id", id).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }

    public MovieDetail getMovie(int id) {
        realm = Realm.getDefaultInstance();
        return realm.where(MovieDetail.class).equalTo("id", id).findFirst();
    }
}
