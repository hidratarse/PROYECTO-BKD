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
    Switch sMSeleccion;
    Button bComenzar;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    private RecyclerView recyclerView;
    private PerfilesAdapter adaptador;
    private PerfilesViewModel vm;
    int contColor =0;
    String[] color = {"Rojo","Negro","Amarrillo","Rosa"};
    ArrayList<Jugador> listaJugadores = new ArrayList<>();
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
            boolean valido=false;
            int posicion=0;
            for (int i=0; i<listaJugadores.size();i++) {
                if (listaJugadores.get(i).getNomJugador() == perfil.getNombre()) {
                    valido = true;
                    posicion=i;
                }
            }
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
                listaJugadores.add(new Jugador(perfil.getNombre(),color[contColor], contColor));
                Log.d("AÑADE JUGADOR", "AÑADE JUGADOR "+listaJugadores.get(listaJugadores.size()-1).getNomJugador());
            }
            for (int i=0; i<listaJugadores.size();i++) {
                Log.d("LISTADO", listaJugadores.get(i).getNomJugador() + " " + listaJugadores.get(i).getColor());
            }

            Log.d("TAMAÑO FINAL", String.valueOf(listaJugadores.size()));
            Log.d(" "," ");
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleccionPerfiles.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleccionPerfiles.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleccionPerfiles.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        sMSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMSeleccion.isChecked()){
                    Login.mp.start();
                }else{
                    Login.mp.pause();
                }
            }
        });

        bComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valido=true;
                if(listaJugadores.size()<2||listaJugadores.size()>4){
                    valido=false;
                    Toast.makeText(SeleccionPerfiles.this, "Selecciona entre 2 y 4 jugadores.", Toast.LENGTH_LONG).show();
                }
                int rojo = 0;
                int negro = 0;
                int amarillo = 0;
                int rosa = 0;
                for (int i=0; i<listaJugadores.size();i++) {
                    switch (listaJugadores.get(i).getContColor()) {
                        case 0:
                            rojo++;
                            break;
                        case 1:
                            negro++;
                            break;
                        case 2:
                            amarillo++;
                            break;
                        case 3:
                            rosa++;
                            break;
                    }
                }
                Log.d("COLORES", rojo +" "+ negro +" "+ amarillo +" "+ rosa);
                if(rojo>=2||negro>1||amarillo>1||rosa>1){
                    valido=false;
                    Toast.makeText(SeleccionPerfiles.this, "No puede haber 2 jugadores con el mismo color.", Toast.LENGTH_LONG).show();

                }

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
        Login.mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Login.mp.start();
    }
}