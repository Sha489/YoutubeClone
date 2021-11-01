package com.example.youtubeclone.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youtubeclone.R;
import com.example.youtubeclone.model.Shorts;
import com.example.youtubeclone.model.Video;
import com.example.youtubeclone.ui.activity.MainActivity;
import com.example.youtubeclone.ui.activity.VideoPlayerActivity;
import com.example.youtubeclone.ui.fragment.ShortsFragment;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShortsAdapter extends RecyclerView.Adapter<ShortsAdapter.ViewHolder> {

    private MainActivity context;
    private ArrayList<Shorts> shortArrayList;
    String PLAYER_TAG = "player";
    ViewHolder currentViewHolder;
    int pos;
    public static SimpleExoPlayer player;

    public ShortsAdapter(MainActivity context, ArrayList<Shorts> shortArrayList) {
        this.context = context;
        this.shortArrayList = shortArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_short, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Shorts shorts = shortArrayList.get(position);
        holder.title.setText(shorts.getVideoTitle());
        holder.channelName.setText(shorts.getChannelName());
        Glide.with(context).load(shorts.getChannelImage()).into(holder.channelImage);

        player = new SimpleExoPlayer.Builder(context).build();
        player.setVideoSurfaceView(holder.playerView);

        MediaItem mediaItem = MediaItem.fromUri(shorts.getVideoUrl());
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    @Override
    public int getItemCount() {
        return shortArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView channelName;
        ImageView channelImage;
        SurfaceView playerView;
        public ViewHolder(View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.playerView);
            title = itemView.findViewById(R.id.title);
            channelName = itemView.findViewById(R.id.channelName);
            channelImage = itemView.findViewById(R.id.channelImage);
        }
    }
}
