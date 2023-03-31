package com.example.proyecto_bkd.ranking;

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
import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.perfiles.Perfil;
import com.example.proyecto_bkd.perfiles.activity_perfiles;

import java.util.ArrayList;
import java.util.Arrays;

public class activity_ranking2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Ranking2_adapter adapter;
    private ArrayList<Perfil> perfiles;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    Switch sMRanking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking2);

        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);

        sMRanking= findViewById(R.id.sMRanking);

        sMRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMRanking.isChecked()){
                    Login.mp.start();
                }else{
                    Login.mp.pause();
                }
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ranking2.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ranking2.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ranking2.this, activity_perfiles.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.id_recycler_ranking2);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        perfiles = new ArrayList<>(Arrays.asList(new Perfil().generarPerfiles()));

        adapter = new Ranking2_adapter(perfiles);

        recyclerView.setAdapter(adapter);
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