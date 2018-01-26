package com.example.juliannr.nextmovie.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Julian Noor Rizani on 25/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class RatingResponse {
    @SerializedName("status_message")
    private String message;

    public String getMessage() {
        return message;
    }
}
