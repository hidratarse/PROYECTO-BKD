package com.example.proyecto_bkd.resumenturno.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.perfiles.PerfilesAdapter;
import com.example.proyecto_bkd.perfiles.PerfilesViewModel;
import com.example.proyecto_bkd.perfiles.actividades.ActivityDetallePerfil;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.activity_ranking2;
import com.example.proyecto_bkd.resumenturno.Feudo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.ArrayList;

public class SeleccionPerfiles extends AppCompatActivity implements Serializable {
    //Elementos del layout
    Switch sMSeleccion;
    Button bComenzar;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;

    //Atributos para contruir el recyclerView
    private RecyclerView recyclerView;
    private PerfilesAdapter adaptador;
    private PerfilesViewModel vm;

    //Atributos para el constructor Jugador
    int contColor =0;
    String[] color = {"Rojo","Negro","Amarrillo","Rosa"};
    ArrayList<Jugador> listaJugadores = new ArrayList<>();

    //Almacena los nombres de listaJugadores para enviarlos a la actividad ResumenTurno
    ArrayList<String> nombres=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_perfiles);
        bComenzar =findViewById(R.id.bComenzar);
        sMSeleccion= findViewById(R.id.sMSeleccion);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        recyclerView=findViewById(R.id.rJugadores);

        if(!Login.mp.isPlaying()){
            sMSeleccion.setChecked(false);
        }

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = currentUser.getEmail();
        String uid = currentUser.getUid();

        if (currentUser != null) {

            // aquí podemos acceder a la infor del usuario, podemos usar su mail para relacionarlo a los perfiles y las partidas
        } else {
            // El usuario no está autenticado, debes enviarlo a la actividad de inicio de sesión
        }

        adaptador = new PerfilesAdapter();
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
            Log.d("TAMAÑO", String.valueOf(listaJugadores.size()));
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
                    Log.d("COLOR A CERO", "COLOR A CERO se elimina"+listaJugadores.get(posicion).getNomJugador());
                    listaJugadores.remove(listaJugadores.get(posicion));
                }else{
                    listaJugadores.get(posicion).setContColor(listaJugadores.get(posicion).getContColor()+1);
                    listaJugadores.get(posicion).setColor(color[listaJugadores.get(posicion).getContColor()]);
                    Log.d("COLOR CAMBIA", "COLOR CAMBIA "+listaJugadores.get(posicion).getNomJugador()+" "+listaJugadores.get(posicion).getColor());
                }
            }else{
                //Si no existe en la lista se añade
                listaJugadores.add(new Jugador(perfil.getNombre(),color[contColor], contColor));
                Log.d("AÑADE JUGADOR", "AÑADE JUGADOR "+listaJugadores.get(listaJugadores.size()-1).getNomJugador());
            }
            for (int i=0; i<listaJugadores.size();i++) {
                Log.d("LISTADO", listaJugadores.get(i).getNomJugador() + " " + listaJugadores.get(i).getColor());
            }

            Log.d("TAMAÑO FINAL", String.valueOf(listaJugadores.size()));
            Log.d(" "," ");
        });

        //Metodo para navegar de actividad entre los estandartes
        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleccionPerfiles.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        //Metodo para navegar de actividad entre los estandartes
        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleccionPerfiles.this, Partida.class);
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
                    Toast.makeText(SeleccionPerfiles.this, "Selecciona entre 2 y 4 jugadores.", Toast.LENGTH_LONG).show();
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
                    Log.d("COLORES",String.valueOf(validacionColores[i]));
                    if(validacionColores[i]>=2){
                        valido=false;
                        Toast.makeText(SeleccionPerfiles.this, "No puede haber 2 jugadores con el mismo color.", Toast.LENGTH_LONG).show();
                    }
                }
                //Bucle para añadir los nombres a listaJugadores para ser enviados a la actividad ResumenTurno
                for (int i=0; i<listaJugadores.size();i++) {
                    nombres.add(listaJugadores.get(i).getNomJugador());
                }
                if(valido){
                    Intent intent = new Intent(SeleccionPerfiles.this, activity_ResumenTurno.class);
                    intent.putStringArrayListExtra("jugadores", nombres);
                    startActivity(intent);
                    finish();
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
            sMSeleccion.setChecked(false);
        }
    }
}