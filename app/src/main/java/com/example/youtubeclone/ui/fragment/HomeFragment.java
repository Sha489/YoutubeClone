package com.example.youtubeclone.ui.fragment;

import static androidx.core.content.ContextCompat.checkSelfPermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeclone.R;
import com.example.youtubeclone.model.Video;
import com.example.youtubeclone.ui.adapter.ExploreAdapter;
import com.example.youtubeclone.ui.adapter.VideosAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    RecyclerView exploreRecycler;
    RecyclerView videoRecycler;
    ExploreAdapter exploreAdapter;
    VideosAdapter videosAdapter;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    public static int arrayCount;
    ArrayList<Video> videoArrayList = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        exploreRecycler = view.findViewById(R.id.exploreRecycler);
        videoRecycler = view.findViewById(R.id.videoRecycler);
        progressBar = view.findViewById(R.id.progressBar);

        firebaseDatabase = FirebaseDatabase.getInstance();
        setUpAdapter();
        fetchDataFromDatabase();
    }

    public void setUpAdapter() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("All");
        arrayList.add("Mixes");
        arrayList.add("Music");
        arrayList.add("BTS");
        arrayList.add("Cakes");
        arrayList.add("Motivation");
        arrayList.add("Cinema");
        arrayList.add("Comedies");
        arrayList.add("Street Food");
        arrayList.add("Conversation");

        exploreAdapter = new ExploreAdapter(getActivity(), arrayList);
        exploreRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        exploreRecycler.setAdapter(exploreAdapter);
    }

    public void setUpVideoAdapter() {
        videosAdapter = new VideosAdapter(getActivity(), videoArrayList);
        videoRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        videoRecycler.setAdapter(videosAdapter);
    }

    public void fetchDataFromDatabase() {
        progressBar.setVisibility(View.VISIBLE);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("video");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
//                Post post = dataSnapshot.getValue(Post.class);
                System.out.println(dataSnapshot.getValue());

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Video video = new Video();
                    video.setVideoTitle(ds.child("videoTitle").getValue(String.class));
                    video.setVideoUrl(ds.child("videoUrl").getValue(String.class));
                    video.setVideoImage(ds.child("videoImage").getValue(String.class));
                    video.setChannelName(ds.child("channelName").getValue(String.class));
                    video.setDuration(ds.child("duration").getValue(String.class));
                    video.setChannelImage(ds.child("channelImage").getValue(String.class));
//                    video.setPostedTime(ds.child("postedTime").getValue(String.class));
                    video.setViews(ds.child("views").getValue(Integer.class));
                    videoArrayList.add(video);
                }
                arrayCount = videoArrayList.size();
                setUpVideoAdapter();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
