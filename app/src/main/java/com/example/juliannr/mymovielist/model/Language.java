package com.example.juliannr.mymovielist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class Language extends RealmObject{
    @SerializedName("iso_639_1")
    String languageCode;
    @SerializedName("name")
    String languageName;

    public String getLanguageCode() {
        return languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }
}

