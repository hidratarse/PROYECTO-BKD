package com.example.proyecto_bkd.ranking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.actividades.PantallaPartida;
import com.example.proyecto_bkd.perfiles.data.Perfil;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity {

    TextView tPartida, tPerfiles, tRanking;
    private RecyclerView recyclerView;
    private RankingAdapter adapter;
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
        tPartida = findViewById(R.id.tPartidas);
        tPerfiles = findViewById(R.id.tPerfiles);
        tRanking = findViewById(R.id.tRanking);

        Animation animEstandarte = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_estandarte);
        tPartida.setAnimation(animEstandarte);
        tPerfiles.setAnimation(animEstandarte);
        tRanking.setAnimation(animEstandarte);
        bImgPartidas.setAnimation(animEstandarte);
        bImgPerfiles.setAnimation(animEstandarte);
        bImgRanking.setAnimation(animEstandarte);

        sMRanking= findViewById(R.id.sMRanking);


        sMRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMRanking.isChecked()){
                    Login.mp.start();
                    Login.music =true;
                }else{
                    Login.mp.pause();
                    Login.music =false;

                }
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ranking.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ranking.this, PantallaPartida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ranking.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.id_recycler_ranking2);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        if(!Login.music){
            sMRanking.setChecked(false);
            Login.mp.pause();
        }else{
            Login.mp.start();
        }
    }
}