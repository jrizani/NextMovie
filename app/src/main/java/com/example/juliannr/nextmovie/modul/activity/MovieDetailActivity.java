package com.example.juliannr.nextmovie.modul.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.juliannr.nextmovie.R;
import com.example.juliannr.nextmovie.model.Cast;
import com.example.juliannr.nextmovie.model.Genre;
import com.example.juliannr.nextmovie.model.MovieDetail;
import com.example.juliannr.nextmovie.modul.adapter.CastAdapter;
import com.example.juliannr.nextmovie.modul.presenter.DetailPresenter;
import com.example.juliannr.nextmovie.modul.view.DetailView;
import com.example.juliannr.nextmovie.utility.Constant;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements DetailView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_favorite)
    ImageView favorite;
    @BindView(R.id.header)
    ImageView header;
    @BindView(R.id.poster)
    ImageView poster;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.genre)
    TextView genre;
    @BindView(R.id.language)
    TextView language;
    @BindView(R.id.rating)
    TextView rating;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.duration)
    TextView duration;
    @BindView(R.id.loading)
    RelativeLayout loading;
    @BindView(R.id.cast)
    RecyclerView cast;

    //Rate
    @BindView(R.id.rate1)
    ImageView rate1;
    @BindView(R.id.rate2)
    ImageView rate2;
    @BindView(R.id.rate3)
    ImageView rate3;
    @BindView(R.id.rate4)
    ImageView rate4;
    @BindView(R.id.rate5)
    ImageView rate5;
    @BindView(R.id.btn_rating)
    AppCompatButton btnRating;

    private int id;

    private boolean favorited;

    private DetailPresenter presenter;
    private String jenis;

    private MovieDetail movie;
    private CastAdapter castAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setSupportActionBar(toolbar);
        presenter = new DetailPresenter();
        presenter.setView(this);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", 1);
        jenis = getIntent().getStringExtra("jenis");
        presenter.loadDetail(id, jenis);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie, menu);
        return true;
    }

    @Override
    public void onLoadData(final MovieDetail movie, final List<Cast> casts) {
        toolbar.setTitle(movie.getTitle());
        this.movie = movie;
        Glide.with(this).load(Constant.Api.IMAGE_PATH + movie.getHeader()).into(header);
        Glide.with(this).load(Constant.Api.IMAGE_PATH + movie.getPoster()).into(poster);
        description.setText(movie.getDescription());
        String genres = "";
        for (Genre genre : movie.getGenres()) {
            if ("".equals(genres)) genres = genre.getGenreName();
            else genres += ", " + genre.getGenreName();
        }
        genre.setText(genres);
        language.setText(movie.getLanguage().get(0).getLanguageCode() + " - " +
                movie.getLanguage().get(0).getLanguageName());
        rating.setText(movie.getRating() + " / 10");
        date.setText(movie.getDate().toLocaleString().substring(0, 12));
        int hour = 0;
        int minute = 0;
        String durationString = "";
        if (movie.getDuration() > 60) {
            hour = movie.getDuration() / 60;
            minute = movie.getDuration() - (60 * hour);
            durationString = hour + "h " + minute + "m";
        } else {
            durationString = minute + "m";
        }
        duration.setText(durationString);
        presenter.checkFavorite(movie.getId());
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favorited) presenter.setFavorite(false, movie, casts);
                else presenter.setFavorite(true, movie, casts);
            }
        });
        if(castAdapter == null) castAdapter = new CastAdapter(this);
        cast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        cast.setHasFixedSize(true);
        cast.setAdapter(castAdapter);
        castAdapter.clearData();
        castAdapter.setData(casts);
        rate1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setRate(2);
                return true;
            }
        });
        rate2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setRate(4);
                return true;
            }
        });
        rate3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setRate(6);
                return true;
            }
        });
        rate4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setRate(8);
                return true;
            }
        });
        rate5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setRate(10);
                return true;
            }
        });
    }

    private void setRate(final int i) {
        clearRate();
        switch (i){
            case 2:
                rate1.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                break;
            case 4:
                rate1.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                rate2.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                break;
            case 6:
                rate1.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                rate2.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                rate3.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                break;
            case 8:
                rate1.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                rate2.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                rate3.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                rate4.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                break;
            case 10:
                rate1.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                rate2.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                rate3.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                rate4.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                rate5.setImageDrawable(getResources().getDrawable(R.drawable.ic_is_rating));
                break;
            default:
                break;
        }
        btnRating.setEnabled(true);
        btnRating.setClickable(true);
        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.sendRating(id, i);
            }
        });
    }

    private void clearRate() {
        rate1.setImageDrawable(getResources().getDrawable(R.drawable.ic_unrating));
        rate2.setImageDrawable(getResources().getDrawable(R.drawable.ic_unrating));
        rate3.setImageDrawable(getResources().getDrawable(R.drawable.ic_unrating));
        rate4.setImageDrawable(getResources().getDrawable(R.drawable.ic_unrating));
        rate5.setImageDrawable(getResources().getDrawable(R.drawable.ic_unrating));

        btnRating.setEnabled(false);
        btnRating.setClickable(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, setShare());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
        }
        return true;
    }

    private String setShare() {
        String content = "[Next Movie - My Movie List]\n";
        content += "Check this out!!\n" +
                movie.getTitle() + " ";
        if (isPlaying(movie.getDate())) {
            content += "\n This movie will release on " + movie.getDate().toLocaleString().substring(0, 12);
        }
        content += "\n\nHelp me to develop this app, check my github: \n" +
                Constant.GITHUB_LINK;
        return content;
    }

    private boolean isPlaying(Date date) {
        return GregorianCalendar.getInstance().getTime()
                .before(date);
    }

    @Override
    public void onError(String messsage) {
        Toast.makeText(this, messsage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setFavorite(boolean favorite) {
        favorited = favorite;
        if (favorite)
            this.favorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorited));
        else this.favorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_unfavorite));
    }

    @Override
    public void onLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNoLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void successRate(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        clearRate();
    }

    @Override
    public void failedRate(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
