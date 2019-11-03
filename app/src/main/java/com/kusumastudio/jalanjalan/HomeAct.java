package com.kusumastudio.jalanjalan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.shapeofview.shapes.CircleView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeAct extends AppCompatActivity {

    LinearLayout btn_ticket_pantai, btn_ticket_bns,
            btn_ticket_coban, btn_ticket_paralayang,
            btn_ticket_jtp,btn_ticket_museum;
    CircleView btn_to_profile;
    ImageView photo_home_user;
    TextView nama_lengkap;
    TextView bio;
    TextView user_balance;

    DatabaseReference reference;
    String USERNAME_KEY = "username_key";
    String username_key = "";
    String username_key_new ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getUsernameLocal();
        btn_ticket_pantai = findViewById(R.id.btn_ticket_pantai);
        btn_ticket_bns = findViewById(R.id.btn_ticket_bns);
        btn_ticket_coban = findViewById(R.id.btn_ticket_coban);
        btn_ticket_paralayang = findViewById(R.id.btn_ticket_paralayang);
        btn_ticket_jtp = findViewById(R.id.btn_ticket_jtp);
        btn_ticket_museum = findViewById(R.id.btn_ticket_museum);

        btn_to_profile = findViewById(R.id.btn_to_profile);
        photo_home_user = findViewById(R.id.photo_home_user);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        bio = findViewById(R.id.bio);
        user_balance = findViewById(R.id.user_balance);

        reference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(username_key_new);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());
                user_balance.setText("US$" + dataSnapshot.child("user_balance").getValue().toString());

                Picasso.with(HomeAct.this)
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit()
                        .into(photo_home_user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_ticket_pantai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotopisaticket= new Intent(HomeAct.this, TicketDetailAct.class);
                //meletakan data kepada intent
                gotopisaticket.putExtra("jenis_tiket", "Pantai");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_bns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotopisaticket= new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket", "BNS");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_coban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotopisaticket= new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket", "Coban");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_paralayang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotopisaticket= new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket", "Paralayang");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_jtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotopisaticket= new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket", "JTP");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_museum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotopisaticket= new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket", "Museum");
                startActivity(gotopisaticket);
            }
        });

        btn_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoprofile = new Intent(HomeAct.this, MyProfileAct.class);
                startActivity(gotoprofile);
            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
