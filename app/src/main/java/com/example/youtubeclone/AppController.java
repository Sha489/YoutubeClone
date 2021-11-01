package com.example.youtubeclone;

import android.app.Application;
import android.content.ComponentCallbacks2;

import androidx.appcompat.app.AppCompatDelegate;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;


public class AppController extends Application implements ComponentCallbacks2 {

    private static AppController mInstance;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/youtube-sans-medium.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

}

