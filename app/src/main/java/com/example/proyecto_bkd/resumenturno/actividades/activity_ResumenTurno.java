package com.example.proyecto_bkd.resumenturno.actividades;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.activity_ranking2;
import com.example.proyecto_bkd.resumenturno.Feudo;
import com.example.proyecto_bkd.resumenturno.data.ResumenTurnoAdapter;
import java.util.ArrayList;

public class activity_ResumenTurno extends AppCompatActivity {
    RecyclerView recyclerView;
    public ArrayList<Feudo> listaFeudos;
    ResumenTurnoAdapter adapter;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking,bAdd;
    TextView tFinTurno,tAddFeudo,tNumTurno,tNomJugador,tPuntosRonda;
    EditText puntosPergamino;
    Button aceptarPuntos;
    Switch sMResumenTurno;
    String[] jugadores={"Jorge","Jose"};
    int[] puntuacion={0,0};
    int ronda=1;
    int turno=0;
    private final int MAX_RONDAS=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_turno);

        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        bAdd=findViewById(R.id.bAdd);
        tFinTurno=findViewById(R.id.tFinTurno);
        tAddFeudo=findViewById(R.id.tAddFeudo);
        tNumTurno = findViewById(R.id.tNumTurno);
        tNomJugador = findViewById(R.id.tNomJugador);
        tPuntosRonda= findViewById(R.id.tPuntosRonda);
        tNumTurno.setText(String.valueOf(ronda));
        sMResumenTurno= findViewById(R.id.sMResumenTurno);

        sMResumenTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMResumenTurno.isChecked()){
                    Login.mp.start();
                }else{
                    Login.mp.pause();
                }
            }
        });
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
                Intent intent = new Intent(activity_ResumenTurno.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.id_rv_resumenTurnos);
        listaFeudos = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ResumenTurnoAdapter(listaFeudos);
        recyclerView.setAdapter(adapter);
        tNomJugador.setText(jugadores[0]);
        tPuntosRonda.setText(String.valueOf(puntuacion[turno]));

        ActivityResultLauncher activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),result -> {
                    int codigo = result.getResultCode();

                    switch (codigo){
                        case RESULT_CANCELED:
                            break;
                        case NuevoFeudo.ACTUALIZAR_ADAPTER:
                            Intent data = result.getData();
                            Feudo fNuevo = (Feudo) data.getSerializableExtra("enviar");
                            puntuacion[turno]=Integer.parseInt(tPuntosRonda.getText().toString())+fNuevo.getPuntos();
                            tPuntosRonda.setText(String.valueOf(puntuacion[turno]));
                            listaFeudos.add(fNuevo);
                            adapter=new ResumenTurnoAdapter(listaFeudos);
                            recyclerView.setAdapter(adapter);
                           /*
                            for (int i = 0; i <listaFeudos.size(); i++) {
                                Log.d("despues",String.valueOf(listaFeudos.get(i).getTorres()));
                            }
                            */
                            break;
                    }
                });


        tFinTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turno++;
                listaFeudos.clear();
                adapter = new ResumenTurnoAdapter(listaFeudos);
                recyclerView.setAdapter(adapter);
                if(turno==jugadores.length) {
                    ronda++;
                    turno=0;
                }
                tNomJugador.setText(jugadores[turno]);
                tNumTurno.setText(ronda+"");
                tPuntosRonda.setText(puntuacion[turno]+"");
                if(ronda>2){
                    tNumTurno.setText(String.valueOf(2));
                        mostrarAlertDialog(turno);
                }
            }
        });
        tAddFeudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ResumenTurno.this, NuevoFeudo.class);
                activityResultLauncher.launch(intent);
            }
        });
    }

    private void mostrarAlertDialog(int idJugador) {
        AlertDialog alertPergaminos= new AlertDialog.Builder(activity_ResumenTurno.this).create();
        LayoutInflater inflater =this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.puntos_pergamino,null);
        alertPergaminos.setView(dialogView);
        puntosPergamino = dialogView.findViewById(R.id.ePuntosPergamino);
        aceptarPuntos = dialogView.findViewById(R.id.bPuntosPerga);

        puntosPergamino.setHint("puntos "+jugadores[idJugador]);
        alertPergaminos.show();
        aceptarPuntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puntuacion[idJugador]=puntuacion[idJugador]+Integer.parseInt(puntosPergamino.getText().toString());
                Log.d("PUNTOS PERGA",jugadores[idJugador] +" "+puntosPergamino.getText().toString()+" "+String.valueOf(puntuacion[idJugador]));
                alertPergaminos.dismiss();
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