package com.example.juliannr.nextmovie.utility;

import android.app.Application;

import com.example.juliannr.nextmovie.api.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.juliannr.nextmovie.utility.Constant.Api.BASE_URL;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class App extends Application {
    private Retrofit retrofit;
    private Gson gson;
    private static App instance;

    public App() {
        this.instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        makeRetrofit();
        makeGson();
    }

    private void makeGson() {
        gson = new GsonBuilder().create();
    }

    private void makeRetrofit() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public Api getApi() {
        return getRetrofit().create(Api.class);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public Gson getGson() {
        return gson;
    }

    public static App getInstance() {
        return instance;
    }
}
