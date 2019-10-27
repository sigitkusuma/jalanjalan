package com.example.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TicketCheckoutAct extends AppCompatActivity {

    LinearLayout btn_back;
    Button btn_pay, btn_tambah, btn_kurang;
    TextView text_jumlah_tiket, textmybalance, texttotalharga;
    ImageView noticeuang;
    Integer ValueJumlahTiket =1;
    Integer mybalance = 200;
    Integer Valuetotalharga = 0;
    Integer Valuehargatiket = 75;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_checkout);

        btn_back = findViewById(R.id.btn_back);
        btn_pay = findViewById(R.id.btn_pay);
        textmybalance = findViewById(R.id.textmybalance);
        texttotalharga = findViewById(R.id.texttotalharga);
        btn_tambah = findViewById(R.id.btn_tambah);
        btn_kurang = findViewById(R.id.btn_kurang);
        text_jumlah_tiket = findViewById(R.id.text_jumkah_tiket);
        noticeuang = findViewById(R.id.noticeuang);

        //setting value baru untuk beberapa komponen
        text_jumlah_tiket.setText(ValueJumlahTiket.toString());
        textmybalance.setText("US$ "+ mybalance+"");
        Valuetotalharga = Valuehargatiket * ValueJumlahTiket;
        texttotalharga.setText("US$ "+ Valuetotalharga+"");

        //secara default hide button mines
        btn_kurang.animate().alpha(0).setDuration(300).start();
        btn_kurang.setEnabled(false);
        noticeuang.setVisibility(View.GONE);

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValueJumlahTiket+=1;
                text_jumlah_tiket.setText(ValueJumlahTiket.toString());
                if (ValueJumlahTiket > 1 ){
                    btn_kurang.animate().alpha(1).setDuration(300).start();
                    btn_kurang.setEnabled(true);
                }
                Valuetotalharga = Valuehargatiket * ValueJumlahTiket;
                texttotalharga.setText("US$ "+ Valuetotalharga+"");
                if (Valuetotalharga > mybalance){
                    btn_pay.animate().translationY(250).alpha(0).setDuration(350).start();
                    btn_pay.setEnabled(false);
                    textmybalance.setTextColor(Color.parseColor("#D1206B"));
                    noticeuang.setVisibility(View.VISIBLE);

                }

            }
        });

        btn_kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValueJumlahTiket-=1;
                text_jumlah_tiket.setText(ValueJumlahTiket.toString());
                if (ValueJumlahTiket < 2 ){
                    btn_kurang.animate().alpha(0).setDuration(300).start();
                    btn_kurang.setEnabled(false);
                }
                Valuetotalharga = Valuehargatiket * ValueJumlahTiket;
                texttotalharga.setText("US$ "+ Valuetotalharga+"");
                if (Valuetotalharga < mybalance){
                    btn_pay.animate().translationY(0).alpha(1).setDuration(350).start();
                    btn_pay.setEnabled(true);
                    textmybalance.setTextColor(Color.parseColor("#203DD1"));
                    noticeuang.setVisibility(View.GONE);

                }

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosuccess = new Intent(TicketCheckoutAct.this, SuccessBuyTicketAct.class);
                startActivity(gotosuccess);
            }
        });
    }
}
