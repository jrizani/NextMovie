package com.example.juliannr.nextmovie.modul.presenter;


import com.example.juliannr.nextmovie.model.SessionResponse;
import com.example.juliannr.nextmovie.modul.view.LauncherView;
import com.example.juliannr.nextmovie.utility.App;
import com.example.juliannr.nextmovie.utility.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Julian Noor Rizani on 26/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class LauncherPresenter {
    private LauncherView view;

    public LauncherView getView() {
        return view;
    }

    public void setView(LauncherView view) {
        this.view = view;
    }

    public void loadSession(){
        Call<SessionResponse> call = App.getInstance().getApi().getSession(Constant.Api.API_KEY);
        call.enqueue(new Callback<SessionResponse>() {
            @Override
            public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
                if(response.code() == 200){
                    getView().onFoundSession(response.body().getSessionId(), response.body().getExpiredDate());
                }else {
                    getView().onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<SessionResponse> call, Throwable t) {
                getView().onError(t.getMessage());
            }
        });
    }
}
