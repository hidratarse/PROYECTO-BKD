package com.example.proyecto_bkd.partida.resumenturno.actividades;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.actividades.PrincipalPartida;
import com.example.proyecto_bkd.partida.data.Feudo;
import com.example.proyecto_bkd.partida.data.Partidas;
import com.example.proyecto_bkd.partida.verPartida.actividades.DetallePartida;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.Ranking;
import com.example.proyecto_bkd.partida.PartidasViewModel;
import com.example.proyecto_bkd.partida.resumenturno.ResumenTurnoAdapter;
import com.example.proyecto_bkd.utils.Alert;
import com.example.proyecto_bkd.utils.GallerySaver;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResumenTurno extends AppCompatActivity {
    //Atributos para las condiciones del transcurso de la partida
    private final int MAX_RONDAS=4;
    int ronda=1;
    int turno=0;
    //Atributos de elementos del layout
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking,bAdd,bPuntosPerga;
    TextView tFinTurno,tAddFeudo,tNumTurno,tNomJugador,tPuntosRonda,tFinal,tTituloAlert,tSi,tNo, tAlert,tMensajeFinal;
    EditText puntosPergamino;
    Switch sMResumenTurno;
    //Atributos para mostrar datos en el recyclerView
    public ArrayList<Feudo> listaFeudos;
    RecyclerView recyclerView;
    ResumenTurnoAdapter adapterResumen;
    String email;
    public static Partidas partida;
    PartidasViewModel vmPartidas;
    public static boolean  finPartida = false;
    int posicionFeudo,restarPuntos,partidasJugadas, partidasGanadas,percent;
    FirebaseUser currentUser;
    FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.aparece_izquierda, R.anim.desaparece_derecha);
        setContentView(R.layout.activity_resumen_turno);
        bImgPartidas = findViewById(R.id.bImgPartidas);
        bImgPerfiles = findViewById(R.id.bImgPerfiles);
        bImgRanking = findViewById(R.id.bImgRanking);
        bAdd = findViewById(R.id.bAdd);
        tFinTurno = findViewById(R.id.tFinTurno);
        tAddFeudo = findViewById(R.id.tAddFeudo);
        tNumTurno = findViewById(R.id.tNumTurno);
        tNomJugador = findViewById(R.id.tNomJugador);
        tPuntosRonda = findViewById(R.id.tPuntosRonda);
        tNumTurno.setText(String.valueOf(ronda));
        sMResumenTurno = findViewById(R.id.sMResumenTurno);

        currentUser= FirebaseAuth.getInstance().getCurrentUser();
        email = currentUser.getEmail();

        //Método para controlar la música
        sMResumenTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sMResumenTurno.isChecked()) {
                    Login.mp.start();
                    Login.music = true;
                } else {
                    Login.mp.pause();
                    Login.music = false;
                }
            }
        });

        //Método para navegar de actividad entre los estandartes
        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResumenTurno.this, Ranking.class);
               controlPartida(intent);
            }
        });

        //Metodo para navegar de actividad entre los estandartes
        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResumenTurno.this, PrincipalPartida.class);
                controlPartida(intent);
            }
        });

        //Metodo para navegar de actividad entre los estandartes
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResumenTurno.this, ActivityPerfiles.class);
                controlPartida(intent);
            }
        });

        vmPartidas = new ViewModelProvider(this).get(PartidasViewModel.class);
        vmPartidas.init();

        //Se muestra el recyclerView
        listaFeudos = new ArrayList<>();
        recyclerView = findViewById(R.id.id_rv_resumenTurnos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterResumen = new ResumenTurnoAdapter(listaFeudos);
        recyclerView.setAdapter(adapterResumen);

        for (int i = 0; i < SeleccionPerfiles.listaJugadores.size(); i++) {
            SeleccionPerfiles.listaJugadores.get(i).setPuntos(0);
        }
        Alert.alertError(ResumenTurno.this,getResources().getString(R.string.Ronda)+" "+ronda);
        tNomJugador.setText(SeleccionPerfiles.listaJugadores.get(turno).getNomJugador());
        //Se modifica la puntuación dependiendo del jugador
        tPuntosRonda.setText(String.valueOf(SeleccionPerfiles.listaJugadores.get(turno).getPuntos()));

        //Se crea ActivityResultLauncher para obtener los datos de los nuevos feudos
        ActivityResultLauncher activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                int codigo = result.getResultCode();

                switch (codigo) {
                    case RESULT_CANCELED:
                        break;
                    case NuevoFeudo.ACTUALIZAR_ADAPTER:
                        Intent data = result.getData();
                        Feudo fNuevo = (Feudo) data.getSerializableExtra("enviar");
                        SeleccionPerfiles.listaJugadores.get(turno).setPuntos(Integer.parseInt(tPuntosRonda.getText().toString()) + fNuevo.getPuntos());
                        tPuntosRonda.setText(String.valueOf(SeleccionPerfiles.listaJugadores.get(turno).getPuntos()));
                        listaFeudos.add(fNuevo);
                        adapterResumen.setResults(listaFeudos);
                        recyclerView.setAdapter(adapterResumen);
                        break;
                    case NuevoFeudo.ACTUALIZAR_FEUDO:
                        Intent dataEditado = result.getData();
                        Feudo fEditado = (Feudo) dataEditado.getSerializableExtra("enviar");
                        posicionFeudo=dataEditado.getIntExtra("posicion",0);
                        restarPuntos=dataEditado.getIntExtra("restar",0);
                        SeleccionPerfiles.listaJugadores.get(turno).setPuntos(Integer.parseInt(tPuntosRonda.getText().toString()) + fEditado.getPuntos()-restarPuntos);
                        tPuntosRonda.setText(String.valueOf(SeleccionPerfiles.listaJugadores.get(turno).getPuntos()));
                        listaFeudos.set(posicionFeudo,fEditado);
                        adapterResumen.setResults(listaFeudos);
                        recyclerView.setAdapter(adapterResumen);
                        break;
                }
            }
        );
        //Al finalizar el turno el recyclerView se vacía y se prepara para el siguiente jugador
        tFinTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResumenTurno.this);
                View confirmDialogView = getLayoutInflater().inflate(R.layout.confirm_dialog, null);
                builder.setView(confirmDialogView);
                TextView btnConfirmar = confirmDialogView.findViewById(R.id.tEliminarDialog);
                TextView btnCancelar = confirmDialogView.findViewById(R.id.tCancelarDialog);
                TextView dialog_title = confirmDialogView.findViewById(R.id.dialog_title);
                btnConfirmar.setText(getResources().getString(R.string.Si));
                btnCancelar.setText(getResources().getString(R.string.No));
                dialog_title.setText(getResources().getString(R.string.TerminarTurnoJugador));
                AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                btnConfirmar.setOnClickListener(v -> {
                    //Alert.alertError(ResumenTurno.this,SeleccionPerfiles.listaJugadores.get(turno).getNomJugador()+": "+SeleccionPerfiles.listaJugadores.get(turno).getPuntos()+ getResources().getString(R.string.Puntos));
                    turno++;
                    listaFeudos.clear();
                    adapterResumen.setResults(listaFeudos);
                    recyclerView.setAdapter(adapterResumen);
                    //Si ha jugado el turno el último jugador se inicia nueva ronda
                    if (turno == SeleccionPerfiles.listaJugadores.size()) {
                        ronda++;
                        turno = 0;
                        if(ronda<5){
                            Alert.alertError(ResumenTurno.this,getResources().getString(R.string.Ronda)+" "+ronda);
                        }
                    }
                    tNomJugador.setText(SeleccionPerfiles.listaJugadores.get(turno).getNomJugador());
                    tNumTurno.setText(ronda + "");
                    tPuntosRonda.setText(SeleccionPerfiles.listaJugadores.get(turno).getPuntos() + "");
                    //Si se ha jugado el turno del último jugador en la ultima ronda se llama al evento mostrarAlertDialog
                    if (ronda > MAX_RONDAS) {
                        tNumTurno.setText(String.valueOf(MAX_RONDAS));
                        mostrarAlertDialog(turno);
                    }
                    alertDialog.dismiss();
                });

                btnCancelar.setOnClickListener(v -> {
                    // cancelar
                    alertDialog.dismiss();
                });
                alertDialog.show();
            }
        });

        //Se inicia la actividad añadir feudo
        tAddFeudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResumenTurno.this, NuevoFeudo.class);
                activityResultLauncher.launch(intent);
            }
        });

        adapterResumen.setClickListener(new ResumenTurnoAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, Feudo feudo, int posicion) {
                Toast.makeText(ResumenTurno.this, feudo.getTorres() + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ResumenTurno.this, NuevoFeudo.class);
                intent.putExtra("editarFeudo",feudo);
                intent.putExtra("posicion",posicion);
                activityResultLauncher.launch(intent);
            }
        });
    }

    private void terminarPartida() {
        AlertDialog alertFinal= new AlertDialog.Builder(ResumenTurno.this).create();
        alertFinal.setCancelable(false);
        LayoutInflater inflater =this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.mensaje_error,null);
        alertFinal.setView(dialogView);
        tMensajeFinal = dialogView.findViewById(R.id.tMensajeError);
        tFinal = dialogView.findViewById(R.id.tAceptarError);
        tMensajeFinal.setText(getResources().getString(R.string.Final));
        tFinal.setTextSize(34);
        alertFinal.show();
        alertFinal.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        tFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResumenTurno.this, DetallePartida.class);
                finPartida=true;
                intent.putExtra("partida",partida.getIdPartida());
                startActivity(intent);
                alertFinal.dismiss();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (Alert.alertError != null && Alert.alertError.isShowing()) {
            // Mostrar el diálogo si aún no se ha mostrado
            Alert.alertError.show();
        } else {
            super.onBackPressed();
        }
    }

    //Se crea ventana AlertDialog para solicitar los puntos conseguidos por los pergaminos del jugador
    private void mostrarAlertDialog(int idJugador) {
        Vibrator vibrator =(Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        AlertDialog alertPergaminos= new AlertDialog.Builder(ResumenTurno.this).create();
        alertPergaminos.setCancelable(false);
        LayoutInflater inflater =this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_inserta_datos,null);
        alertPergaminos.setView(dialogView);
        puntosPergamino = dialogView.findViewById(R.id.eDato);
        tTituloAlert = dialogView.findViewById(R.id.tAlertDato);
        bPuntosPerga = dialogView.findViewById(R.id.bAceptarDato);
        tTituloAlert.setText(getResources().getString(R.string.AlertPergamino));
        puntosPergamino.setHint(getResources().getString(R.string.IntroducePuntos)+" "+ SeleccionPerfiles.listaJugadores.get(idJugador).getNomJugador());
        puntosPergamino.requestFocus();
        alertPergaminos.show();
        alertPergaminos.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        bPuntosPerga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valido=true;
                    try{
                        SeleccionPerfiles.listaJugadores.get(idJugador).setPuntos(SeleccionPerfiles.listaJugadores.get(idJugador).getPuntos()+Integer.parseInt(puntosPergamino.getText().toString()));
                        Log.d("PUNTOS PERGA",SeleccionPerfiles.listaJugadores.get(idJugador).getNomJugador()+" "+puntosPergamino.getText().toString()+" "+String.valueOf(SeleccionPerfiles.listaJugadores.get(idJugador).getPuntos()));
                    }catch (NumberFormatException e){
                        vibrator.vibrate(500);
                        Alert.alertError(ResumenTurno.this,getResources().getString(R.string.NoNum));
                        valido = false;
                    }

                if(valido){
                    alertPergaminos.dismiss();
                        turno++;
                    if(turno<SeleccionPerfiles.listaJugadores.size()) {
                        tNomJugador.setText(SeleccionPerfiles.listaJugadores.get(turno).getNomJugador());
                        mostrarAlertDialog(turno);
                    }else{
                        posiciones();
                        crearPartida();
                        actualizar();
                        vmPartidas.insertarPartida(partida, () -> terminarPartida());
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

    private void crearPartida(){
        long ahora = System.currentTimeMillis();
        Date date = new Date(ahora);
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        String fecha = df.format(date);
        switch (SeleccionPerfiles.listaJugadores.size()){
            case 2:
                partida =new Partidas(email, fecha,SeleccionPerfiles.listaJugadores.get(0),SeleccionPerfiles.listaJugadores.get(1));
                break;
            case 3:
                partida =new Partidas(email, fecha,SeleccionPerfiles.listaJugadores.get(0),SeleccionPerfiles.listaJugadores.get(1),SeleccionPerfiles.listaJugadores.get(2));
                break;
            case 4:
                partida =new Partidas(email, fecha,SeleccionPerfiles.listaJugadores.get(0),SeleccionPerfiles.listaJugadores.get(1),SeleccionPerfiles.listaJugadores.get(2),SeleccionPerfiles.listaJugadores.get(3));
                break;
        }

    }
    private void actualizar(){
        Map <String, Object> map = new HashMap<>();
        for (int i = 0; i < SeleccionPerfiles.listaJugadores.size(); i++) {
            map.clear();
            partidasJugadas = SeleccionPerfiles.listaJugadores.get(i).getPartidasJugadas()+1;
            partidasGanadas =SeleccionPerfiles.listaJugadores.get(i).getPartidasGanadas();
            map.put("partidasJugadas",String.valueOf(partidasJugadas));

            if(SeleccionPerfiles.listaJugadores.get(i).getPuntos()>SeleccionPerfiles.listaJugadores.get(i).getMaxPuntuacion()){
                map.put("maxPuntuacion",String.valueOf(SeleccionPerfiles.listaJugadores.get(i).getPuntos()));
            }
            if(SeleccionPerfiles.listaJugadores.get(i).getPosicion()==1){
                partidasGanadas=partidasGanadas+1;
                percent=(int)(partidasGanadas*100.0)/partidasJugadas;
                Toast.makeText(this,String.valueOf(percent), Toast.LENGTH_SHORT);
                map.put("partidasGanadas",String.valueOf(partidasGanadas));
                map.put("porcentajeGanadas",String.valueOf(percent));
            }else {
                percent=(int)(partidasGanadas*100.0)/partidasJugadas;
                map.put("porcentajeGanadas",String.valueOf(percent));
                Toast.makeText(this,String.valueOf(percent), Toast.LENGTH_SHORT);
            }

            mFirestore = FirebaseFirestore.getInstance();
            mFirestore.collection("perfiles").document(SeleccionPerfiles.listaJugadores.get(i).getIdJugador()).update(map);
        }
    }

    private void posiciones(){
       ArrayList<Integer> puntuaciones = new ArrayList<>();
        for (int j = 0; j <SeleccionPerfiles.listaJugadores.size(); j++) {
            puntuaciones.add(SeleccionPerfiles.listaJugadores.get(j).getPuntos());
        }
        Collections.sort(puntuaciones);
        Collections.reverse(puntuaciones);
        int posicion=0;
        for (int i = 0; i <puntuaciones.size() ; i++) {
            posicion = puntuaciones.indexOf(SeleccionPerfiles.listaJugadores.get(i).getPuntos()) ;
            SeleccionPerfiles.listaJugadores.get(i).setPosicion(posicion+1);
        }
    }

    private void controlPartida(Intent intent){
        AlertDialog salirPartida= new AlertDialog.Builder(ResumenTurno.this).create();
        salirPartida.setCancelable(false);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.confirm_dialog,null);
        salirPartida.setView(dialogView);
        tAlert = dialogView.findViewById(R.id.dialog_title);
        tSi = dialogView.findViewById(R.id.tEliminarDialog);
        tNo = dialogView.findViewById(R.id.tCancelarDialog);
        tAlert.setText(getResources().getString(R.string.salirPartida));
        tSi.setText(getResources().getString(R.string.Si));
        tNo.setText(getResources().getString(R.string.No));
        tSi.setTextSize(34);
        tNo.setTextSize(34);
        salirPartida.show();
        salirPartida.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        tSi.setOnClickListener(view -> {
            salirPartida.dismiss();
            startActivity(intent);
            finish();
        });
        tNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salirPartida.dismiss();
            }
        });
    }
}