package com.example.RPP_5_Fedodeev.likes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlab5.R;

public class RecyclerAdapterForLikes extends RecyclerView.Adapter<RecyclerAdapterForLikes.ViewHolder> {
    LayoutInflater inflater;

    public RecyclerAdapterForLikes(Context context) {
        inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.liked_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.webView.getSettings().setDisplayZoomControls(false);
        holder.webView.getSettings().setLoadWithOverviewMode(true);
        holder.webView.getSettings().setUseWideViewPort(true);
        holder.webView.loadUrl(DataLike.createInstance().getUrls().get(position));
    }

    @Override
    public int getItemCount() {
        return DataLike.createInstance().getUrls().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final WebView webView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            webView = itemView.findViewById(R.id.liked_view);
        }
    }
}
