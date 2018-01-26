package com.example.juliannr.nextmovie.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Julian Noor Rizani on 25/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class RatingBody {
    @SerializedName("value")
    private double rate;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
