package com.example.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashAct extends AppCompatActivity {

    Animation addsplash;
    ImageView applogo;
    TextView apptitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //load animation
        addsplash = AnimationUtils.loadAnimation(this, R.anim.addsplash);

        //load element
        applogo = findViewById(R.id.applogo);
        apptitle = findViewById(R.id.apptitle);

        //membuat timer
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //menuju halamaan lain
                Intent abc = new Intent(SplashAct.this, GetStartedAct.class);
                startActivity(abc);
                finish();
            }
        }, 2000); //2000 ms = 2 second
    }
}
