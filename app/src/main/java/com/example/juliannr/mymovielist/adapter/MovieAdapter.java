package com.example.juliannr.mymovielist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.juliannr.mymovielist.R;
import com.example.juliannr.mymovielist.model.Movie;
import com.example.juliannr.mymovielist.viewholder.MovieViewHolder;

import java.util.List;

import static com.example.juliannr.mymovielist.utility.Constant.Api.IMAGE_PATH;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<Movie> movies;
    private Context context;
    private SetOnItemClickListener setOnItemClickListener;

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    public void setSetOnItemClickListener(SetOnItemClickListener setOnItemClickListener) {
        this.setOnItemClickListener = setOnItemClickListener;
    }

    public SetOnItemClickListener getSetOnItemClickListener() {
        return setOnItemClickListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view, parent.getContext(), this);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Glide.with(context).load(IMAGE_PATH + movie.getImage()).into(holder.getImage());
        holder.getTitle().setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface SetOnItemClickListener{
        public void onClick(View view, int position);
    }
}
