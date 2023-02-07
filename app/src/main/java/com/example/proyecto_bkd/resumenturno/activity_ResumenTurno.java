package com.example.proyecto_bkd.resumenturno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.perfiles.activity_perfiles;
import com.example.proyecto_bkd.ranking.activity_ranking2;

import java.util.ArrayList;

public class activity_ResumenTurno extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Formulario> formularios;
    ResumenTurnoAdapter adapter;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_turno);

        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ResumenTurno.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ResumenTurno.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ResumenTurno.this, activity_perfiles.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.id_rv_resumenTurnos);

        formularios = Formulario.generarFormularios();

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ResumenTurnoAdapter(formularios);

        recyclerView.setAdapter(adapter);

    }
}