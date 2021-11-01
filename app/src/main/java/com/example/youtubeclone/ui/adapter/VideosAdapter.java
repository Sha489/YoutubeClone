package com.example.youtubeclone.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youtubeclone.R;
import com.example.youtubeclone.model.Video;
import com.example.youtubeclone.ui.activity.VideoPlayerActivity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Video> videoArrayList;

    public VideosAdapter(Context context, ArrayList<Video> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(videoArrayList.get(position).getVideoImage()).into(holder.videoImage);
        Glide.with(context).load(videoArrayList.get(position).getChannelImage()).into(holder.channelImage);

        holder.videoTitle.setText(videoArrayList.get(position).getVideoTitle());
        holder.duration.setText(videoArrayList.get(position).getDuration());
        holder.otherDetails.setText(videoArrayList.get(position).getChannelName() + " . " + videoArrayList.get(position).getViews() + " views");

        String pattern = "\\W([\\w-]{9,})(\\W|$)";
        Pattern pattern2 = Pattern.compile(pattern);
        Matcher matcher2 = pattern2.matcher(videoArrayList.get(position).getVideoUrl());
        if (matcher2.find()) {
            videoArrayList.get(position).setVideoCode(matcher2.group(1));
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, VideoPlayerActivity.class);
            intent.putExtra("videoCode", videoArrayList.get(position).getVideoCode());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView channelImage;
        ImageView videoImage;
        TextView videoTitle, otherDetails, duration;
        public ViewHolder(View itemView) {
            super(itemView);
            channelImage = itemView.findViewById(R.id.channelImage);
            videoImage = itemView.findViewById(R.id.videoImage);
            videoTitle = itemView.findViewById(R.id.videoTitle);
            duration = itemView.findViewById(R.id.duration);
            otherDetails = itemView.findViewById(R.id.otherDetails);
        }
    }
}
