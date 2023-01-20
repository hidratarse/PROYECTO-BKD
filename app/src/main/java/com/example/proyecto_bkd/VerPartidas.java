package com.example.proyecto_bkd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class VerPartidas extends AppCompatActivity {
    public static final String MSG = "texto";
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_partidas);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        /*
        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPartidas.this, Ranking.class);
                intent.putExtra(MSG, "Hola");
                startActivity(intent);
                finish();
            }
        });
        */
        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPartidas.this, Partida.class);
                intent.putExtra(MSG, "Hola");
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPartidas.this, Perfil_Detalle.class);
                intent.putExtra(MSG, "Hola");
                startActivity(intent);
                finish();
            }
        });
    }
}