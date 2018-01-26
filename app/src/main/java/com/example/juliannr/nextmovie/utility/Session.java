package com.example.juliannr.nextmovie.utility;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Julian Noor Rizani on 26/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class Session {
    private static final String AUTH_PREFERENCES = "auth_preferences";
    private static final String SESSION_ID = "sessionId";
    private static final String EXPIRED_DATE = "expiredDate";

    private static Session instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Session(Context context) {
        sharedPreferences = context.getSharedPreferences(AUTH_PREFERENCES, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if(!sharedPreferences.getString(EXPIRED_DATE, "").equals("") &&
                sharedPreferences.getString(EXPIRED_DATE, "") != null){
            Date today = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
            Date expired = App.getInstance()
                    .parseDateUTC(sharedPreferences.getString(EXPIRED_DATE, ""));
            if(today.after(expired)){
                editor.clear();
                editor.commit();
            }
        }
    }

    public static void init(Context context){
        instance = new Session(context);
    }

    public synchronized static Session getInstance(){
        return instance;
    }

    public void setSessionId(String sessionId){
        editor.putString(SESSION_ID, sessionId);
        editor.commit();
    }

    public String getSessionId(){
        return sharedPreferences.getString(SESSION_ID, "");
    }

    public void setExpiredDate(String expiredDate){
        editor.putString(EXPIRED_DATE, expiredDate);
        editor.commit();
    }

    public String getExpiredDate(){
        return sharedPreferences.getString(EXPIRED_DATE, "");
    }
}
