package com.example.juliannr.nextmovie.modul.view;

/**
 * Created by Julian Noor Rizani on 26/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public interface LauncherView {
    void onFoundSession(String sessionId, String expired);
    void onError(String message);
}
