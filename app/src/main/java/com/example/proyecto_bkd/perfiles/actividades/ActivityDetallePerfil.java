package com.example.proyecto_bkd.perfiles.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
import com.example.proyecto_bkd.partida.PantallaPartida;
import com.example.proyecto_bkd.perfiles.PerfilesViewModel;
import com.example.proyecto_bkd.perfiles.data.Perfil;
import com.example.proyecto_bkd.ranking.Ranking;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityDetallePerfil extends AppCompatActivity {
    EditText nombre;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    TextView tCancela, tModificar, tInsertar, nPartidas, nGanadas, nPuntuacion, tBorrar;
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
        tBorrar = findViewById(R.id.tBorrar);
        sMPerfilDetalle= findViewById(R.id.sMPerfilDetalle);
        nPartidas = findViewById(R.id.NumPartidasJugadas);
        nGanadas = findViewById(R.id.NumPartidasGanadas);
        nPuntuacion = findViewById(R.id.NumMaxPuntuacion);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = currentUser.getEmail();

        vm = new ViewModelProvider(this).get(PerfilesViewModel.class);
        vm.init();

        boolean editando = getIntent().getBooleanExtra("EDITANDO", false);
        String idPerfil = getIntent().getStringExtra("ID");

        Log.d("KEK", idPerfil);

        if (editando){
            tModificar.setVisibility(View.VISIBLE);
            tInsertar.setVisibility(View.INVISIBLE);
            vm.getPerfil(idPerfil);
        }else {
            tModificar.setVisibility(View.INVISIBLE);
            tInsertar.setVisibility(View.VISIBLE);
            vm.clear();
        }

        vm.getPerfilLivedata().observe(this, perfil -> {
            nombre.setText(perfil.getNombre());
            nPartidas.setText(perfil.getPartidasJugadas());
            nGanadas.setText(perfil.getPartidasGanadas());
            nPuntuacion.setText(perfil.getMaxPuntuacion());
        });

        tBorrar.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityDetallePerfil.this);

            View confirmDialogView = getLayoutInflater().inflate(R.layout.confirm_dialog, null);
            builder.setView(confirmDialogView);

            TextView btnConfirmar = confirmDialogView.findViewById(R.id.tEliminarDialog);
            TextView btnCancelar = confirmDialogView.findViewById(R.id.tCancelarDialog);
            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            btnConfirmar.setOnClickListener(v -> {
                vm.eliminarPerfil(idPerfil);
                alertDialog.dismiss();
                Intent intent = new Intent(ActivityDetallePerfil.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            });

            btnCancelar.setOnClickListener(v -> {
                // cancelar
                alertDialog.dismiss();
            });
            alertDialog.show();
        });

        tInsertar.setOnClickListener(view -> {
            String newName = nombre.getText().toString();
            Perfil nuevoPerfil = new Perfil(email,newName,"0","0","0");
            vm.insertarPerfil(nuevoPerfil);
            Intent intent = new Intent(ActivityDetallePerfil.this, ActivityPerfiles.class);
            startActivity(intent);
            finish();
        });

        tModificar.setOnClickListener(view -> {
            String mNombre = String.valueOf(nombre.getText());
            String mPartidas = String.valueOf(nPartidas.getText());
            String mGanadas = String.valueOf(nGanadas.getText());
            String mPuntos = String.valueOf(nPuntuacion.getText());
            Perfil nPerfil = new Perfil(email, mNombre, mPartidas, mGanadas, mPuntos);
            nPerfil.setId(idPerfil);
            vm.modificarPerfil(idPerfil,nPerfil);
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
                Intent intent = new Intent(ActivityDetallePerfil.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetallePerfil.this, PantallaPartida.class);
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
                    Login.music =true;
                }else {
                    Login.mp.pause();
                    Login.music = false;
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
        if(Login.music){
            Login.mp.start();
        }else{
            Login.mp.pause();
            sMPerfilDetalle.setChecked(false);
        }
    }
}