package com.example.proyecto_bkd.perfiles;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.partida.VerPartidas;
import com.example.proyecto_bkd.ranking.activity_ranking2;

import java.util.ArrayList;
import java.util.Arrays;

public class activity_perfiles extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Perfiles_adapter adaptador;
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
                Intent intent = new Intent(activity_perfiles.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_perfiles.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_perfiles.this, activity_perfiles.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        perfiles = new ArrayList<>(Arrays.asList(new Perfil().generarPerfiles()));

        adaptador = new Perfiles_adapter(perfiles);

        Log.d(TAG, String.valueOf(adaptador.getItemCount()));

        recyclerView.setAdapter(adaptador);

        adaptador.setClickListener((view, perfil) -> {
            Toast.makeText(activity_perfiles.this,"Pulsado "+ perfil.getNombre(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ActivityPerfil_Detalle.class);
            intent.putExtra("PERFIL",perfil);
            startActivity(intent);
        });
    }
}