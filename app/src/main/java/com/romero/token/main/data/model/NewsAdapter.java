package com.romero.token.main.data.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.romero.token.Main2;
import com.romero.token.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    List<New> news;
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public NewsAdapter(List news) {
        this.news = news;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv,parent,false);
        ViewHolder evh = new NewsAdapter.ViewHolder(view,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        if(news !=null) {
            holder.title.setText(news.get(position).getTitle());
            Picasso.with(Main2.getmain2Context()).load(news.get(position).getCoverImage()).into(holder.img);
            holder.game.setText(news.get(position).getGame());
        }else {
            holder.title.setText("No News");
        }

    }

    @Override
    public int getItemCount() {
        if (news != null)
            return news.size();
        else return 0;
    }

    public void setNews(List<New> news) {
        this.news = news;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img;
        public TextView game;

        public ViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            title = itemView.findViewById(R.id.titleTv);
            img = itemView.findViewById(R.id.ImageVid);
            game= itemView.findViewById(R.id.gameTv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
