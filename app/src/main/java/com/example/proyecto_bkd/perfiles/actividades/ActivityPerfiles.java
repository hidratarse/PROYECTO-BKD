package com.example.proyecto_bkd.perfiles.actividades;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.perfiles.data.Perfil;
import com.example.proyecto_bkd.perfiles.PerfilesAdapter;
import com.example.proyecto_bkd.ranking.activity_ranking2;

import java.util.ArrayList;
import java.util.Arrays;

public class ActivityPerfiles extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PerfilesAdapter adaptador;
    private ArrayList<Perfil> perfiles;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    Switch sMPerfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiles);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        recyclerView = findViewById(R.id.recycled_perfiles);
        sMPerfiles= findViewById(R.id.sMPerfiles);

        sMPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMPerfiles.isChecked()){
                    Login.mp.start();
                }else{
                    Login.mp.pause();
                }
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPerfiles.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPerfiles.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPerfiles.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        perfiles = new ArrayList<>(Arrays.asList(new Perfil().generarPerfiles()));

        adaptador = new PerfilesAdapter(perfiles);

        Log.d(TAG, String.valueOf(adaptador.getItemCount()));

        recyclerView.setAdapter(adaptador);

        adaptador.setClickListener((view, perfil) -> {
            Toast.makeText(ActivityPerfiles.this,"Pulsado "+ perfil.getEmail(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ActivityDetallePerfil.class);
            intent.putExtra("PERFIL",perfil);
            startActivity(intent);
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