package com.example.youtubeclone.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeclone.R;
import com.example.youtubeclone.model.Video;
import com.example.youtubeclone.ui.activity.MainActivity;
import com.example.youtubeclone.ui.adapter.ExploreAdapter;
import com.example.youtubeclone.ui.adapter.VideosAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UploadFragment extends Fragment {

    RecyclerView exploreRecycler;
    RecyclerView videoRecycler;
    ExploreAdapter exploreAdapter;
    VideosAdapter videosAdapter;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    MainActivity activity;

    EditText videoTitle, youtubeVideoUrl, imageUrl, channelName, duration, channelImage;
    Button uploadVideo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.upload_fragment, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = (MainActivity) getActivity();
        firebaseDatabase = FirebaseDatabase.getInstance();

        youtubeVideoUrl = view.findViewById(R.id.youtubeVideoUrl);
        videoTitle = view.findViewById(R.id.videoTitle);
        imageUrl = view.findViewById(R.id.imageUrl);
        channelName = view.findViewById(R.id.channelName);
        duration = view.findViewById(R.id.duration);
        channelImage = view.findViewById(R.id.channelImage);
        uploadVideo = view.findViewById(R.id.uploadVideo);

        uploadVideo.setOnClickListener(v -> {
            databaseReference = FirebaseDatabase.getInstance().getReference("video").child("video_" + (HomeFragment.arrayCount + 1));
            Map<String, Object> map = new HashMap<>();
            map.put("videoTitle", videoTitle.getText().toString());
            map.put("videoUrl", youtubeVideoUrl.getText().toString());
            map.put("videoImage", imageUrl.getText().toString());
            map.put("channelName", channelName.getText().toString());
            map.put("channelImage", channelImage.getText().toString());
            map.put("views", 0);
            map.put("postedTime", Calendar.getInstance().getTime());
            map.put("duration", duration.getText().toString());
            databaseReference.updateChildren(map);
            activity.bottomNavigation.setCurrentItem(0);
        });


    }
}
