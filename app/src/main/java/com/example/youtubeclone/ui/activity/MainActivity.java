package com.example.youtubeclone.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.youtubeclone.R;
import com.example.youtubeclone.ui.adapter.ExploreAdapter;
import com.example.youtubeclone.ui.adapter.VideosAdapter;
import com.example.youtubeclone.model.Video;
import com.example.youtubeclone.ui.fragment.HomeFragment;
import com.example.youtubeclone.ui.fragment.ShortsFragment;
import com.example.youtubeclone.ui.fragment.UploadFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {

    public AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        setUpBotttomNavigation();
    }

    public void setUpBotttomNavigation()
    {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home, R.drawable.ic_baseline_home_24, R.color.red);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.shorts, R.drawable.flim, R.color.red);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.upload, R.drawable.ic_baseline_cloud_upload_24, R.color.red);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        bottomNavigation.setAccentColor(ContextCompat.getColor(getApplicationContext(), R.color.red));

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if(position == 0) {
                    replaceWithFragment(new HomeFragment());
                } else if(position == 1) {
                    replaceWithFragment(new UploadFragment());
                }
                return true;
            }
        });
        replaceWithFragment(new HomeFragment());
    }

    public void replaceWithFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
    }
}