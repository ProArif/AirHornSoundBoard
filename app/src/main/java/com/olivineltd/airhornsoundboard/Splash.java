package com.olivineltd.airhornsoundboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.cunoraz.gifview.library.GifView;

public class Splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1500;
    GifView gifView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        gifView = (GifView)findViewById(R.id.gifview);
        gifView.play();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(Splash.this, Home.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
