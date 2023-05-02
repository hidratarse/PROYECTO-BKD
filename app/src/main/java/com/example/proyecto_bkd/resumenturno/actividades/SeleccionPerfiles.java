package com.example.proyecto_bkd.resumenturno.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.PantallaPartida;
import com.example.proyecto_bkd.perfiles.PerfilesViewModel;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.Ranking;
import com.example.proyecto_bkd.resumenturno.JugadorAdapter;
import com.example.proyecto_bkd.resumenturno.data.Jugador;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.ArrayList;

public class SeleccionPerfiles extends AppCompatActivity implements Serializable {
    //Elementos del layout
    Switch sMSeleccion;
    Button bComenzar;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    TextView tAceptar, tMensajeError;
    //Atributos para contruir el recyclerView
    private RecyclerView recyclerView;
    private JugadorAdapter adaptador;
    private PerfilesViewModel vm;

    //Atributos para el constructor Jugador
    int contColor =0;
    String[] color = {"Rojo","Negro","Amarillo","Rosa"};
    public static ArrayList<Jugador> listaJugadores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_perfiles);
        overridePendingTransition(R.anim.aparece_izquierda,R.anim.desaparece_derecha);
        bComenzar =findViewById(R.id.bComenzar);
        sMSeleccion= findViewById(R.id.sMSeleccion);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        recyclerView=findViewById(R.id.rJugadores);
        Vibrator vibrator =(Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = currentUser.getEmail();
        String uid = currentUser.getUid();

        if (currentUser != null) {

            // aquí podemos acceder a la infor del usuario, podemos usar su mail para relacionarlo a los perfiles y las partidas
        } else {
            // El usuario no está autenticado, debes enviarlo a la actividad de inicio de sesión
        }

        adaptador = new JugadorAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adaptador);
        vm = new ViewModelProvider(this).get(PerfilesViewModel.class);
        vm.init();

        vm.getPerfiles(email);

        vm.getPerfilesLivedata().observe(this, (dato) -> {
            adaptador.setResults(dato);
        });

        adaptador.setClickListener((view, perfil) -> {
            //Atributo para validar la selección
            boolean valido=false;
            //Atributo para indicar la posicion del jugador en el arraylist si tiene el mismo nombre que el nombre del perfil seleccionado
            int posicion=0;
            for (int i=0; i<listaJugadores.size();i++) {
                if (listaJugadores.get(i).getNomJugador() == perfil.getNombre()) {
                    valido = true;
                    posicion=i;
                }
            }
            //si existe en el arraylist se modifica el color, si ha llegado al ultimo color del array se elimina de la lista
            if (valido) {
                if(listaJugadores.get(posicion).getContColor()==color.length-1){
                    listaJugadores.remove(listaJugadores.get(posicion));

                }else{
                    listaJugadores.get(posicion).setContColor(listaJugadores.get(posicion).getContColor()+1);
                    listaJugadores.get(posicion).setColor(color[listaJugadores.get(posicion).getContColor()]);
                }
            }else{
                //Si no existe en la lista se añade
                listaJugadores.add(new Jugador(perfil.getNombre(),color[contColor], contColor));
            }
        });

        //Metodo para navegar de actividad entre los estandartes
        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleccionPerfiles.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });

        //Metodo para navegar de actividad entre los estandartes
        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleccionPerfiles.this, PantallaPartida.class);
                startActivity(intent);
                finish();
            }
        });

        //Metodo para navegar de actividad entre los estandartes
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleccionPerfiles.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        //Método para controlar la música
        sMSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMSeleccion.isChecked()){
                    Login.mp.start();
                    Login.music =true;
                }else {
                    Login.mp.pause();
                    Login.music = false;
                }
            }
        });
        //Metodo que envia la lista de jugadores a ResumenTurno
        bComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valido=true;
                //Si no hay entre 2 y 4 jugadores seleccionados no comienza la partida
                if(listaJugadores.size()<2||listaJugadores.size()>4){
                    valido=false;
                    vibrator.vibrate(500);
                    alertError(getResources().getString(R.string.ErrorNumJugadores));
                }
                int validacionColores[]={0,0,0,0};

                for (int i=0; i<listaJugadores.size();i++) {
                    switch (listaJugadores.get(i).getContColor()) {
                        case 0:
                            validacionColores[0]++;
                            break;
                        case 1:
                            validacionColores[1]++;
                            break;
                        case 2:
                            validacionColores[2]++;
                            break;
                        case 3:
                            validacionColores[3]++;
                            break;
                    }
                }
                //Bucle para validar si hay 2 jugadores con el mismo color
                for (int i = 0; i < validacionColores.length; i++) {
                    if(validacionColores[i]>=2){
                        valido=false;
                        vibrator.vibrate(500);
                        alertError(getResources().getString(R.string.ErrorColorJugadores));
                    }
                }
                if(valido){
                    Intent intent = new Intent(SeleccionPerfiles.this, ResumenTurno.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void alertError(String mensaje) {
        AlertDialog alertError= new AlertDialog.Builder(SeleccionPerfiles.this).create();
        LayoutInflater inflater =this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.mensaje_error,null);
        alertError.setView(dialogView);
        tAceptar = dialogView.findViewById(R.id.tAceptarError);
        tMensajeError = dialogView.findViewById(R.id.tMensajeError);
        tMensajeError.setText(mensaje);
        alertError.show();
        alertError.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        tAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertError.dismiss();            }
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
            sMSeleccion.setChecked(false);
        }
    }
}