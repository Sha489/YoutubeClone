package com.example.youtubeclone.model;

public class Shorts {

    String videoTitle, videoUrl, channelName, channelImage;

    public Shorts(){

    }

    public Shorts(String videoTitle, String videoUrl, String channelName, String channelImage){
        this.videoTitle = videoTitle;
        this.videoUrl = videoUrl;
        this.channelName = channelName;
        this.channelImage = channelImage;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelImage() {
        return channelImage;
    }

    public void setChannelImage(String channelImage) {
        this.channelImage = channelImage;
    }
}
