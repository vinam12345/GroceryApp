package com.example.groceryapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    Animation animation;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image=findViewById(R.id.image);
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.enter_from_left);
        image.startAnimation(animation);

//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.together);
//        animation.setInterpolator(new LinearInterpolator());
//        animation.setRepeatCount(Animation.INFINITE);
//        animation.setDuration(1000);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                overridePendingTransition(R.anim.enetr_from_right,R.anim.exit_to_left);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}