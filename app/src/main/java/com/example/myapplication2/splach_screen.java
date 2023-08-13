package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class splach_screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);
        LinearLayout devloperBox = findViewById(R.id.devloperBox);
        ImageView icon = findViewById(R.id.icon_tic_tac);
        Intent intent = new Intent(splach_screen.this, MainActivity.class);
        Handler handler = new Handler();
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.splach_screen_animation);
        icon.startAnimation(animation);
        devloperBox.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fad_in));
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },5000);
    }
}