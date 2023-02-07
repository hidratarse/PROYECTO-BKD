package com.example.proyecto_bkd.partida;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.proyecto_bkd.perfiles.Perfil_Detalle;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.ranking.activity_ranking2;

public class VerPartidas extends AppCompatActivity {

    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    RecyclerView rcv;
    VerPartidasAdapter adap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_partidas);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);

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
                Intent intent = new Intent(VerPartidas.this, Perfil_Detalle.class);
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
                Toast.makeText(VerPartidas.this,"Pulsado "+position +resumen.getJ1()+" "+resumen.getJ2(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}