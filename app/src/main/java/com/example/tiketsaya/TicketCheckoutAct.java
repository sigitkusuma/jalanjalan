package com.example.tiketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class TicketCheckoutAct extends AppCompatActivity {

    LinearLayout btn_back;
    Button btn_pay, btn_tambah, btn_kurang;
    TextView text_jumlah_tiket, textmybalance, texttotalharga, nama_wisata, lokasi, ketentuan;
    ImageView noticeuang;
    Integer sisa_balance = 0;
    Integer ValueJumlahTiket =1;
    Integer mybalance = 0;
    Integer Valuetotalharga = 0;
    Integer Valuehargatiket = 0;
    DatabaseReference reference, reference2, reference3, reference4;

    String USERNAME_KEY = "username_key";
    String username_key = "";
    String username_key_new ="";

    String date_wisata ="";
    String time_wisata ="";

    //generate no int secara random
    //karena kita ingin membuat transaksi secara unik
    Integer nomor_transaksi = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_checkout);

        getUsernameLocal();

        //mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String jenis_ticket_baru = bundle.getString("jenis_tiket");

        btn_back = findViewById(R.id.btn_back);
        btn_pay = findViewById(R.id.btn_pay);
        textmybalance = findViewById(R.id.textmybalance);
        texttotalharga = findViewById(R.id.texttotalharga);
        btn_tambah = findViewById(R.id.btn_tambah);
        btn_kurang = findViewById(R.id.btn_kurang);
        text_jumlah_tiket = findViewById(R.id.text_jumkah_tiket);
        noticeuang = findViewById(R.id.noticeuang);
        nama_wisata = findViewById(R.id.nama_wisata);
        lokasi = findViewById(R.id.lokasi);
        ketentuan = findViewById(R.id.ketentuan);

        //setting value baru untuk beberapa komponen
        text_jumlah_tiket.setText(ValueJumlahTiket.toString());

        //secara default hide button mines
        btn_kurang.animate().alpha(0).setDuration(300).start();
        btn_kurang.setEnabled(false);
        noticeuang.setVisibility(View.GONE);

        //mengambil data user dari database
        reference2 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mybalance = Integer.valueOf(dataSnapshot.child("user_balance").getValue().toString());
                textmybalance.setText("US$ "+ mybalance+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //mengambil data dari database berdasarkan intent
        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(jenis_ticket_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //menimpa data yg ada dengan data dari database
                nama_wisata.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                lokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                ketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());
                date_wisata = dataSnapshot.child("date_wisata").getValue().toString();
                time_wisata = dataSnapshot.child("time_wisata").getValue().toString();
                Valuehargatiket = Integer.valueOf(dataSnapshot.child("harga_tiket").getValue().toString());
                Valuetotalharga = Valuehargatiket * ValueJumlahTiket;
                texttotalharga.setText("US$ "+ Valuetotalharga+"");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //menyimpan data user kepada firebase dan membuat table baru "mytcket"
                reference3 = FirebaseDatabase.getInstance()
                        .getReference().child("MyTickets")
                        .child(username_key_new).child(nama_wisata.getText().toString() +nomor_transaksi);
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference3.getRef().child("id_tiket").setValue(nama_wisata.getText().toString() +nomor_transaksi);
                        reference3.getRef().child("nama_wisata").setValue(nama_wisata.getText().toString());
                        reference3.getRef().child("lokasi").setValue(lokasi.getText().toString());
                        reference3.getRef().child("ketentuan").setValue(ketentuan.getText().toString());
                        reference3.getRef().child("date_wisata").setValue(date_wisata);
                        reference3.getRef().child("time_wisata").setValue(time_wisata);
                        reference3.getRef().child("jumlah_tiket").setValue(ValueJumlahTiket);

                        Intent gotosuccess = new Intent(TicketCheckoutAct.this, SuccessBuyTicketAct.class);
                        startActivity(gotosuccess);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                //mengambil data user dari database
                reference4 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        sisa_balance = mybalance - Valuetotalharga;
                        reference4.getRef().child("user_balance").setValue(sisa_balance);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
