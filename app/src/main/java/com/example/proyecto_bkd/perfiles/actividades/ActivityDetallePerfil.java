package com.example.proyecto_bkd.perfiles.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.perfiles.PerfilesViewModel;
import com.example.proyecto_bkd.perfiles.data.Perfil;
import com.example.proyecto_bkd.ranking.activity_ranking2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityDetallePerfil extends AppCompatActivity {
    EditText nombre;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    TextView tCancela, tModificar, tInsertar;
    Switch sMPerfilDetalle;
    PerfilesViewModel vm;
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
        tInsertar = findViewById(R.id.tInsertar);
        sMPerfilDetalle= findViewById(R.id.sMPerfilDetalle);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = currentUser.getEmail();

        vm = new ViewModelProvider(this).get(PerfilesViewModel.class);
        vm.init();

        boolean editando = getIntent().getBooleanExtra("PERFIL", false);

        if (editando){
            tModificar.setVisibility(View.VISIBLE);
            tInsertar.setVisibility(View.INVISIBLE);
        }else {
            tModificar.setVisibility(View.INVISIBLE);
            tInsertar.setVisibility(View.VISIBLE);
        }

        tInsertar.setOnClickListener(view -> {
            String newName = nombre.getText().toString();
            Perfil nuevoPerfil = new Perfil(email,newName,"1","2","3");
            vm.insertarPerfil(nuevoPerfil);
            Intent intent = new Intent(ActivityDetallePerfil.this, ActivityPerfiles.class);
            startActivity(intent);
            finish();
        });

        tModificar.setOnClickListener(view -> {
            Intent intent = new Intent(ActivityDetallePerfil.this, ActivityPerfiles.class);
            startActivity(intent);
            finish();
        });

        tCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetallePerfil.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetallePerfil.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetallePerfil.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetallePerfil.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

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
    }

    @Override
    protected void onPause() {
        super.onPause();
        Login.mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Login.mp.start();
    }
}