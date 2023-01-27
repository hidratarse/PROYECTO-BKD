package com.example.proyecto_bkd.partida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.proyecto_bkd.Perfil_Detalle;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.activity_perfiles;


public class Partida extends AppCompatActivity {
    public static final int CODIGO_LOGIN =2;
    public static final int CODIGO_VUELTA_TEXTO = 3;
    public static final int CODIGO_REVERSO=4;
    public static final String MSG = "texto";
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    TextView verPartida, newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        verPartida=findViewById(R.id.tVerPartidas);
        newGame=findViewById(R.id.tNewGame);
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
                Intent intent = new Intent(Partida.this, activity_perfiles.class);
                intent.putExtra(MSG, "Hola");
                startActivity(intent);
                finish();
            }
        });
        verPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, VerPartidas.class);
                intent.putExtra(MSG, "Hola");
                startActivity(intent);
                finish();
            }
        });
    }
}