package com.example.anamuslim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {


    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView = findViewById(R.id.logo);
        Animation animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.zoom);

        imageView.setAnimation(animation);

        new Handler().postDelayed(() -> {
            Intent i1 = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(i1);
            finish();
        }, 3000);
    }
}