package com.example.proyecto_bkd.partida.verPartida.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.PartidasViewModel;
import com.example.proyecto_bkd.partida.actividades.PrincipalPartida;
import com.example.proyecto_bkd.partida.data.Partidas;
import com.example.proyecto_bkd.partida.resumenturno.actividades.ResumenTurno;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.Ranking;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DetallePartida extends AppCompatActivity {
    TextView tCerrar,tFechaP,tJug1,tJug2,tJug3,tJug4,tColor1,tColor2,tColor3,tColor4, tEliminar;
    TextView tPtos1, tPtos2,tPtos3,tPtos4,tPosicion1,tPosicion2,tPosicion3,tPosicion4;
    Switch sMDetallePartida;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking, imgEliminarPartida;
    ImageView imgFoto;
    PartidasViewModel vm;
    String idPartida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_partida);
        sMDetallePartida = findViewById(R.id.sMDetallePartida);
        bImgPartidas = findViewById(R.id.bImgPartidas);
        bImgPerfiles = findViewById(R.id.bImgPerfiles);
        bImgRanking = findViewById(R.id.bImgRanking);
        imgFoto = findViewById(R.id.imgFoto);
        tFechaP = findViewById(R.id.tFechaP);
        tJug1 = findViewById(R.id.tJug1);
        tJug2 = findViewById(R.id.tJug2);
        tJug3 = findViewById(R.id.tJug3);
        tJug4 = findViewById(R.id.tJug4);
        tColor1 = findViewById(R.id.tColor1);
        tColor2 = findViewById(R.id.tColor2);
        tColor3 = findViewById(R.id.tColor3);
        tColor4 = findViewById(R.id.tColor4);
        tPtos1 = findViewById(R.id.tPtos1);
        tPtos2 = findViewById(R.id.tPtos2);
        tPtos3 = findViewById(R.id.tPtos3);
        tPtos4 = findViewById(R.id.tPtos4);
        tPosicion1 = findViewById(R.id.tPoscion1);
        tPosicion2 = findViewById(R.id.tPoscion2);
        tPosicion3 = findViewById(R.id.tPoscion3);
        tPosicion4 = findViewById(R.id.tPoscion4);
        tEliminar = findViewById(R.id.tEliminarFeudo);
        imgEliminarPartida  = findViewById(R.id.imgEliminarPartida);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = currentUser.getEmail();

        vm = new ViewModelProvider(this).get(PartidasViewModel.class);
        vm.init();

        if (ResumenTurno.finPartida) {
            colocarDatos(ResumenTurno.partida);
            tEliminar.setVisibility(View.INVISIBLE);
            imgEliminarPartida.setVisibility(View.INVISIBLE);
        }else{
            idPartida = getIntent().getStringExtra("ID");
            vm.getPartida(idPartida);
            vm.getPartidaLivedata().observe(this, partidas -> {
                colocarDatos(partidas);
            });
            tEliminar.setVisibility(View.VISIBLE);
            imgEliminarPartida.setVisibility(View.VISIBLE);
        }
        ResumenTurno.finPartida=false;


        idPartida = getIntent().getStringExtra("partida");

        vm.getPartidaLivedata().observe(this, partidas -> {
            colocarDatos(partidas);
            Glide.with(this).load(partidas.getFotoPartida()).into(this.imgFoto);
        });

        vm.getPartida(idPartida);

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePartida.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePartida.this, PrincipalPartida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePartida.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        sMDetallePartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sMDetallePartida.isChecked()) {
                    Login.mp.start();
                    Login.music = true;
                } else {
                    Login.mp.pause();
                    Login.music = false;
                }
            }
        });

        tCerrar = findViewById(R.id.tCerrar);
        tCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePartida.this, VerPartidas.class);
                startActivity(intent);
                finish();
            }
        });
        tEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetallePartida.this);

                    View confirmDialogView = getLayoutInflater().inflate(R.layout.confirm_dialog, null);
                    builder.setView(confirmDialogView);

                    TextView btnConfirmar = confirmDialogView.findViewById(R.id.tEliminarDialog);
                    TextView btnCancelar = confirmDialogView.findViewById(R.id.tCancelarDialog);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    btnConfirmar.setOnClickListener(v -> {
                        vm.eliminarPartida(idPartida);
                        alertDialog.dismiss();
                        Intent intent = new Intent(DetallePartida.this, VerPartidas.class);
                        startActivity(intent);
                        finish();
                    });

                    btnCancelar.setOnClickListener(v -> {
                        // cancelar
                        alertDialog.dismiss();
                    });
                    alertDialog.show();
                }catch (Exception e){

                }
            }
        });
    }
    public void colocarDatos(Partidas partidas){

        tFechaP.setText(partidas.getFecha()+"");
        tJug1.setText(partidas.getJ1().getNomJugador());
        tJug2.setText(partidas.getJ2().getNomJugador());
        switch (partidas.getJ1().getColor()){
            case "Negro":
                tColor1.setBackgroundColor(Color.parseColor("#000000"));
                break;
            case "Rojo":
                tColor1.setBackgroundColor(Color.parseColor("#e02c00"));
                break;
            case "Amarillo":
                tColor1.setBackgroundColor(Color.parseColor("#ffeb40"));
                break;
            case "Rosa":
                tColor1.setBackgroundColor(Color.parseColor("#e98df5"));
                break;
        }
        switch (partidas.getJ2().getColor()){
            case "Negro":
                tColor2.setBackgroundColor(Color.parseColor("#000000"));
                break;
            case "Rojo":
                tColor2.setBackgroundColor(Color.parseColor("#e02c00"));
                break;
            case "Amarillo":
                tColor2.setBackgroundColor(Color.parseColor("#ffeb40"));
                break;
            case "Rosa":
                tColor2.setBackgroundColor(Color.parseColor("#e98df5"));
                break;
        }

        tPtos1.setText(String.valueOf(partidas.getJ1().getPuntos()));
        tPtos2.setText(String.valueOf(partidas.getJ2().getPuntos()));
        tPosicion1.setText(String.valueOf(partidas.getJ1().getPosicion()));
        tPosicion2.setText((String.valueOf(partidas.getJ2().getPosicion())));
        if(partidas.getJ3()!=null) {
            switch (partidas.getJ3().getColor()){
                case "Negro":
                    tColor3.setBackgroundColor(Color.parseColor("#000000"));
                    break;
                case "Rojo":
                    tColor3.setBackgroundColor(Color.parseColor("#e02c00"));
                    break;
                case "Amarillo":
                    tColor3.setBackgroundColor(Color.parseColor("#ffeb40"));
                    break;
                case "Rosa":
                    tColor3.setBackgroundColor(Color.parseColor("#e98df5"));
                    break;
            }
            tJug3.setText(partidas.getJ3().getNomJugador());
            tPtos3.setText(String.valueOf(partidas.getJ3().getPuntos()));
            tPosicion3.setText((String.valueOf(partidas.getJ3().getPosicion())));
        }
        if(partidas.getJ4()!=null) {
            switch (partidas.getJ4().getColor()){
                case "Negro":
                    tColor4.setBackgroundColor(Color.parseColor("#000000"));
                    break;
                case "Rojo":
                    tColor4.setBackgroundColor(Color.parseColor("#e02c00"));
                    break;
                case "Amarillo":
                    tColor4.setBackgroundColor(Color.parseColor("#ffeb40"));
                    break;
                case "Rosa":
                    tColor4.setBackgroundColor(Color.parseColor("#e98df5"));
                    break;
            }
            tJug4.setText(partidas.getJ4().getNomJugador());
            tPtos4.setText(String.valueOf(partidas.getJ4().getPuntos()));
            tPosicion4.setText((String.valueOf(partidas.getJ4().getPosicion())));
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
        if(Login.music){
            Login.mp.start();
        }else{
            Login.mp.pause();
            sMDetallePartida.setChecked(false);
        }
    }
}