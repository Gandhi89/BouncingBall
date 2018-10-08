package com.example.shivamgandhi.bouncingball.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.shivamgandhi.bouncingball.R;

public class HomeActivity extends AppCompatActivity {

    ImageView bouncinBallIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bouncinBallIv = findViewById(R.id.imageView);

        // Load the animation
        Animation animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide);

        // Start the animation
        bouncinBallIv.startAnimation(animSlide);
    }
}
