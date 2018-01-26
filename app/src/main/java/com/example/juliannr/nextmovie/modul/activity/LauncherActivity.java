package com.example.juliannr.nextmovie.modul.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.juliannr.nextmovie.R;
import com.example.juliannr.nextmovie.modul.presenter.LauncherPresenter;
import com.example.juliannr.nextmovie.modul.view.LauncherView;
import com.example.juliannr.nextmovie.utility.Session;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Julian Noor Rizani on 25/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class LauncherActivity extends AppCompatActivity implements LauncherView {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private LauncherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),
                android.R.color.white),
                PorterDuff.Mode.SRC_IN);
        presenter = new LauncherPresenter();
        presenter.setView(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.loadSession();
            }
        }, 3000);
    }

    @Override
    public void onFoundSession(String sessionId, String expired) {
        if(Session.getInstance().getSessionId().equals("") ||
                Session.getInstance().getSessionId() == null){
            Session.getInstance().setSessionId(sessionId);
            Session.getInstance().setExpiredDate(expired);
        }
        startActivity(new Intent(this, MovieActivity.class));
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, "Cannot open app : " + message, Toast.LENGTH_LONG).show();
    }
}
