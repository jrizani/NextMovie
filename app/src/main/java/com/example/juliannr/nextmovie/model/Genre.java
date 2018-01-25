package com.example.juliannr.nextmovie.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class Genre extends RealmObject{
    @SerializedName("name")
    String genreName;

    public String getGenreName() {
        return genreName;
    }
}
