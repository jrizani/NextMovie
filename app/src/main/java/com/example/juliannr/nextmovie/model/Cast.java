package com.example.juliannr.nextmovie.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Julian Noor Rizani on 25/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class Cast extends RealmObject {
    private String character;
    private String name;
    @SerializedName("profile_path")
    private String image;

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
