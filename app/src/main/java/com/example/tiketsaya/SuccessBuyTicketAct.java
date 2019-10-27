package com.example.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccessBuyTicketAct extends AppCompatActivity {

    Animation addsplash, bottomtotop, toptobottom;
    ImageView image_success_buy;
    TextView app_success_title, app_success_subtitle;
    Button btn_view_ticket, btn_my_dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_buy_ticket);

        //load animation
        addsplash = AnimationUtils.loadAnimation(this, R.anim.addsplash);
        toptobottom = AnimationUtils.loadAnimation(this, R.anim.toptobottom);
        bottomtotop = AnimationUtils.loadAnimation(this, R.anim.bottomtotop);

        //load element
        image_success_buy = findViewById(R.id.image_success_buy);
        app_success_title = findViewById(R.id.app_success_title);
        app_success_subtitle = findViewById(R.id.app_sucess_subtitle);
        btn_view_ticket = findViewById(R.id.btn_view_ticket);
        btn_my_dashboard = findViewById(R.id.btn_my_dashboard);

        //run animation
        image_success_buy.startAnimation(addsplash);
        app_success_title.startAnimation(toptobottom);
        app_success_subtitle.startAnimation(toptobottom);
        btn_view_ticket.startAnimation(bottomtotop);
        btn_my_dashboard.startAnimation(bottomtotop);
    }
}
