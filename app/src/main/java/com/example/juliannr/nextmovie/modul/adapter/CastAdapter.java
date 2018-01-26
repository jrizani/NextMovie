package com.example.juliannr.nextmovie.modul.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.juliannr.nextmovie.R;
import com.example.juliannr.nextmovie.model.Cast;
import com.example.juliannr.nextmovie.utility.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Julian Noor Rizani on 25/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {
    private List<Cast> casts;
    private Context context;

    public CastAdapter(Context context) {
        casts = new ArrayList<>();
        this.context = context;
    }

    public void setData(List<Cast> casts){
        this.casts.addAll(casts);
        notifyDataSetChanged();
    }

    public void clearData(){
        this.casts.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cast cast = casts.get(position);
        holder.character.setText(cast.getCharacter());
        holder.name.setText(cast.getName());
        Glide.with(context).load(Constant.Api.IMAGE_PATH + cast.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cast_character)
        TextView character;
        @BindView(R.id.cast_name)
        TextView name;
        @BindView(R.id.cast_image)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
