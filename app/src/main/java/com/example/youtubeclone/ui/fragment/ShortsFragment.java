package com.example.youtubeclone.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.youtubeclone.R;
import com.example.youtubeclone.model.Shorts;
import com.example.youtubeclone.ui.activity.MainActivity;
import com.example.youtubeclone.ui.adapter.ShortsAdapter;
import com.example.youtubeclone.ui.adapter.VideosAdapter;
import com.google.android.exoplayer2.ExoPlayer;

import java.util.ArrayList;

public class ShortsFragment extends Fragment {

    ViewPager2 verticalViewPager;
    ShortsAdapter shortsAdapter;
    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shorts, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = (MainActivity) getActivity();
        verticalViewPager = view.findViewById(R.id.verticalViewPager);
        setUpAdapter();
    }

    public void setUpAdapter() {
        ArrayList<Shorts> shortsArrayList = new ArrayList<>();
        shortsArrayList.add(new Shorts("Magic Time #Shorts", "https://images.all-free-download.com/footage_preview/webm/apricot_flower_82.webm", "That little Puff", "https://cdn.pixabay.com/photo/2021/06/24/12/35/sunset-6361127_960_720.jpg"));
        shortsArrayList.add(new Shorts("Melting Chocolate Cake #Shorts", "https://images.all-free-download.com/footage_preview/mp4/traffic_lights_city_street_night_1059.mp4", "Cake Zone", "https://cdn.pixabay.com/photo/2021/09/20/06/55/spaghetti-6639970_960_720.jpg"));
        shortsArrayList.add(new Shorts("The blind box opening challenge is so exciting #Shorts", "https://images.all-free-download.com/footage_preview/mp4/neptune_planetary_system_planet_693.mp4", "Funny Family", "https://cdn.pixabay.com/photo/2021/09/19/12/19/animal-6637774_960_720.jpg"));
        shortsArrayList.add(new Shorts("Best Oddly Satisfying Video #Shorts", "https://images.all-free-download.com/footage_preview/mp4/film_leader_movie_credits_red_veil_810.mp4 ", "Solacing", "https://cdn.pixabay.com/photo/2021/09/14/14/46/cologne-6624212_960_720.jpg"));

        shortsAdapter = new ShortsAdapter(activity, shortsArrayList);
        verticalViewPager.setAdapter(shortsAdapter);
        verticalViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
//                if(state == ViewPager2.SCROLL_STATE_IDLE) {
//
//                    if(verticalViewPager.canScrollVertically(1)) {
//                        shortsAdapter.player.play();
//                    } else {
//                        shortsAdapter.player.stop();
//                    }
//                } else if(state == ViewPager2.SCROLL_STATE_DRAGGING) {
//                    shortsAdapter.player.stop();
//                }
            }
        });
    }
}
