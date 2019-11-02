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

public class GetStartedAct extends AppCompatActivity {

    Button btn_sign_in; Button btn_new_account_create;
    Animation toptobottom, bottomtotop;
    ImageView emblem_app;
    TextView emblem_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        //load animation
        toptobottom = AnimationUtils.loadAnimation(this, R.anim.toptobottom);
        bottomtotop = AnimationUtils.loadAnimation(this, R.anim.bottomtotop);

        //load element
        emblem_app = findViewById(R.id.emblem_app);
        emblem_text = findViewById(R.id.emblem_text);
        btn_sign_in = findViewById(R.id.btn_sign_in);
        btn_new_account_create = findViewById(R.id.btn_new_account_create);

        //run animation
        emblem_app.startAnimation(toptobottom);
        emblem_text.startAnimation(toptobottom);
        btn_sign_in.startAnimation(bottomtotop);
        btn_new_account_create.startAnimation(bottomtotop);

        //action
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotosign = new Intent(GetStartedAct.this, SignInAct.class);
                startActivity(gotosign);
            }
        });

        btn_new_account_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoregister = new Intent(GetStartedAct.this, RegisterOneAct.class);
                startActivity(gotoregister);
            }
        });
    }
}
