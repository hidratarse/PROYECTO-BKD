package com.example.proyecto_bkd.partida.verPartida.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.actividades.PrincipalPartida;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.Ranking;
import com.example.proyecto_bkd.partida.PartidasViewModel;
import com.example.proyecto_bkd.partida.verPartida.VerPartidasAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerPartidas extends AppCompatActivity {
    private RecyclerView recyclerView;
    private VerPartidasAdapter adaptador;
    private PartidasViewModel vm;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    Switch sMVerPartida;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_partidas);
        overridePendingTransition(R.anim.aparece_derecha,R.anim.desaparece_izquierda);

        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        sMVerPartida= findViewById(R.id.sMVerPartida);
        recyclerView = findViewById(R.id.recyclerPartidas);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        email = currentUser.getEmail();

        sMVerPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMVerPartida.isChecked()){
                    Login.mp.start();
                    Login.music =true;
                }else {
                    Login.mp.pause();
                    Login.music = false;
                }
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPartidas.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPartidas.this, PrincipalPartida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPartidas.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        adaptador = new VerPartidasAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptador);

        vm = new ViewModelProvider(this).get(PartidasViewModel.class);
        vm.init();

        vm.getPartidas(email);

        vm.getPartidasLivedata().observe(this, (dato)-> {
            adaptador.setResults(dato);
        });

        adaptador.setClickListener((view, partidas) -> {
            Intent intent = new Intent(VerPartidas.this, DetallePartida.class);
            intent.putExtra("ID",partidas.getIdPartida());
            startActivity(intent);
            finish();
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
        if(Login.music){
            Login.mp.start();
        }else{
            Login.mp.pause();
            sMVerPartida.setChecked(false);
        }
    }
}