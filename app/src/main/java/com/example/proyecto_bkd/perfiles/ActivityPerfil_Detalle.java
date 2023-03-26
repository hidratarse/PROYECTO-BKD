package com.example.proyecto_bkd.perfiles;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.ranking.activity_ranking2;

public class ActivityPerfil_Detalle extends AppCompatActivity {
    EditText nombre;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    TextView tCancela, tModificar;
    Switch sMPerfilDetalle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_detalle);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        nombre = findViewById(R.id.eNombre);
        tCancela=findViewById(R.id.tCancela);
        tModificar=findViewById(R.id.tModificar);

        sMPerfilDetalle= findViewById(R.id.sMPerfilDetalle);

        sMPerfilDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMPerfilDetalle.isChecked()){
                    Login.mp.start();
                }else{
                    Login.mp.pause();
                }
            }
        });


        tModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPerfil_Detalle.this, activity_perfiles.class);
                startActivity(intent);
                finish();
            }
        });

        tCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPerfil_Detalle.this, activity_perfiles.class);
                startActivity(intent);
                finish();
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPerfil_Detalle.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPerfil_Detalle.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPerfil_Detalle.this, activity_perfiles.class);
                startActivity(intent);
                finish();
            }
        });

        Perfil perfil = (Perfil) getIntent().getSerializableExtra("PERFIL");

        nombre.setText(perfil.getNombre());
    }
}