package com.example.juliannr.nextmovie.modul.viewholder;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juliannr.nextmovie.R;
import com.example.juliannr.nextmovie.modul.adapter.MovieAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private Context context;
    @BindView(R.id.root_layout)
    ConstraintLayout rootLayout;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.rating)
    TextView rating;
    private MovieAdapter callback;

    public MovieViewHolder(View itemView, Context context, final MovieAdapter callback) {
        super(itemView);
        this.context = context;
        this.callback = callback;
        ButterKnife.bind(this, itemView);
        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback.getSetOnItemClickListener() != null)
                    callback.getSetOnItemClickListener().onClick(view, getAdapterPosition());
            }
        });
    }

    public TextView getRating() {
        return rating;
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getTitle() {
        return title;
    }
}
