package com.example.RPP_5_Fedodeev;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.RPP_5_Fedodeev.url.UrlData;
import com.example.androidlab5.R;
import com.example.RPP_5_Fedodeev.likes.DataLike;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    LayoutInflater inflater;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if(position == UrlData.getInstance().getUrls().size() - 1) {
            UrlData.getInstance().load();
        }

        holder.like.setBackgroundColor(Color.WHITE);
        holder.dislike.setBackgroundColor(Color.WHITE);
        holder.view.getSettings().setDisplayZoomControls(false);
        holder.view.getSettings().setLoadWithOverviewMode(true);
        holder.view.getSettings().setUseWideViewPort(true);
        final String url = UrlData.getInstance().getUrls().get(position);
        switch (UrlData.getInstance().getLiked(url)) {
            case -1:
                holder.like.setBackgroundColor(Color.WHITE);
                holder.dislike.setBackgroundColor(Color.RED);
                break;
            case 0:
                holder.like.setBackgroundColor(Color.WHITE);
                holder.dislike.setBackgroundColor(Color.WHITE);
                break;
            case 1:
                holder.like.setBackgroundColor(Color.GREEN);
                holder.dislike.setBackgroundColor(Color.WHITE);
                break;
        }
        holder.view.loadUrl(url);
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                holder.like.setBackgroundColor(Color.GREEN);
                holder.dislike.setBackgroundColor(Color.WHITE);
                UrlData.getInstance().setLiked(url);
                DataLike.createInstance().addUrl(holder.view.getUrl());
            }
        });
        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                holder.dislike.setBackgroundColor(Color.RED);
                holder.like.setBackgroundColor(Color.WHITE);
                UrlData.getInstance().setDisliked(url);
                DataLike.createInstance().deleteUrl(holder.view.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return UrlData.getInstance().getUrls().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final WebView view;
        final Button like;
        final Button dislike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.web_view);
            like = itemView.findViewById(R.id.like);
            dislike = itemView.findViewById(R.id.dislike);
        }
    }
    public RecyclerAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }
}
