package com.example.proyecto_bkd.partida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.perfiles.activity_perfiles;
import com.example.proyecto_bkd.ranking.activity_ranking2;
import com.example.proyecto_bkd.resumenturno.ResumenTurnoAdapter;
import com.example.proyecto_bkd.resumenturno.activity_ResumenTurno;


public class Partida extends AppCompatActivity {
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    TextView verPartida, newGame, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        verPartida=findViewById(R.id.tVerPartidas);
        newGame=findViewById(R.id.tNewGame);
        salir=findViewById(R.id.tSalir);

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, activity_perfiles.class);
                startActivity(intent);
                finish();
            }
        });
        verPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, VerPartidas.class);
                startActivity(intent);
                finish();
            }
        });

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, activity_ResumenTurno.class);
                startActivity(intent);
                finish();
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}