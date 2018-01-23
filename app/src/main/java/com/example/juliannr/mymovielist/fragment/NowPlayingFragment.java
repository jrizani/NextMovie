package com.example.juliannr.mymovielist.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.juliannr.mymovielist.R;
import com.example.juliannr.mymovielist.adapter.MovieAdapter;
import com.example.juliannr.mymovielist.model.Movie;
import com.example.juliannr.mymovielist.presenter.MoviePresenter;
import com.example.juliannr.mymovielist.utility.Constant;
import com.example.juliannr.mymovielist.view.MovieView;

import java.util.List;
import java.util.Random;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class NowPlayingFragment extends Fragment implements MovieView {

    private Unbinder unbinder;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.slider)
    ImageView slider;
    @BindView(R.id.slider_title)
    TextView sliderTitle;
    private MoviePresenter presenter;
    private int pages;

    public NowPlayingFragment() {
        presenter = new MoviePresenter();
        presenter.setView(this);
    }

    public static NowPlayingFragment newInstance(){
        NowPlayingFragment fragment = new NowPlayingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        unbinder = ButterKnife.bind(this, view);
        //presenter.loadNowPlaying(1);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadNowPlaying(1);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder.unbind();
        unbinder = null;
    }

    @Override
    public void onStart() {
        super.onStart();
//        presenter.loadNowPlaying(1);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadNowPlaying(1);
    }

    @Override
    public void onMoviesFound(final List<Movie> movies) {
        if(!movies.isEmpty() && movies != null){
            final Random random = new Random();
            Movie movie = movies.get(random.nextInt(movies.size()));
            slider.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            MovieAdapter adapter = new MovieAdapter(movies, getContext());
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setAdapter(adapter);

            Glide.with(getContext()).load(Constant.Api.IMAGE_PATH +
                    movie.getBackdrop()).into(slider);
            sliderTitle.setText(movie.getTitle());
            sliderTitle.setVisibility(View.VISIBLE);

            adapter.setSetOnItemClickListener(new MovieAdapter.SetOnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    //masuk detail
                }
            });
            new CountDownTimer(10000,1000){
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    Movie movie = movies.get(random.nextInt(movies.size()));
                    Glide.with(getContext()).load(Constant.Api.IMAGE_PATH +
                            movie.getBackdrop()).into(slider);
                    sliderTitle.setText(movie.getTitle());
                    sliderTitle.setVisibility(View.VISIBLE);
                    start();
                }
            }.start();
        }
    }

    @Override
    public void setTotalPages(int pages) {
        this.pages = pages;
    }

    @Override
    public void onError(String message) {
        sliderTitle.setVisibility(View.GONE);
        slider.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
    }
}
