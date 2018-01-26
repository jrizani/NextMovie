package com.example.juliannr.nextmovie.model;

import android.text.format.DateUtils;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Julian Noor Rizani on 26/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class SessionResponse {
    @SerializedName("guest_session_id")
    private String sessionId;
    @SerializedName("expires_at")
    private String expiredDate;

    public String getSessionId() {
        return sessionId;
    }

    public String getExpiredDate() {
        return expiredDate;
    }
}
