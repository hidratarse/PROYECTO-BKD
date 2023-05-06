package com.example.proyecto_bkd.ranking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.PartidasViewModel;
import com.example.proyecto_bkd.partida.actividades.PrincipalPartida;
import com.example.proyecto_bkd.perfiles.PerfilesViewModel;
import com.example.proyecto_bkd.perfiles.data.Perfil;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ranking extends AppCompatActivity {

    TextView tPartida, tPerfiles, tRanking;
    private RecyclerView recyclerView;
    private RankingAdapter adapter;
    private PerfilesViewModel vm;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking,bMaxPuntuacion, bWin;
    Switch sMRanking;
    String email;
    public static boolean ordenPuntos =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking2);

        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        tPartida = findViewById(R.id.tPartidas);
        tPerfiles = findViewById(R.id.tPerfiles);
        tRanking = findViewById(R.id.tRanking);
        bMaxPuntuacion = findViewById(R.id.bMaxPuntuacion);
        bWin = findViewById(R.id.bWin);
        recyclerView = findViewById(R.id.id_recycler_ranking2);
        Animation animEstandarte = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_estandarte);
        tPartida.setAnimation(animEstandarte);
        tPerfiles.setAnimation(animEstandarte);
        tRanking.setAnimation(animEstandarte);
        bImgPartidas.setAnimation(animEstandarte);
        bImgPerfiles.setAnimation(animEstandarte);
        bImgRanking.setAnimation(animEstandarte);


        sMRanking= findViewById(R.id.sMRanking);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        email = currentUser.getEmail();

        sMRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMRanking.isChecked()){
                    Login.mp.start();
                    Login.music =true;
                }else{
                    Login.mp.pause();
                    Login.music =false;

                }
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ranking.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ranking.this, PrincipalPartida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ranking.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });
        OrdenarPorPuntos();

        bMaxPuntuacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ordenPuntos =true;
                OrdenarPorPuntos();
            }
        });
        bWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ordenPuntos=false;
                OrdenarPorVictorias();
            }
        });
    }

    public void OrdenarPorPuntos(){
        adapter = new RankingAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        vm = new ViewModelProvider(this).get(PerfilesViewModel.class);
        vm.init();
        vm.getPerfiles(email);
        vm.getPerfilesLivedata().observe(this, perfiles -> {
            Collections.sort(perfiles, new PuntuacionComparator()); // ordenar la lista según el criterio de puntuación
            adapter.setResults(perfiles);
        });
        adapter.notifyDataSetChanged();
    }
    public void OrdenarPorVictorias(){
        adapter = new RankingAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        vm = new ViewModelProvider(this).get(PerfilesViewModel.class);
        vm.init();
        vm.getPerfiles(email);
        vm.getPerfilesLivedata().observe(this, perfiles -> {
            Collections.sort(perfiles, new VictoriasComparator()); // ordenar la lista según el criterio de puntuación
            adapter.setResults(perfiles);
        });
        adapter.notifyDataSetChanged();
    }

    public class PuntuacionComparator implements Comparator<Perfil> {
        @Override
        public int compare(Perfil p1, Perfil p2) {
            return Integer.compare(Integer.parseInt(p2.getMaxPuntuacion()), Integer.parseInt(p1.getMaxPuntuacion())); // ordena de mayor a menor
        }
    }
    public class VictoriasComparator implements Comparator<Perfil> {
        @Override
        public int compare(Perfil p1, Perfil p2) {
            return Integer.compare(Integer.parseInt(p2.getPartidasGanadas()), Integer.parseInt(p1.getPartidasGanadas())); // ordena de mayor a menor
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Login.mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!Login.music){
            sMRanking.setChecked(false);
            Login.mp.pause();
        }else{
            Login.mp.start();
        }
    }
}