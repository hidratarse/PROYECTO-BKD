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
import android.widget.Toast;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.activity_ranking2;
import com.example.proyecto_bkd.resumenturno.Feudo;
import com.example.proyecto_bkd.resumenturno.data.ResumenTurnoAdapter;
import java.util.ArrayList;

public class activity_ResumenTurno extends AppCompatActivity {
    //Atributos para las condiciones del transcurso de la partida
    private final int MAX_RONDAS=4;
    int ronda=1;
    int turno=0;
    ArrayList<String> listaNombres=new ArrayList<String>();
    int[] puntuacion = new int[4];

    //Atributos de elementos del layout
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking,bAdd;
    TextView tFinTurno,tAddFeudo,tNumTurno,tNomJugador,tPuntosRonda;
    EditText puntosPergamino;
    Button aceptarPuntos;
    Switch sMResumenTurno;

    //Atributos para mostrar datos en el recyclerView
    Bundle bundle;
    public ArrayList<Feudo> listaFeudos;
    RecyclerView recyclerView;
    ResumenTurnoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.aparece_izquierda,R.anim.desaparece_derecha);
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

        if(!Login.mp.isPlaying()){
            sMResumenTurno.setChecked(false);
        }

        //Método para controlar la música
        sMResumenTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMResumenTurno.isChecked()){
                    Login.mp.start();
                    Login.music =true;
                }else {
                    Login.mp.pause();
                    Login.music = false;
                }
            }
        });

        //Metodo para navegar de actividad entre los estandartes
        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ResumenTurno.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        //Metodo para navegar de actividad entre los estandartes
        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ResumenTurno.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });

        //Metodo para navegar de actividad entre los estandartes
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ResumenTurno.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        //Se muestra el recyclerView
        listaFeudos = new ArrayList<>();
        recyclerView = findViewById(R.id.id_rv_resumenTurnos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ResumenTurnoAdapter(listaFeudos);
        recyclerView.setAdapter(adapter);

        //Se obtienen los nombres de los jugadores
        bundle=getIntent().getExtras();
        listaNombres = getIntent().getStringArrayListExtra("jugadores");
        tNomJugador.setText(listaNombres.get(0));
        Log.d("TAMAÑO LISTA", listaNombres.size()+"");


            puntuacion = new int[listaNombres.size()];

        if (ronda == 1 && turno == 0) {
            for (int i = 0; i < puntuacion.length; i++) {
                puntuacion[i] = 0;
            }
        }
        Log.d("TAMAÑO PUNTUACION", puntuacion.length+"");
        //Se modifica la puntuacion dependiendo del jugador
        tPuntosRonda.setText(String.valueOf(puntuacion[turno]));

        //Se crea ActivityResultLauncher para obtener los datos de los nuevos feudos
        ActivityResultLauncher activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),result -> {
                    int codigo = result.getResultCode();

                    switch (codigo){
                        case RESULT_CANCELED:
                            break;
                        case NuevoFeudo.ACTUALIZAR_ADAPTER:

                            Intent data = result.getData();
                            Feudo fNuevo = (Feudo) data.getSerializableExtra("enviar");
                            Feudo fCloned = new Feudo(fNuevo.getRecursos(), fNuevo.getTorres(), fNuevo.getPuntos());
                            puntuacion[turno] = Integer.parseInt(tPuntosRonda.getText().toString())+fNuevo.getPuntos();
                            tPuntosRonda.setText(String.valueOf(puntuacion[turno]));
                            listaFeudos.add(fCloned);
                            fNuevo=null;
                            adapter=new ResumenTurnoAdapter(listaFeudos);
                            recyclerView.setAdapter(adapter);

                            break;
                    }
                });

        //Al finalizar el turno el recyclerView se vacía y se prepara para el siguiente jugador
        tFinTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turno++;
                listaFeudos.clear();
                adapter = new ResumenTurnoAdapter(listaFeudos);
                recyclerView.setAdapter(adapter);
                //Si ha jugado el turno el último jugador se inicia nueva ronda
                if(turno==listaNombres.size()) {
                    ronda++;
                    turno=0;
                }
                tNomJugador.setText(listaNombres.get(turno));
                tNumTurno.setText(ronda+"");
                tPuntosRonda.setText(puntuacion[turno]+"");
                //Si se ha jugado el turno del último jugador en la ultima ronda se llama al evento mostrarAlertDialog
                if(ronda>MAX_RONDAS){
                    tNumTurno.setText(String.valueOf(MAX_RONDAS));
                        mostrarAlertDialog(turno);
                }
            }
        });

        //Se inicia la actividad añadir feudo
        tAddFeudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ResumenTurno.this, NuevoFeudo.class);
                activityResultLauncher.launch(intent);
            }
        });
    }

    //Se crea ventana AlertDialog para solicitar los puntos conseguidos por los pergaminos del jugador
    private void mostrarAlertDialog(int idJugador) {
        AlertDialog alertPergaminos= new AlertDialog.Builder(activity_ResumenTurno.this).create();
        LayoutInflater inflater =this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.puntos_pergamino,null);
        alertPergaminos.setView(dialogView);
        puntosPergamino = dialogView.findViewById(R.id.ePuntosPergamino);
        aceptarPuntos = dialogView.findViewById(R.id.bPuntosPerga);

        puntosPergamino.setHint("puntos "+listaNombres.get(idJugador));
        alertPergaminos.show();

        aceptarPuntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valido=true;
                    try{
                        puntuacion[idJugador]+=Integer.parseInt(puntosPergamino.getText().toString());
                        Log.d("PUNTOS PERGA",listaNombres.get(idJugador)+" "+puntosPergamino.getText().toString()+" "+String.valueOf(puntuacion[idJugador]));
                    }catch (NumberFormatException e){
                        Toast.makeText(activity_ResumenTurno.this, "No has introducido un número", Toast.LENGTH_SHORT).show();
                        valido = false;
                    }

                if(valido){
                    alertPergaminos.dismiss();
                        turno++;
                    if(turno<listaNombres.size()) {
                        tNomJugador.setText(listaNombres.get(turno));
                        mostrarAlertDialog(turno);
                    }else{
                        Intent intent = new Intent(activity_ResumenTurno.this, Partida.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Se para la música
        Login.mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Se reanuda la música
        if(Login.music){
            Login.mp.start();
        }else{
            Login.mp.pause();
            sMResumenTurno.setChecked(false);
        }
    }
}