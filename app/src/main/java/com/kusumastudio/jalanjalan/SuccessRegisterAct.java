package com.kusumastudio.jalanjalan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccessRegisterAct extends AppCompatActivity {

    Button btn_explore;
    Animation bottomtotop, toptobottom;
    ImageView image_success_register;
    TextView app_title, app_subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);

        //load animation
        toptobottom = AnimationUtils.loadAnimation(this, R.anim.toptobottom);
        bottomtotop = AnimationUtils.loadAnimation(this, R.anim.bottomtotop);

        //load element
        image_success_register = findViewById(R.id.image_success_register);
        app_title = findViewById(R.id.app_title);
        app_subtitle = findViewById(R.id.app_subtitle);
        btn_explore = findViewById(R.id.btn_explore);

        //run animation
        image_success_register.startAnimation(toptobottom);
        app_title.startAnimation(toptobottom);
        app_subtitle.startAnimation(toptobottom);
        btn_explore.startAnimation(bottomtotop);


        btn_explore = findViewById(R.id.btn_explore);
        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotohome = new Intent(SuccessRegisterAct.this, HomeAct.class);
                startActivity(gotohome);
            }
        });
    }
}
