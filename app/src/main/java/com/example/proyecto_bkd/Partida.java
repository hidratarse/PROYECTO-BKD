package com.example.proyecto_bkd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class Partida extends AppCompatActivity {
    public static final int CODIGO_LOGIN =2;
    public static final int CODIGO_VUELTA_TEXTO = 3;
    public static final int CODIGO_REVERSO=4;
    public static final String MSG = "texto";
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        /*
        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, Ranking.class);
                intent.putExtra(MSG, "Hola");
                startActivity(intent);
                finish();
            }
        });
        */
        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, Partida.class);
                intent.putExtra(MSG, "Hola");
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, Perfil_Detalle.class);
                intent.putExtra(MSG, "Hola");
                startActivity(intent);
                finish();
            }
        });
    }
}