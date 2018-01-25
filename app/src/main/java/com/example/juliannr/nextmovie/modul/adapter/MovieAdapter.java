package com.example.juliannr.nextmovie.modul.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.juliannr.nextmovie.R;
import com.example.juliannr.nextmovie.model.Movie;
import com.example.juliannr.nextmovie.modul.viewholder.MovieViewHolder;
import com.example.juliannr.nextmovie.utility.Constant;

import java.util.ArrayList;
import java.util.List;

import static com.example.juliannr.nextmovie.utility.Constant.Api.IMAGE_PATH;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<Movie> movies;
    private Context context;
    private SetOnItemClickListener setOnItemClickListener;

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    public void addAll(List<Movie> movies){
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public void resetData(){
        movies.clear();
        notifyDataSetChanged();
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
        holder.getRating().setText(Constant.Api.RATING + movie.getRating());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface SetOnItemClickListener{
        public void onClick(View view, int position);
    }
}
