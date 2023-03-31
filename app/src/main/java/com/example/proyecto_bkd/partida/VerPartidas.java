package com.example.proyecto_bkd.partida;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.perfiles.activity_perfiles;
import com.example.proyecto_bkd.ranking.activity_ranking2;

public class VerPartidas extends AppCompatActivity {

    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    RecyclerView rcv;
    VerPartidasAdapter adap;
    Switch sMVerPartida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_partidas);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        sMVerPartida= findViewById(R.id.sMVerPartida);

        sMVerPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMVerPartida.isChecked()){
                    Login.mp.start();
                }else{
                    Login.mp.pause();
                }
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPartidas.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPartidas.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPartidas.this, activity_perfiles.class);
                startActivity(intent);
                finish();
            }
        });
        rcv = findViewById(R.id.recyclerPartidas);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        adap = new VerPartidasAdapter(ResumenPartidas.generador());
        rcv.setAdapter(adap);

        adap.setClickListener(new VerPartidasAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, ResumenPartidas resumen) {
                Intent intent = new Intent(VerPartidas.this, DetallePartida.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        Login.mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Login.mp.start();
    }
}