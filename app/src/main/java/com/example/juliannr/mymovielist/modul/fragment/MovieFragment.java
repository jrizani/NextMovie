package com.example.juliannr.mymovielist.modul.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.example.juliannr.mymovielist.model.Movie;
import com.example.juliannr.mymovielist.modul.adapter.MovieAdapter;
import com.example.juliannr.mymovielist.modul.presenter.MoviePresenter;
import com.example.juliannr.mymovielist.modul.view.MovieView;
import com.example.juliannr.mymovielist.utility.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class MovieFragment extends Fragment implements MovieView {

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
    private String jenis;
    private Context context;
//    private boolean isLoading = true;
//    private int page = 1;
//    private List<Movie> allMovies = new ArrayList<>();

    public MovieFragment() {
        presenter = new MoviePresenter();
        presenter.setView(this);
    }

    public static MovieFragment newInstance(String jenis) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putString("jenis", jenis);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setRetainInstance(true);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            this.jenis = getArguments().getString("jenis");
        }
        presenter.loadMovies(jenis, 1);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder = null;
//        page = 1;
//        allMovies = new ArrayList<>();
    }

    @Override
    public void onStart() {
        super.onStart();
//        presenter.loadNowPlaying(1);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onMoviesFound(final List<Movie> movies) {
//        allMovies.addAll(movies);
        if (!movies.isEmpty() && movies != null) {
//            isLoading = false;
            final Random random = new Random();
            Movie movie = movies.get(random.nextInt(movies.size()));
            slider.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            final MovieAdapter adapter = new MovieAdapter(movies, getContext());
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setAdapter(adapter);

            Glide.with(context).load(Constant.Api.IMAGE_PATH +
                    movie.getBackdrop()).into(slider);
            sliderTitle.setText(movie.getTitle());
            sliderTitle.setVisibility(View.VISIBLE);

            adapter.setSetOnItemClickListener(new MovieAdapter.SetOnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    //masuk detail
                }
            });
            new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    Movie movie = movies.get(random.nextInt(movies.size()));
                    Glide.with(context).load(Constant.Api.IMAGE_PATH +
                            movie.getBackdrop()).into(slider);
                    sliderTitle.setText(movie.getTitle());
                    sliderTitle.setVisibility(View.VISIBLE);
                    start();
                }
            }.start();
//            final GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
//            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//
//                @Override
//                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//                    int visibleThreshold = 1;
//                    int totalItemCount = layoutManager.getItemCount();
//                    int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
//                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
//                        if (adapter.getOnLoadMoreListener() != null) {
//                            adapter.getOnLoadMoreListener().onLoadMore();
//                        }
//                        isLoading = true;
//                    }
//                }
//            });
//
//            adapter.setOnLoadMoreListener(new MovieAdapter.OnLoadMoreListener() {
//                @Override
//                public void onLoadMore() {
//                    page += 1;
//                    if (page <= pages) {
//                        presenter.loadMovies(jenis, page);
//                    }
//                }
//            });
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
