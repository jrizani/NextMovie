package com.example.juliannr.nextmovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Julian Noor Rizani on 25/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class CastResponse extends RealmObject {
    @SerializedName("cast")
    private RealmList<Cast> casts;
    private int id;

    public List<Cast> getCasts() {
        return casts;
    }

    public int getId() {
        return id;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = new RealmList<>();
        for(Cast cast : casts){
            this.casts.add(cast);
        }
    }

    public void setId(int id) {
        this.id = id;
    }
}
