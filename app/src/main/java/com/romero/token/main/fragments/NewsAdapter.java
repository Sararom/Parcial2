package com.romero.token.main.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.romero.token.Main2;
import com.romero.token.MainActivity;
import com.romero.token.R;
import com.romero.token.main.data.model.New;
import com.romero.token.main.data.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    ArrayList<New> news;
    private int adapterPosition;

    public NewsAdapter(ArrayList news) {
        this.news = news;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.title.setText(news.get(position).getTitle());
        Picasso.with(Main2.getmain2Context()).load(news.get(position).getCoverImage()).into(holder.img);
        holder.game.setText(news.get(position).getGame());

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img;
        public TextView game;

        public ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.titleTv);
            img = itemView.findViewById(R.id.ImageVid);
            game= itemView.findViewById(R.id.gameTv);
        }
    }
}
